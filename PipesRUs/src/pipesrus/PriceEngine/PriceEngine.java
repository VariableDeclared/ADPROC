/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pipesrus.PriceEngine;

import pipesrus.Models.*;
import pipesrus.error.*;

/**
 *
 * @author UP732011 <UP732011@myport.ac.uk>
 */
public class PriceEngine {

    public PriceEngine() {

    }

    private Pipe processTypeThreeToFive(PipeModel model, boolean chemResistance) {
        if (model.getPipeGrade() != PipeGrade.ONE) {
            return null;
        }

        if (!model.getInsulated()) {
            return new PipeTypeThree(model.getLength(), model.getDiameter(),
                    model.getPipeGrade(), chemResistance);
        } else if (!model.getReinforced()) {
            return new PipeTypeFour(model.getLength(), model.getDiameter(),
                    model.getPipeGrade(), chemResistance);
        } else if (model.getPipeGrade() != PipeGrade.TWO) {
            return new PipeTypeFive(model.getLength(), model.getDiameter(),
                    model.getPipeGrade(), chemResistance);
        }

        return null;
    }

    public void getQuote(PipeModel model, QuoteReciever reciever) throws Exception {
        Pipe pipeToUse = null;
        PipeGrade grade = model.getPipeGrade();
        boolean chemResistance = model.getChemicalResistance();
        boolean reinforced = model.getReinforced();
        boolean insulated = model.getInsulated();
        if (model == null) {
            throw new NullPointerException("Model is null");
        }

        switch (model.getPipeColour()) {

            case NO_COLOUR:
                if (!(grade == PipeGrade.FOUR || grade == PipeGrade.FOUR
                        || insulated || reinforced)) {
                    pipeToUse = new PipeTypeOne(model.getLength(), model.getDiameter(),
                            model.getPipeGrade(), chemResistance);
                }
                break;
            case ONE_COLOUR:
                if (!(insulated || reinforced
                        || grade == PipeGrade.ONE || grade == PipeGrade.FIVE)) {
                    pipeToUse = new PipeTypeTwo(model.getLength(), model.getDiameter(),
                            model.getPipeGrade(), chemResistance);
                }
                break;
            case TWO_COLOURS:
                pipeToUse = processTypeThreeToFive(model, chemResistance);
                break;
            default:
                throw new NullPipeColourException("No colour set");
        }
        if (pipeToUse == null) {
            reciever.showError("No pipe for that selection", new Exception("Please refine your criteria"));
        }
        OrderModel quoteModel = new OrderModel(pipeToUse,
                pipeToUse.getPrice(),
                pipeToUse.getVolume());
        reciever.acceptOrderModel(quoteModel);
    }
}
