/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pipesrus;

import java.awt.Dimension;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 *
 * @author pete
 */
public class PipesRUsGUI extends JFrame implements ActionListener {
    
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
    private void print(String value)
    {
        System.out.println(value);
    }
    /**
     * 
     * @param evnt 
     */
    @Override
    public synchronized void actionPerformed(ActionEvent evnt)
    {
        //Not sure yet......
        print("EVNETFAFGFWKRFW");
    }
    private JButton createAndReturnJButtonWithName(String name)
    {
        JButton newButton = new JButton(name);
        newButton.addActionListener(this);
        return newButton;
    }
    
    private JRadioButton createAndReturnJRadioButtonWithName(String name)
    {
        JRadioButton newButton = new JRadioButton(name);
        newButton.addActionListener(this);
        return newButton;
    }
    private void initInterface()
    {
        //this.setLayout(new FlowLayout());
        JTabbedPane mainInterface = new JTabbedPane();
        //this.setLayout(new FlowLayout());
        JPanel tabOnePanel = new JPanel();
        JPanel tabTwoPanel = new JPanel();
        //init panels
       /* JButton goButton = new JButton("Go");
        goButton.addActionListener(this);
        tabOnePanel.add(goButton);*/
        
        tabOnePanel.add(this.createAndReturnJButtonWithName("Go"));
        ButtonGroup colourButtons = new ButtonGroup();
        
        //init colour buttons
        //JRadioButton noColour = this.createAndReturnJRadioButtonWithName("No colour");
        
        JPanel innerPanel = new JPanel();
        innerPanel.add(this.createAndReturnJRadioButtonWithName("No colour"));
        innerPanel.add(this.createAndReturnJRadioButtonWithName("One colour"));
        innerPanel.add(this.createAndReturnJRadioButtonWithName("Two colour"));
        
        tabOnePanel.add(innerPanel);
        
        //select no colour by default
        // do it here
        
        JTextField textBox = new JTextField("Enter grade here");
        textBox.addActionListener(this);
        tabOnePanel.add(textBox);
        
        JToggleButton innerInsulation = new JToggleButton("Inner Insulation");
        innerInsulation.addActionListener(this);
        tabOnePanel.add(innerInsulation);
        
        JToggleButton outReinforcement = new JToggleButton("Outer reinforcement");
        outReinforcement.addActionListener(this);
        tabOnePanel.add(outReinforcement);
        
        JToggleButton chemicalResistance = new JToggleButton("Chemical Resistance");
        chemicalResistance.addActionListener(this);
        tabOnePanel.add(chemicalResistance);
        
        mainInterface.addTab("Tab 1", tabOnePanel);
        mainInterface.addTab("Tab 2", tabTwoPanel);
         
        mainInterface.addChangeListener(new ChangeListener () {
            public void stateChanged(ChangeEvent ent)
            {
                print("SOMETHING HAPPENED");
            }
            
        });
        //mainInterface.setLayout(new FlowLayout());
        this.add(mainInterface);
        
    }
}
