package vista;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;

import java.awt.event.ActionListener;

import java.awt.event.FocusEvent;

import java.awt.event.WindowAdapter;

import java.awt.event.WindowEvent;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;

import modelo.ArbolPerturbacion;

public class Parcial_Arbol_Modal extends JFrame
{
    private Parcial_Panel_Arbol panelCentro;


    public Parcial_Arbol_Modal(String string, ArbolPerturbacion arbol, ActionListener actionlistener) throws HeadlessException
    {
        super(string);
        this.setVisible(true);

        setExtendedState(java.awt
                             .Frame
                             .MAXIMIZED_BOTH);
        this.panelCentro = new Parcial_Panel_Arbol(arbol, actionlistener, false);
        this.panelCentro.setModoEdicion(true);
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
    public ArbolPerturbacion getArbol()
    {
        return this.panelCentro.getArbol();
        }
}
