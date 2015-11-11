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
import pipesrus.Models.*;
import java.util.*;
import pipesrus.Interface.PipesRUsTable;
import java.lang.reflect.*;
import javax.imageio.ImageIO;
import pipesrus.PriceEngine.*;
import javax.swing.table.*;
import java.io.*;
import pipesrus.misc.*;

/**
 *
 * @author UP732011
 *
 *
 */

/*
 *   To do:
 *       Fix the labelling of combo box stuff
 *
 */
public class PipesRUsGUI extends JFrame implements ActionListener,
        QuoteReciever,
        KeyListener {

    private Dimension userWindow;
    private JTabbedPane mainInterface;
    private HashMap<String, JComponent> components;
    private JPanel informationTab, paymentTab;
    private PriceEngine engine;
    private JTable summaryTable;
    private ArrayList<String[]> stringTable;
    private String [] columnNames ;
    
    

    public PipesRUsGUI()
    {

        super();
        try
        {
            File icon = new File("pipe.ico");
            this.setIconImage(ImageIO.read(icon));
            //*INITMENUBAR* must be called before altering other GUI stuff
            initMenuBar();

            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } 
        catch (Exception ex)
        {
            swallowError(ex);
        }
        this.stringTable = new ArrayList<>(25);
        this.columnNames = new String[]{"Pipe", "Total Length", "Total Value"};
        this.engine = new PriceEngine();
        this.components = new HashMap<>();
        this.informationTab = new JPanel();
        this.paymentTab = new JPanel(new BorderLayout());
        this.mainInterface = new JTabbedPane();
        this.mainInterface.addTab("Information", this.informationTab);
        this.mainInterface.addTab("Order Summary", this.paymentTab);
        this.informationTab.setLayout(new FlowLayout());

        //lock the user to one tab.
        //this.mainInterface.setEnabledAt(1, false);
        this.add(mainInterface);
        this.setTitle("Pipes R Us");
        userWindow = Toolkit.getDefaultToolkit().getScreenSize();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        //////////////////////////////////// exp ////////////////////////
//        Object [][] tableArray =
//                new String[][]{{"Hell","O", "O"}, {"Hell","O", "O"}, {"Row Three", "F", "F"}};
//        
//        openTableWindow(tableArray);
        //////////////////////////////////////// exp //////////////////////
        //throw new UnsupportedOperationException("Not yet implemented");
        initInformationScreenWithModel(new PipeModel());
        initOrderScreen();
        //initInterface();

        this.setSize((int) Math.floor(userWindow.width * 0.8),
                (int) Math.floor(userWindow.height * 0.8));

    }

    public PipesRUsGUI(double sizePercentage)
    {
        this();
        //this.setLayout(new FlowLayout());
        this.setSize((int) Math.floor(userWindow.width * sizePercentage),
                (int) Math.floor(userWindow.height * sizePercentage));

    }

    private void print(String value)
    {
        System.out.println(value);
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        processKey(e);
    }

    private void processKey(KeyEvent e)
    {
        JTextField source = (JTextField) e.getSource();
        try
        {
            Float test = Float.valueOf(source.getText());
            System.out.println(test);
        } catch (Exception ex)
        {
            System.out.println("except");
            this.components.get("Add").setEnabled(false);
        }
        
        if (source.getText().isEmpty())
        {
            this.components.get("Add").setEnabled(false);
        } 
        
        else
            
        {
            this.components.get("Add").setEnabled(true);
        }

    }

    @Override
    public void keyTyped(KeyEvent e)
    {
        processKey(e);
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        processKey(e);
    }

    /**
     *
     * @param evnt
     */
    @Override
    public synchronized void actionPerformed(ActionEvent evnt)
    {
        try
        {
            
            PipeModel model = new PipeModel();
            //main event handler
            switch (evnt.getActionCommand())
            {
                case "Exit":
                    //terminate gracefully
                    System.exit(0);
                    break;
                    
                case "Add":
                    //
                    model = this.tryUpdateModel();
                    break;
                    
                case "Submit":
                    model = this.tryUpdateModel();

                    if (model == null)
                    {
                        throw new Exception("Model is null after trying to update");
                    }

                    System.out.println("Going to quote");
                    this.engine.getQuote(model, this);
                    
                default:
                    break;
            }
        } catch (Exception ex)
        {
            swallowError(ex);
        }
    }

    private JButton createAndReturnJButtonWithName(String name)
    {
        JButton newButton = new JButton(name);
        addDefaultActionListener(newButton);
        return newButton;
    }

    private JPanel createAndReturnJPanel()
    {
        JPanel newPanel = new JPanel(new FlowLayout());
        return newPanel;
    }
//
//    private void addToPanel(JPanel panel, AbstractButton[] items) {
//        for (AbstractButton button : items) {
//            this.components.put("Radio button " + button.getText(), button);
//            panel.add(button);
//        }
//    }

    private void initOrderScreen()
    {
//        JTextArea informationScreen = new JTextArea();
//        this.paymentTab.add(informationScreen);
//        informationScreen.setText("Order not yet completed");
        this.summaryTable = new PipesRUsTable(new PipesRUsTableModel(new String[][]{{"","","£0.00"}}, 
                new String[]{"Pipe", "Total Length", "Total Value"}));

        
        System.out.println("Setting table model");
        
        this.summaryTable.setColumnSelectionAllowed(false);
        JPanel orderPanel = new JPanel(new BorderLayout());
        orderPanel.setBorder(BorderFactory.createTitledBorder("Order Summary"));
        orderPanel.add(new JScrollPane(this.summaryTable), BorderLayout.CENTER);
        this.paymentTab.add(orderPanel, BorderLayout.CENTER);
    }

    private AbstractButton addDefaultActionListener(AbstractButton btn)
    {
        btn.addActionListener(this);
        return btn;
    }

//    private JCheckBox createAndReturnJCheckBoxWithName(String name) {
//        JCheckBox newButton = new JCheckBox(name);
//        newButton.setName(name);
//        addDefaultActionListener(newButton);
//        return newButton;
//    }
    private JMenuItem createMenuItemWithName(String name, ActionListener listener)
    {
        JMenuItem item = new JMenuItem(name);
        item.setName(name);
        addDefaultActionListener(item);
        return item;
    }

//    private JComponent createJToggleButtonWithName(String name) {
//        JToggleButton newButton = new JToggleButton(name);
//        newButton.setName(name);
//        addDefaultActionListener(newButton);
//        return newButton;
//    }
    private void initMenuBar()
    {
        JMenuBar mainMenu = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        fileMenu.add(this.createMenuItemWithName("Exit", this));
        mainMenu.add(fileMenu);
        this.add(mainMenu);
        this.setJMenuBar(mainMenu);

    }

    private Boolean evaluateAsRadioButton(String key)
    {
        return ((JCheckBox) this.components.get(key)).isSelected();
    }

    private String evaluateAsComboBox(String key)
    {
        return ((JComboBox) this.components.get(key)).getSelectedItem().toString();
    }

    private String evaluateAsTextbox(String key)
    {
        return ((JTextField) this.components.get(key)).getText();
    }

    public PipeModel tryUpdateModel()
    {
        PipeColour colour = PipeColour.valueOf(evaluateAsComboBox("Pipe Colour"));
        PipeGrade grade = PipeGrade.valueOf(evaluateAsComboBox("Pipe Grade"));
        Boolean chemicalResistance = evaluateAsRadioButton("Chemical Resistance");
        Boolean insulated = evaluateAsRadioButton("Insulated");
        Boolean reinforced = evaluateAsRadioButton("Reinforced");
        Float length = Float.valueOf(evaluateAsTextbox("Length"));
        Float diameter = Float.valueOf(evaluateAsTextbox("Diameter"));
//        System.out.println(colour);
//        System.out.println(grade);
//        System.out.println(chemicalResistance);
//        System.out.println(insulated);
//        System.out.println(reinforced);
//        System.out.println(length);
//        System.out.println(diameter);
        return new PipeModel(insulated, reinforced, chemicalResistance,
                grade, colour, length, diameter);

    }

    @Override
    public void acceptOrderModel(OrderModel model)
    {
        this.mainInterface.setEnabledAt(1, true);
        this.mainInterface.setSelectedIndex(1);
        JTextArea information = (JTextArea) this.paymentTab.getComponent(0);
        StringBuilder output = new StringBuilder();
        output.append("------------------------- \n");
        output.append("Price: " + model.getTotalCost() + "\n");
        output.append(" Pipe: " + model.getPipe().getGrade() + "\n");
        output.append(" Volume: " + model.getTotalPipe() + "\n");
        information.setText(output.toString());

        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void swallowError(Exception ex)
    {
        //improve this at a later date.
        JOptionPane.showMessageDialog(this, "Something went wrong", "Error", JOptionPane.OK_OPTION);

    }

    private void swallowError(Exception ex, String title)
    {
        JOptionPane.showMessageDialog(this, ex.getCause(), title, JOptionPane.OK_OPTION);
    }

    public void showError(String errorTitle, Exception ex)
    {
        swallowError(ex, errorTitle);
        this.mainInterface.setSelectedIndex(0);
    }

    private void openTableWindow(Object[][] table)
    {
        StandardGUI tableWindow = new StandardGUI("Order Summary");
        JTable orderTable = new JTable(table,
                new String[]
                {
                    "Order Num", "Length", "Total Value"
                });
        JPanel tableContainer = new JPanel();
        tableContainer.setBorder(BorderFactory.createTitledBorder("Order Summary"));
        tableContainer.setLayout(new BorderLayout());
        tableContainer.add(orderTable, BorderLayout.CENTER);
        tableWindow.add(tableContainer);
        tableWindow.setVisible(true);
    }

    private String parseWords(String toParse)
    {
        toParse = toParse.replace("get", "");
        toParse = toParse.replace("_", " ");
        StringBuilder returnString = new StringBuilder();
        for (char c : toParse.toCharArray())
        {
            String sqChar = new String(new char[]
            {
                c
            });
            if (sqChar.equals(sqChar.toUpperCase()) && toParse.indexOf(c) != 0)
            {
                returnString.append(" ");
                returnString.append(sqChar);
            } else
            {
                returnString.append(c);
            }
        }
        return returnString.toString();
    }

    private boolean ignoreMethod(String humanName)
    {
        boolean returnval = false;
        switch (humanName.toLowerCase())
        {
            case "equals":
            case "notify":
            case "hash code":
            case "wait":
            case "notify all":
            case "class":
            case "to string":
                returnval = true;
                break;
        }

        return returnval;
    }

    /**
     * Initialize the interface with the model and it's types.
     *
     * @param model
     */
    //This uses reflection.
    private void initInformationScreenWithModel(Model model)
    {
        JPanel leftPanel, rightPanel, centrePanel, southPanel;
        this.informationTab.setLayout(new BorderLayout());

        leftPanel = new JPanel(new FlowLayout());
        this.informationTab.add(leftPanel, BorderLayout.LINE_START);
        rightPanel = new JPanel(new FlowLayout());
        this.informationTab.add(rightPanel, BorderLayout.LINE_END);
        centrePanel = new JPanel(new FlowLayout());
        this.informationTab.add(centrePanel, BorderLayout.CENTER);
        southPanel = new JPanel(new FlowLayout());
        this.informationTab.add(southPanel, BorderLayout.SOUTH);
        try
        {

            Class<?> specificModel = Class.forName(model.getClass().getName());

            Method[] members = specificModel.getMethods();
            JComponent newComponent = null;
            for (Method member : members)
            {
                String memberHumanName = parseWords(member.getName());
                Class<?> memberType = member.getReturnType();
                if (this.ignoreMethod(memberHumanName))
                {
                    continue;
                }

                //if type is boolean then create a RadioButton, grouped.
                if (memberType.getTypeName().equals(Boolean.class.getTypeName()))
                {
                    newComponent = new JCheckBox(memberHumanName);
                    rightPanel.add(newComponent);
                } //if enum then just create a textbox.
                if (memberType.isEnum())
                {
                    JPanel enclosingPanel = new JPanel();
                    enclosingPanel.add(new JLabel(member.getName()));

                    Object[] eMembers = memberType.getEnumConstants();
                    newComponent = new JComboBox(eMembers);
                    enclosingPanel.add(newComponent);
                    centrePanel.add(newComponent);
                }

                if (memberType.getTypeName().equals(Float.class.getName()))
                {
                    newComponent = new JTextField(memberHumanName);
                    newComponent.addKeyListener(this);
                    leftPanel.add(new JLabel(memberHumanName + ":"));
                    leftPanel.add(newComponent);
                }

                if (newComponent != null)
                {
//                    System.out.println("Added text field for: " + memberHumanName);
//                    this.informationTab.add(newComponent);
//                    this.components.put(memberHumanName, newComponent);
                    this.components.put(memberHumanName, newComponent);
                }

            }

        } catch (ClassNotFoundException ex)
        {
            swallowError(ex, "Class not found");
        } catch (Exception ex)
        {
            swallowError(ex);
        }
        
        
        JButton go = new JButton("Add");
        go.addActionListener(this);
        go.setEnabled(false);
        this.components.put("Add", go);
        southPanel.add(go);
        
        JButton submit = new JButton("Submit");
        submit.addActionListener(this);
        submit.setVisible(true);
        this.components.put("Submit", submit);
        southPanel.add(submit);
        
    }

}
