package vista;

import arbol_perturbacion_visual.AEvaluableVisual;

import java.awt.BorderLayout;

import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.border.TitledBorder;

import modelo.ArbolPerturbacion;

public class Promedio_Arbol_No_Modal extends JFrame implements Interface_Arbol_Promedio
{
    private Promedio_Panel_Arbol miPanel=new Promedio_Panel_Arbol();
    private static Promedio_Arbol_No_Modal instance=null;


    public static Promedio_Arbol_No_Modal getInstance()
    {
        if(instance==null) instance=new Promedio_Arbol_No_Modal();
        return instance;
    }

    private Promedio_Arbol_No_Modal()
    {
        super();
        
        this.getContentPane().setLayout(new BorderLayout(0, 0));
        this.getContentPane().add(this.miPanel, BorderLayout.CENTER);
        setExtendedState(java.awt
                             .Frame
                             .MAXIMIZED_BOTH);
        this.setVisible(true);


    }

    @Override
    public void setArbol(ArbolPerturbacion a)
    {
        this.miPanel.setArbol(a);
        
    }


}
