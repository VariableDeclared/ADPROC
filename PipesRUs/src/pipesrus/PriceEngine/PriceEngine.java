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
        if (model.getPipeGrade() == PipeGrade.ONE)
        {
            return null;
        }

        if (!model.getInsulated() && !model.getReinforced()) {
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
    private void throwPipeConfigError() throws PipeConfigurationNotSupportedException
    {
        throw new PipeConfigurationNotSupportedException("There is no pipe for that configuration");
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
                if (grade == PipeGrade.FOUR || grade == PipeGrade.FIVE || insulated || reinforced) 
                {
                    throwPipeConfigError();
                }
                else
                {
                    pipeToUse = new PipeTypeOne(model.getLength(), model.getDiameter(),
                            model.getPipeGrade(), chemResistance);
                }
                break;

            case ONE_COLOUR: //Type 2
                if (grade == PipeGrade.ONE || grade == PipeGrade.FIVE || insulated || reinforced) 
                {
                    throwPipeConfigError();
                }
                else
                {
                    pipeToUse = new PipeTypeTwo(model.getLength(), model.getDiameter(),
                            model.getPipeGrade(), chemResistance);
                }
                break;

            case TWO_COLOURS://Type 3, 4 ,5
                pipeToUse = processTypeThreeToFive(model, chemResistance);
                if (pipeToUse == null)
                        throwPipeConfigError();
                break;

            default:
                throw new NullPipeColourException("No colour set");
        }
        if(pipeToUse == null)
            throw new Exception("Pipe is null");
        
        return pipeToUse;
    }
}
