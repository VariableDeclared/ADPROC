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
        this.infoBox = new JTextField();
        this.done = new JButton();
        this.responseString = "";
        
        this.done.addActionListener(this);
        
        
        Container contentPane = this.getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(new JLabel(label));
        JTextArea info = new JTextArea(message);
        info.setEditable(false);
        contentPane.add(info);
        contentPane.add(infoBox);
        
        
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
