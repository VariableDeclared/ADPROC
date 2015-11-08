/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pipesrus.misc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author Pete
 */
public class StandardGUI extends JFrame {

    private Dimension userWindow;

    public StandardGUI() {
        super();
        initScreenSize();
        init();
    }
 
    public StandardGUI(String title) {
        super(title);
        initScreenSize();
        init();
    }

    public StandardGUI(String title, float percentageSqSize) {
        this(title);
        this.setSize((int) Math.floor(userWindow.width * percentageSqSize),
                (int) Math.floor(userWindow.height * percentageSqSize));
    }

    public StandardGUI(float widthPercent, float heightPercent) {
        this();
        this.setSize((int) Math.floor(userWindow.width * widthPercent),
                (int) Math.floor(userWindow.height * heightPercent));
    }
    private void initScreenSize()
    {
        userWindow = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize((int) Math.floor(userWindow.width * 0.8),
                (int) Math.floor(userWindow.height * 0.8));
    }
    private void init() {
        JMenuBar menuBar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenuItem closeButton = new JMenuItem("Close");
        JFrame ref = this;
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ref.setVisible(false);
            }

        });
        file.add(closeButton);
        menuBar.add(file);
        this.setJMenuBar(menuBar);
    }
}
