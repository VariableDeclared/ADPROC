/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pipesrus;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

/**
 *
 * @author pete
 */
public class PipesRUsGUI extends JFrame {
    
    private Dimension userWindow;
    public PipesRUsGUI(double sizePercentage)
    {
        this.setTitle("Pipes R Us");
        userWindow = Toolkit.getDefaultToolkit().getScreenSize();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        
        this.setSize((int)Math.floor(userWindow.width*sizePercentage),
                (int)Math.floor(userWindow.height*sizePercentage));
        
        initInterface();
    }
    
    private void initInterface()
    {
        //this.setLayout(new FlowLayout());
        JTabbedPane mainInterface = new JTabbedPane();
        
        mainInterface.addTab("Tab 1", null);
        mainInterface.addTab("Tab 2", null);
        //mainInterface.setLayout(new FlowLayout());
        this.add(mainInterface);
        
    }
}
