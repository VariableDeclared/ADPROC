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
import pipesrus.Interface.NumberTextBox;
import java.lang.reflect.*;
import pipesrus.PriceEngine.*;

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
public class PipesRUsGUI extends JFrame implements ActionListener {

    private Dimension userWindow;
    private JTabbedPane mainInterface;
    private HashMap<String, JComponent> components;
    private JPanel informationTab, paymentTab;

    public PipesRUsGUI() {

        super();
        //*INITMENUBAR* must be called before altering other GUI stuff
        initMenuBar();
        this.components = new HashMap<>();
        this.informationTab = new JPanel();
        this.paymentTab = new JPanel();
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            swallowError(ex);
        }
        this.mainInterface = new JTabbedPane();
        this.mainInterface.addTab("Information", this.informationTab);
        this.mainInterface.addTab("Order Summary", this.paymentTab);
        this.add(mainInterface);
        this.setTitle("Pipes R Us");
        userWindow = Toolkit.getDefaultToolkit().getScreenSize();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        //throw new UnsupportedOperationException("Not yet implemented");
        initInformationScreenWithModel(new PipeModel());
        initOrderScreen();
        //initInterface();

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
            case "Go":
                throw new UnsupportedOperationException();
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

    private Boolean evaluateAsRadioButton(String key) {
        return ((JRadioButton) this.components.get(key)).isSelected();
    }

    private String evaluateAsComboBox(String key) {
        return ((JComboBox) this.components.get(key)).getSelectedItem().toString();
    }

    public void tryUpdateModel(PipeModel pipe) {
        PipeColour colour = PipeColour.valueOf(evaluateAsComboBox("Pipe Colour"));
        PipeGrade grade = PipeGrade.valueOf(evaluateAsComboBox("Pipe Grade"));
        Boolean chemicalResistance = evaluateAsRadioButton("Chemical Resistance");
        Boolean insulated = evaluateAsRadioButton("Insulated");
        Boolean reinforced = evaluateAsRadioButton("Reinforced");

        pipe = new PipeModel(insulated, reinforced, chemicalResistance,
                grade, colour);

    }

    public void acceptOrderModel(OrderModel model) {
        this.mainInterface.setSelectedIndex(1);
        JTextArea information = (JTextArea) this.paymentTab.getComponent(0);
        information.setText("--------------------");

        throw new UnsupportedOperationException();
    }

    private void swallowError(Exception ex) {
        //improve this at a later date.
        JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.OK_OPTION);

    }
//
//    public void add(String name, JComponent component) {
//        super.add(component);
//        this.components.put(name, component);
//
//    }
    //needs to be fixed
