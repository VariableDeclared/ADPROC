/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pipesrus;

import javax.swing.ImageIcon;
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
            ImageIcon icon = new ImageIcon("pipe.jpg");
            System.out.println(icon.getDescription());
            gui.setIconImage(icon.getImage());
            gui.setVisible(true);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getClass().toString() + " "+ ex.getMessage());
        }
    }

}
