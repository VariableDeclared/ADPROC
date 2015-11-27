/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pipesrus.Interface;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 *
 * @author 732011 <up732011@myport.ac.uk>
 */
public class InfoDialog extends JDialog implements ActionListener {
    private JTextField infoBox;
    private JButton done;
    private String responseString;
   
    public InfoDialog(String title, String label, String message)
    {
        super();
        this.infoBox = new JTextField("Enter response here");
        this.done = new JButton("Done");
        this.responseString = "";
        JPanel north = null, south = null, centre = null;
        north = instantiateAndFlowLayout(north);
        south = instantiateAndFlowLayout(south);
        centre = instantiateAndFlowLayout(centre);
        
        
        this.done.addActionListener(this);
        
        
        Container contentPane = this.getContentPane();
        contentPane.setLayout(new BorderLayout());

        JTextArea info = new JTextArea(message);
        info.setEditable(false);
        contentPane.add(north, BorderLayout.NORTH);
        contentPane.add(south, BorderLayout.SOUTH);
        contentPane.add(centre, BorderLayout.CENTER);
        centre.add(this.infoBox);
        north.add(new JLabel(label));
        north.add(info);
        south.add(done);
        
        this.pack();
    }
     private JPanel instantiateAndFlowLayout(JPanel panel)
    {
        panel = new JPanel();
       panel.setLayout(new FlowLayout());
        return panel;
    }
    public String getResponse()
    {
        return this.responseString;
    }
    
    public void actionPerformed(ActionEvent evnt)
    {
        this.responseString = this.infoBox.getText();
        
        
        this.setVisible(false);
    }
}