//    private void initInterface() {
//        //this.setLayout(new FlowLayout());
//        
//       
//        tabOnePanel.setLayout(new BorderLayout());
//        JPanel centrePanel = new JPanel();
//        JPanel lowerPanel = new JPanel();
//        JPanel upperPanel = new JPanel();
//
//        //init panels
//        // tabOnePanel.setLayout(new FlowLayout());
//        lowerPanel.add(this.createAndReturnJButtonWithName("Go"));
//
//        ButtonGroup colourButtons = new ButtonGroup();
//
//        //init colour buttons
//        //JRadioButton noColour = this.createAndReturnJRadioButtonWithName("No colour");
//        ButtonGroup colourGroup = new ButtonGroup();
//        JRadioButton noColour, oneColour, twoColour;
//        noColour = this.createAndReturnJRadioButtonWithName("No colour");
//        oneColour = this.createAndReturnJRadioButtonWithName("One colour");
//        twoColour = this.createAndReturnJRadioButtonWithName("Two colour");
//        colourGroup.add(noColour);
//        colourGroup.add(oneColour);
//        colourGroup.add(twoColour);
//
//        this.addToPanel(centrePanel,
//                new AbstractButton[]{noColour, oneColour, twoColour});
//
//        //select no colour by default
//        // do it here
//        NumberTextBox textBox = new NumberTextBox("Enter grade here");
//        textBox.setName("Pipe Grade");
//
//        textBox.setToolTipText("Put the grade (From 1-5) in here.");
//        textBox.addFocusListener(new FocusListener() {
//            public void focusGained(FocusEvent e) {
//                textBox.selectAll();
//
//            }
//
//            public void focusLost(FocusEvent e) {
//                textBox.select(0, 0);
//            }
//        });
//        textBox.addActionListener(this);
//        this.components.put("Pipe grade", textBox);
//        centrePanel.add(textBox);
//
//        centrePanel.add(createJToggleButtonWithName("Inner Insulation"));
//
//        centrePanel.add(createJToggleButtonWithName("Outer reinforcement"));
//
//        centrePanel.add(createJToggleButtonWithName("Chemical Resistance"));
//
//        tabOnePanel.add(upperPanel, BorderLayout.NORTH);
//        tabOnePanel.add(centrePanel, BorderLayout.CENTER);
//        tabOnePanel.add(lowerPanel, BorderLayout.SOUTH);
//        mainInterface.addTab("Tab 1", tabOnePanel);
//        mainInterface.addTab("Tab 2", tabTwoPanel);
//
//        mainInterface.addChangeListener(new ChangeListener() {
//            public void stateChanged(ChangeEvent ent) {
//                print("SOMETHING HAPPENED");
//            }
//
//        });
//
//        this.add("mainInterface", mainInterface);
//
//        tryUpdateModel(new PipeModel());
//    }
//    

    private String parseWords(String toParse) {
        toParse = toParse.replace("get", "");
        toParse = toParse.replace("_", " ");
        StringBuilder returnString = new StringBuilder();
        for (char c : toParse.toCharArray()) {
            String sqChar = new String(new char[]{c});
            if (sqChar.equals(sqChar.toUpperCase()) && toParse.indexOf(c) != 0) {
                returnString.append(" ");
                returnString.append(sqChar);
            } else {
                returnString.append(c);
            }
        }
        return returnString.toString();
    }

    private void initOrderScreen() {
        JTextArea informationScreen = new JTextArea();
        this.paymentTab.add(informationScreen);
        informationScreen.setText("Order not yet completed");

    }

    /**
     * Initialize the interface with the model and it's types.
     *
     * @param model
     */
    //This uses reflection.
    private void initInformationScreenWithModel(Model model) {
        try {

            ButtonGroup buttonGroup = new ButtonGroup();
            Class<?> specificModel = Class.forName(model.getClass().getName());

            Method[] members = specificModel.getMethods();
            JComponent newComponent = null;
            for (Method member : members) {
                String memberHumanName = parseWords(member.getName());
                Class<?> memberType = member.getReturnType();

                //if type is boolean then create a RadioButton, grouped.
                if (memberType.getTypeName().equals(Boolean.class.getTypeName())) {
                    newComponent = new JRadioButton(memberHumanName);
                    buttonGroup.add((JRadioButton) newComponent);
                } //if enum then just create a textbox.
                if (memberType.isEnum()) {
                    JPanel enclosingPanel = new JPanel();
                    enclosingPanel.add(new JLabel(member.getName()));

                    Object[] eMembers = memberType.getEnumConstants();
                    newComponent = new JComboBox(eMembers);
                    enclosingPanel.add(newComponent);

                } else if (memberType.getTypeName().equals(String.class.getName())
                        && !member.getName().equals("toString")) {
                    newComponent = new JTextField(memberHumanName);

                }
                if (newComponent != null) {
                    this.informationTab.add(newComponent);
                    this.components.put(memberHumanName, newComponent);
                }

            }

        } catch (ClassNotFoundException ex) {
            swallowError(ex);
        } catch (Exception ex) {
            swallowError(ex);
        }
        JButton go = new JButton("Go");
        go.addActionListener(this);
        this.informationTab.add(go);
    }

}
