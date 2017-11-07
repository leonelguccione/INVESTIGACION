package vista;

import java.awt.BorderLayout;
import java.awt.HeadlessException;

import javax.swing.JFrame;

import modelo.ArbolPerturbacion;

public class Asignatura_Arbol_Modal extends JFrame
{
    private Asignatura_Panel_Arbol panelCentro;


    public Asignatura_Arbol_Modal(String string, ArbolPerturbacion arbol, Asignatura_Ventana actionlistener) throws HeadlessException
    {
        super(string);
        this.setVisible(true);

        setExtendedState(java.awt
                             .Frame
                             .MAXIMIZED_BOTH);
        this.panelCentro = new Asignatura_Panel_Arbol( actionlistener);
        this.panelCentro.setArbol(arbol);
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


    void setModoEdicion(boolean b)
    {
        this.panelCentro.setModoEdicion(b);
    }

    boolean isModoEdicion()
    {
        return this.panelCentro.isModoEdicion();
    }
}
