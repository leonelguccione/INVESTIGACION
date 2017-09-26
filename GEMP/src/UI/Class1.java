package UI;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class Class1 extends JFrame
{
    private UI_Panel_Parcial panelCentro=new UI_Panel_Parcial();
  
   
    public Class1(String string) throws HeadlessException
    {
        super(string);
        this.setVisible(true);
        setExtendedState(java.awt
                             .Frame
                             .MAXIMIZED_BOTH);
        this.inicia_visual();
    }

   
    private void inicia_visual()
    {
        this.getContentPane().removeAll();
        this.getContentPane().add(this.panelCentro, BorderLayout.CENTER);
       
        
        this.pack();
        setExtendedState(java.awt
                             .Frame
                             .MAXIMIZED_BOTH);
    }
}
