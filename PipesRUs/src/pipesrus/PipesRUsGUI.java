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
import pipesrus.Models.*;
import java.util.*;
/**
 *
 * @author Pete
 * 
 * 
 */
public class PipesRUsGUI extends JFrame implements ActionListener {

    private Dimension userWindow;
    private JTabbedPane mainInterface;
    private HashMap<String, JComponent> components;
    private JDialog interfaceDialog;

    public PipesRUsGUI()
    {
        super();
        this.components = new HashMap<>();
        this.interfaceDialog = new JDialog(this, "", true);
        this.setTitle("Pipes R Us");
        userWindow = Toolkit.getDefaultToolkit().getScreenSize();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        initMenuBar();
        initInterface();
       
        this.setSize((int) Math.floor(userWindow.width * 0.8),
                (int) Math.floor(userWindow.height * 0.8));
    }
    public PipesRUsGUI(double sizePercentage) {
        this();
        //this.setLayout(new FlowLayout());
        this.setSize((int) Math.floor(userWindow.width * sizePercentage),
                (int) Math.floor(userWindow.height * sizePercentage));
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
        addDefaultActionListener(newButton);
        return newButton;
    }

    private void addToPanel(JPanel panel, AbstractButton[] items) {
        for (AbstractButton button : items) {
            this.components.put("Radio button " + button.getText(), button);
            panel.add(button);
        }
    }

    private AbstractButton addDefaultActionListener(AbstractButton btn) {
        btn.addActionListener(this);
        return btn;
    }

    private JRadioButton createAndReturnJRadioButtonWithName(String name) {
        JRadioButton newButton = new JRadioButton(name);
        newButton.setName(name);
        addDefaultActionListener(newButton);
        return newButton;
    }

    private JMenuItem createMenuItemWithName(String name, ActionListener listener) {
        JMenuItem item = new JMenuItem(name);
        item.setName(name);
        addDefaultActionListener(item);
        return item;
    }

    private JComponent createJToggleButtonWithName(String name) {
        JToggleButton newButton = new JToggleButton(name);
        newButton.setName(name);
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
    public JPanel [] processPanels()
    {
        JPanel[] panels = new JPanel[this.mainInterface.getComponents().length];
        
        //continue
    }
    public void tryUpdateModel(PipeModel pipe)
    {
       try{
         JPanel [] panels = (JPanel []) this.mainInterface.getComponents();
          // print(this.mainInterface.getComponent(0).getClass().toString())
         JComponent [] inputComponents  = (JComponent []) panels[0].getComponents();

         for(JComponent component : inputComponents)
         {
             if(component.getClass().equals(JButton.class))
             {
                 print("Found a JButton");
             }
         }
       
       }
       catch(ClassCastException ex)
       {
           swollowError(ex);
       }
       
    }
    private void swollowError(Exception ex)
    {
        //improve this at a later date.
        JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.OK_OPTION);
        
    }
    public void add(String name, JComponent component)
    {
        super.add(component);
        this.components.put(name, component);
        
    }
    private void initInterface() {
        //this.setLayout(new FlowLayout());
        mainInterface = new JTabbedPane();
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
        textBox.setName("Pipe Grade");
        
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
        this.components.put("Pipe grade", textBox);
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

        this.add("mainInterface", mainInterface);
        
        
        tryUpdateModel(new PipeModel());
    }
}
