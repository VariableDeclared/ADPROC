/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pipesrus;

import javax.swing.JOptionPane;

/**
 *
 * @author UP738106
 */
public class PipesRUs {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            //Instiate the gui
            PipesRUsGUI gui = new PipesRUsGUI(0.5);

            gui.setVisible(true);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

}
