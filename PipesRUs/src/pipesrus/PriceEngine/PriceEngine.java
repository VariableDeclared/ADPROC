/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pipesrus.PriceEngine;

import java.util.LinkedList;
import jdk.nashorn.internal.objects.NativeArray;
import pipesrus.Models.*;
import pipesrus.error.*;

/**
 *
 * @author UP732011 <UP732011@myport.ac.uk>
 */
public class PriceEngine {

    public PriceEngine()
    {

    }

    private Pipe processTypeThreeToFive(PipeModel model, boolean chemResistance)
    {
        if (model.getPipeGrade() != PipeGrade.ONE) {
            return null;
        }

        if (!model.getInsulated()) {
            return new PipeTypeThree(model.getLength(),
                    model.getDiameter(),
                    model.getPipeGrade(),
                    chemResistance);

        } else {
            if (!model.getReinforced()) {
                return new PipeTypeFour(model.getLength(),
                        model.getDiameter(),
                        model.getPipeGrade(),
                        chemResistance);

            } else {
                if (model.getPipeGrade() != PipeGrade.TWO) {
                    return new PipeTypeFive(model.getLength(),
                            model.getDiameter(),
                            model.getPipeGrade(),
                            chemResistance);
                }
            }
        }

        return null;
    }

    public Pipe getPipeForModel(PipeModel model) throws Exception
    {
        Pipe pipeToUse = null;

        PipeGrade grade = model.getPipeGrade();

        boolean chemResistance = model.getChemicalResistance();
        boolean reinforced = model.getReinforced();
        boolean insulated = model.getInsulated();
        if (model == null) {
            throw new NullPointerException("Model is null");
        }
        switch (model.getPipeColour()) {

            case NO_COLOUR: //Type 1
                if (!(grade == PipeGrade.FOUR || grade == PipeGrade.FOUR //Need to correct this logic
                        || insulated || reinforced)) {
                    pipeToUse = new PipeTypeOne(model.getLength(), model.getDiameter(),
                            model.getPipeGrade(), chemResistance);
                }
                break;

            case ONE_COLOUR: //Type 2
                if (!(insulated || reinforced
                        || grade == PipeGrade.ONE || grade == PipeGrade.FIVE)) {
                    pipeToUse = new PipeTypeTwo(model.getLength(), model.getDiameter(),
                            model.getPipeGrade(), chemResistance);
                }
                break;

            case TWO_COLOURS://Type 3, 4 ,5
                pipeToUse = processTypeThreeToFive(model, chemResistance);
                break;

            default:
                throw new NullPipeColourException("No colour set");
        }
        if(pipeToUse == null)
            throw new Exception("Pipe is null");
        
        return pipeToUse;
    }

//    public void getQuote(LinkedList<Pipe> pipes, QuoteReciever reciever) throws Exception
//    {
//        Pipe pipeToUse = null;
//        PipeModel model = new PipeModel();
//
////        for (int i = 0; i < modelList.size(); i++) {
////            model = modelList.get(i);
////
////            PipeGrade grade = model.getPipeGrade();
////
////            boolean chemResistance = model.getChemicalResistance();
////            boolean reinforced = model.getReinforced();
////            boolean insulated = model.getInsulated();
////            if (model == null) {
////                throw new NullPointerException("Model is null");
////            }
////
////            switch (modelList.get(i).getPipeColour()) {
////
////                case NO_COLOUR: //Type 1
////                    if (!(grade == PipeGrade.FOUR || grade == PipeGrade.FOUR //Need to correct this logic
////                            || insulated || reinforced)) {
////                        pipeToUse = new PipeTypeOne(model.getLength(), model.getDiameter(),
////                                model.getPipeGrade(), chemResistance);
////                    }
////                    break;
////
////                case ONE_COLOUR: //Type 2
////                    if (!(insulated || reinforced
////                            || grade == PipeGrade.ONE || grade == PipeGrade.FIVE)) {
////                        pipeToUse = new PipeTypeTwo(model.getLength(), model.getDiameter(),
////                                model.getPipeGrade(), chemResistance);
////                    }
////                    break;
////
////                case TWO_COLOURS://Type 3, 4 ,5
////                    pipeToUse = processTypeThreeToFive(model, chemResistance);
////                    break;
////
////                default:
////                    throw new NullPipeColourException("No colour set");
////            }
////
////            if (pipeToUse == null) {
////                reciever.showError("No pipe for that selection", new Exception("Please refine your criteria"));
////            }
////
////            OrderModel quoteModel = new OrderModel(modelList);
////
////            reciever.acceptOrderModel(quoteModel);
////        }
//    }
}
