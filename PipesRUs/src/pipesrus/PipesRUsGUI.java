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
import java.math.BigDecimal;
import java.math.RoundingMode;

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
    private DefaultTableModel tableModel;
    private LinkedList<Pipe> modelList;
    private Double runningTotal = 0.0;

    public PipesRUsGUI()
    {

        super();
        try {

            
            //*INITMENUBAR* must be called before altering other GUI stuff
            initMenuBar();

            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            swallowError(ex);
        }

        this.engine = new PriceEngine();
        this.components = new HashMap<>();

        this.informationTab = new JPanel();
        this.paymentTab = new JPanel(new BorderLayout());

        this.mainInterface = new JTabbedPane();
        this.mainInterface.addTab("Information", this.informationTab);
        this.mainInterface.addTab("Order Summary", this.paymentTab);

        this.informationTab.setLayout(new FlowLayout());

        this.modelList = new LinkedList<>(); //for storing each model

        //lock the user to one tab.
        this.mainInterface.setEnabledAt(1, false);
        this.add(mainInterface);
        this.setTitle("Pipes R Us");
        userWindow = Toolkit.getDefaultToolkit().getScreenSize();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        initInformationScreenWithModel(new PipeModel());
        initOrderScreen();

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
        try {
            Double test = Double.valueOf(source.getText());
        } catch (Exception ex) {
            this.components.get("Add").setEnabled(false);
            return;
        }
        if (source.getText().isEmpty()) {
            this.components.get("Add").setEnabled(false);
        } else {
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
    private String writeStringToFile(String textToWrite) throws Exception
    {
        System.out.println("Writing to file");
        Calendar cal = Calendar.getInstance();
        String filename = cal.get(Calendar.DAY_OF_MONTH) + cal.get(Calendar.MONTH)
                + cal.get(Calendar.YEAR)+ "-" + UUID.randomUUID().toString()+".txt";
        File textFile = new File(filename);
        textFile.createNewFile();
        if(!textFile.canWrite())
        {
            throw new Exception("Cannot write to text file");
        }
        FileWriter fw = new FileWriter(textFile.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(textToWrite, 0, textToWrite.length());
        bw.close();
        fw.close();
        
        return filename;
        
    }
    /**
     *
     * @param evnt
     */
    @Override
    public synchronized void actionPerformed(ActionEvent evnt)
    {
        try {

            //main event handler
            switch (evnt.getActionCommand()) {
                case "Exit":
                    //terminate gracefully
                    System.exit(0);
                    break;

                case "Add":
                    PipeModel model = this.tryUpdateModel();
                     //append to linkedList
                    
                    updateRunningTotal(model.getValue()); //add price to order total
                    double value = new BigDecimal(model.getValue()).setScale(2, RoundingMode.HALF_UP).doubleValue();
                    

                    this.tableModel.addRow(new Object[]{
                        model.getPipeGrade(),
                        model.getPipeColour(),
                        model.getInsulated(),
                        model.getReinforced(),
                        model.getLength(),
                        "£"+value
                    });

                    

                    break;

                case "Submit":

                    if (modelList.size() == 0) {
                        throw new Exception("No pipes added to order");
                    }

//                    System.out.println("Size: " + modelList.size()); //debug 
//
//                    System.out.println("Going to quote");
                    //move the user to the order tab
                    this.mainInterface.setSelectedIndex(1);
                    this.mainInterface.setEnabledAt(1, true);
                    break;
                case "Remove last pipe":
                    if(modelList.size() == 0)
                    {
                        JOptionPane.showMessageDialog(this, "No pipes to remove");
                        break;
                    }
                    Pipe removedPipe = (Pipe) this.modelList.removeLast();
                    ((DefaultTableModel)this.summaryTable.getModel()).removeRow(modelList.size()+1);
                    //minus is important here
                    updateRunningTotal(-removedPipe.getPrice());
                    break;
                case "Clear order":
                    for(int i = 0; i < this.modelList.size(); i++)
                    {
                        this.tableModel.removeRow(i+1);
                        updateRunningTotal(-this.runningTotal);
                    }
                    
                    this.modelList.clear();
                    this.mainInterface.setSelectedIndex(0);
                    this.mainInterface.setEnabledAt(1, false);
                    break;
                case "Write to file":
                    String textToFile = "";
                    for(int row = 0; row < this.tableModel.getRowCount(); row++)
                    {
                        for(int col = 0; col < this.tableModel.getColumnCount(); col++)
                        {
                            System.out.println(textToFile);
                            textToFile += " " + this.tableModel.getValueAt(row, col);
                            
                        }
                        textToFile += "\n";
                    }
                    JOptionPane.showMessageDialog(this,"File name: "+ writeStringToFile(textToFile));
                    break;
                default:
                    break;
            }
        } catch (Exception ex) {
            swallowError(ex);
        }
    }
    private void updateRunningTotal(double value)
    {
        this.runningTotal += new BigDecimal(value).setScale(2, RoundingMode.HALF_UP).doubleValue();
        this.tableModel.setValueAt("£"+ this.runningTotal, 0, 5); //sets the 'Total' cell to the sum of all pipe prices
    }
    private JButton createAndReturnJButtonWithName(String name)
    {
        JButton newButton = new JButton(name);
        addDefaultActionListener(newButton);
        return newButton;
    }

    private void initOrderScreen()
    {
        this.summaryTable = new PipesRUsTable(new PipesRUsTableModel(new String[][]{{"", "", "", "", "", "£0.00"}},
                new String[]{"Pipe Grade", "Colour", "Inner Insulation", "Outer Reinforcement", "Total Length", "Total Value"}));

        System.out.println("Setting table model");

        this.summaryTable.setColumnSelectionAllowed(false);
        JPanel orderPanel = new JPanel(new BorderLayout());
        JPanel orderButtons = new JPanel(new FlowLayout());
        
        orderPanel.setBorder(BorderFactory.createTitledBorder("Order Summary"));
        orderButtons.setBorder(BorderFactory.createTitledBorder("Controls"));
        
        orderPanel.add(new JScrollPane(this.summaryTable), BorderLayout.CENTER);
        orderButtons.add(createAndReturnJButtonWithName("Remove last pipe"));
        orderButtons.add(createAndReturnJButtonWithName("Clear order"));
        orderButtons.add(createAndReturnJButtonWithName("Write to file"));
        
        
        this.paymentTab.add(orderButtons, BorderLayout.SOUTH);
        this.paymentTab.add(orderPanel, BorderLayout.CENTER);
        this.tableModel = (DefaultTableModel) this.summaryTable.getModel();
    }

    private AbstractButton addDefaultActionListener(AbstractButton btn)
    {
        btn.addActionListener(this);
        return btn;
    }

    private JMenuItem createMenuItemWithName(String name, ActionListener listener)
    {
        JMenuItem item = new JMenuItem(name);
        item.setName(name);
        addDefaultActionListener(item);
        return item;
    }

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
        try {
            PipeColour colour = PipeColour.valueOf(evaluateAsComboBox("Pipe Colour"));
            PipeGrade grade = PipeGrade.valueOf(evaluateAsComboBox("Pipe Grade"));
            Boolean chemicalResistance = evaluateAsRadioButton("Chemical Resistance");
            Boolean insulated = evaluateAsRadioButton("Insulated");
            Boolean reinforced = evaluateAsRadioButton("Reinforced");
            Double length = Double.valueOf(evaluateAsTextbox("Length"));
            Double diameter = Double.valueOf(evaluateAsTextbox("Diameter"));
            PipeModel model = new PipeModel(insulated, reinforced, chemicalResistance,
                    grade, colour, length, diameter);
            Pipe pipe = this.engine.getPipeForModel(model);
            double value = pipe.getPrice();
            model.setValue(value);
            modelList.add(pipe);
            return model;
        } catch (Exception ex) {
            swallowError(ex);
            this.components.get("Submit").setEnabled(false);
            return null;
        }

    }

    @Override
    public void acceptOrderModel(OrderModel model)
    {
        this.mainInterface.setEnabledAt(1, true);
        this.mainInterface.setSelectedIndex(1);
    }

    private void swallowError(Exception ex)
    {
        //improve this at a later date.
        JOptionPane.showMessageDialog(this, "Something went wrong \n" + ex.getMessage(), "Error", JOptionPane.OK_OPTION);

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

    private String parseWords(String toParse)
    {
        toParse = toParse.replace("get", "");
        toParse = toParse.replace("_", " ");
        StringBuilder returnString = new StringBuilder();
        for (char c : toParse.toCharArray()) {
            String sqChar = new String(new char[]{
                c
            });
            if (sqChar.equals(sqChar.toUpperCase()) && toParse.indexOf(c) != 0) {
                returnString.append(" ");
                returnString.append(sqChar);
            } else {
                returnString.append(c);
            }
        }
        return returnString.toString();
    }

    private boolean ignoreMethod(String humanName)
    {
        boolean returnval = false;
        switch (humanName.toLowerCase()) {
            case "equals":
            case "notify":
            case "hash code":
            case "wait":
            case "notify all":
            case "class":
            case "to string":
            case "voloume":
            case "voolume":
            case "price":
            case "value":

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
        leftPanel.setBorder(BorderFactory.createTitledBorder("Value Entry"));
        rightPanel = new JPanel(new FlowLayout());
        rightPanel.setBorder(BorderFactory.createTitledBorder("Attributes"));
        this.informationTab.add(rightPanel, BorderLayout.LINE_END);
        centrePanel = new JPanel(new FlowLayout());
        centrePanel.setBorder(BorderFactory.createTitledBorder("Materials"));
        this.informationTab.add(centrePanel, BorderLayout.CENTER);
        southPanel = new JPanel(new FlowLayout());
        this.informationTab.add(southPanel, BorderLayout.SOUTH);
        try {

            Class<?> specificModel = Class.forName(model.getClass().getName());

            Method[] members = specificModel.getMethods();
            JComponent newComponent = null;
            for (Method member : members) {
                String memberHumanName = parseWords(member.getName());
                Class<?> memberType = member.getReturnType();
                if (this.ignoreMethod(memberHumanName)) {
                    continue;
                }

                //if type is boolean then create a RadioButton, grouped.
                if (memberType.getTypeName().equals(Boolean.class.getTypeName())) {
                    newComponent = new JCheckBox(memberHumanName);
                    rightPanel.add(newComponent);
                } //if enum then just create a textbox.
                if (memberType.isEnum()) {
                    JPanel enclosingPanel = new JPanel();
                    enclosingPanel.add(new JLabel(member.getName()));

                    Object[] eMembers = memberType.getEnumConstants();
                    newComponent = new JComboBox(eMembers);
                    enclosingPanel.add(newComponent);
                    centrePanel.add(newComponent);
                }

                if (memberType.getTypeName().equals(double.class.getName())) {
                    newComponent = new JTextField(memberHumanName);
                    newComponent.addKeyListener(this);
                    leftPanel.add(new JLabel(memberHumanName + ":"));
                    leftPanel.add(newComponent);
                }

                if (newComponent != null) {
                    this.components.put(memberHumanName, newComponent);
                }

            }

        } catch (ClassNotFoundException ex) {
            swallowError(ex, "Class not found");
        } catch (Exception ex) {
            swallowError(ex);
        }

        JButton add = createAndReturnJButtonWithName("Add");
        add.setEnabled(false);
        this.components.put("Add", add);
        southPanel.add(add);

        JButton submit = createAndReturnJButtonWithName("Submit");
        submit.setEnabled(true);
        this.components.put("Submit", submit);
        southPanel.add(submit);
        southPanel.setBorder(BorderFactory.createTitledBorder("Controls"));
    }

}
