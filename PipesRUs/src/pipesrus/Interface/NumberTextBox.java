/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pipesrus.Interface;

import javax.swing.*;
import java.awt.event.*;
import java.beans.*;

/**
 *  Simple text box that replaces non-numeric chars with 1.
 * @author pete
 */
public class NumberTextBox extends JTextField {
    
    /**
     * Sets the name as the value passed
     * @param value What the name the number text box
     */
    public NumberTextBox(String value)
    {
        super(value);
        /*little bit of a 'hack' as 'this' inside the anonymous 
        /type refers to the anonymous type.*/
        NumberTextBox ref = this;
        this.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                ref.selectAll();

            }

            public void focusLost(FocusEvent e) {
                ref.select(0, 0);
            }
        });

        
    }
    public NumberTextBox() {
        super();
        //failed code section... 
        /*
        this.addKeyListener(new KeyListener() {

            public void keyPressed(KeyEvent e) {
               handleKey(e);
               System.out.println("Triggered");
            }

            public void keyReleased(KeyEvent e) {
                handleKey(e);
            }

            public void keyTyped(KeyEvent e) {
                handleKey(e);
            }
            
            private void handleKey(KeyEvent e)
            {
                //failed code... may come in useful later
//                    System.out.println((int)e.getKeyChar());
//                    if(((int)e.getKeyChar()) < 48 && ((int)e.getKeyChar()) > 58)
//                    {
//                        NumberTextBox tb =  (NumberTextBox) e.getSource();
//                        tb.setText(tb.getText().replace(e.getKeyChar(),'1'));
//                        
//                    }

            }

        });
                */
    }

    public boolean isAllNumericChar()
    {
        boolean noAlphas = true;
        for(char c : this.getText().toCharArray())
        {
            if(((int)c) < 48 && ((int)c) > 58)
            {       
                noAlphas &= false;
                break;
            }
        }
        return noAlphas;
    }
}
