package UI;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.HeadlessException;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import modelo.ArbolPerturbacion;
import modelo.Examen;

public class Correccion_Arbol_Modal extends JFrame
{
    private Correccion_Panel_Arbol panelCentro;


    public Correccion_Arbol_Modal(String string, ArbolPerturbacion arbol, ActionListener actionlistener) throws HeadlessException
    {
        super(string);
        this.setVisible(true);

        setExtendedState(java.awt
                             .Frame
                             .MAXIMIZED_BOTH);
        this.panelCentro = new Correccion_Panel_Arbol( arbol,actionlistener, false);
        
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

    void setExamen(Examen examen)
    {this.panelCentro.setExamen(examen);
    }

    void refresh()
    {                    this.panelCentro.actualiza_jtree();
                    this.panelCentro.verifica_modificado();
    }
}
