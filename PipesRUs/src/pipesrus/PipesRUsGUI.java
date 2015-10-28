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
 * @author Pete
 * 
 * 
 */
public class PipesRUsGUI extends JFrame implements ActionListener {

    private Dimension userWindow;
    private JTabbedPane mainInterface;

    public PipesRUsGUI(double sizePercentage) {
        this.setTitle("Pipes R Us");
        userWindow = Toolkit.getDefaultToolkit().getScreenSize();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        //this.setLayout(new FlowLayout());
        this.setSize((int) Math.floor(userWindow.width * sizePercentage),
                (int) Math.floor(userWindow.height * sizePercentage));
        initMenuBar();
        initInterface();

    }

    private void print(String value) {
        System.out.println(value);
    }

    /**
     *
     * @param evnt
     */
    @Override
    public synchronized void actionPerformed(ActionEvent evnt) {
        //main event handler
        switch (evnt.getActionCommand()) {
            case "Exit":
                //terminate gracefully
                System.exit(0);
                break;
            default:
                break;
        }
    }

    private JButton createAndReturnJButtonWithName(String name) {
        JButton newButton = new JButton(name);
        newButton.addActionListener(this);
        return newButton;
    }

    private void addToPanel(JPanel panel, AbstractButton[] items) {
        for (AbstractButton button : items) {
            panel.add(button);
        }
    }

    private AbstractButton addDefaultActionListener(AbstractButton btn) {
        btn.addActionListener(this);
        return btn;
    }

    private JRadioButton createAndReturnJRadioButtonWithName(String name) {
        JRadioButton newButton = new JRadioButton(name);
        addDefaultActionListener(newButton);;
        return newButton;
    }

    private JMenuItem createMenuItemWithName(String name, ActionListener listener) {
        JMenuItem item = new JMenuItem(name);
        addDefaultActionListener(item);
        return item;
    }

    private JComponent createJToggleButtonWithName(String name) {
        JToggleButton newButton = new JToggleButton(name);
        addDefaultActionListener(newButton);
        return newButton;
    }

    private void initMenuBar() {
        JMenuBar mainMenu = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        fileMenu.add(this.createMenuItemWithName("Exit", this));
        mainMenu.add(fileMenu);
        this.add(mainMenu);
        this.setJMenuBar(mainMenu);

    }

    private void initInterface() {
        //this.setLayout(new FlowLayout());
        mainInterface = new JTabbedPane();
        //this.setLayout(new FlowLayout());
        JPanel tabOnePanel = new JPanel();
        JPanel tabTwoPanel = new JPanel();
        tabOnePanel.setLayout(new BorderLayout());
        JPanel centrePanel = new JPanel();
        JPanel lowerPanel = new JPanel();
        JPanel upperPanel = new JPanel();
        
        //init panels
        // tabOnePanel.setLayout(new FlowLayout());
        lowerPanel.add(this.createAndReturnJButtonWithName("Go"));
        ButtonGroup colourButtons = new ButtonGroup();
        
        //init colour buttons
        //JRadioButton noColour = this.createAndReturnJRadioButtonWithName("No colour");
        ButtonGroup colourGroup = new ButtonGroup();
        JRadioButton noColour, oneColour, twoColour;
        noColour = this.createAndReturnJRadioButtonWithName("No colour");
        oneColour = this.createAndReturnJRadioButtonWithName("One colour");
        twoColour = this.createAndReturnJRadioButtonWithName("Two colour");
        colourGroup.add(noColour);
        colourGroup.add(oneColour);
        colourGroup.add(twoColour);
        
        this.addToPanel(centrePanel,
                new AbstractButton[]{noColour, oneColour, twoColour});

        //select no colour by default
        // do it here
        JTextField textBox = new JTextField("Enter grade here");
        textBox.setToolTipText("Put the grade (From 1-5) in here.");
        textBox.addFocusListener(new FocusListener() { 
                public void focusGained(FocusEvent e)
                {
                    textBox.selectAll();
                    
                }
                
                public void focusLost(FocusEvent e)
                {
                    textBox.select(0, 0);
                }
        });
        textBox.addActionListener(this);
        centrePanel.add(textBox);

        centrePanel.add(createJToggleButtonWithName("Inner Insulation"));

        centrePanel.add(createJToggleButtonWithName("Outer reinforcement"));

        centrePanel.add(createJToggleButtonWithName("Chemical Resistance"));

        tabOnePanel.add(upperPanel, BorderLayout.NORTH);
        tabOnePanel.add(centrePanel, BorderLayout.CENTER);
        tabOnePanel.add(lowerPanel, BorderLayout.SOUTH);
        mainInterface.addTab("Tab 1", tabOnePanel);
        mainInterface.addTab("Tab 2", tabTwoPanel);

        mainInterface.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent ent) {
                print("SOMETHING HAPPENED");
            }
            
            

        });

        this.add(mainInterface);

    }
}
