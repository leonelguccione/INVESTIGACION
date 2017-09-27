package UI;

import arbol_perturbacion_visual.AEvaluableVisual;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

import javax.swing.JScrollPane;

import javax.swing.border.BevelBorder;
import javax.swing.tree.DefaultMutableTreeNode;

import modelo.ArbolPerturbacion;

public class UI_JPanel_Parcial extends JPanel
{
    private AEvaluableVisual jtree_arbol_visual = new AEvaluableVisual();
    private JButton jbGuardar = new JButton("Guardar");
    private JButton jbCancelar = new JButton("Cancelar");
    private JButton jbOcultar = new JButton("Ocultar Nodo");
    private JCheckBox jchVerOcultos = new JCheckBox("Ver Nodos Ocultos", true);
    private JPanel panelSur = new JPanel();
    private JPanel panelIzquierda = new JPanel();
    private JPanel panelDerecha = new JPanel();
    public static final String GUARDAR = "GUARDAR";
    public static final String CANCELAR = "CANCELAR";
    public static final String OCULTAR = "OCULTAR";
    public static final String VEROCULTOS = "VEROCULTOS";
    private ArbolPerturbacion arbol;


    public UI_JPanel_Parcial(ArbolPerturbacion arbol)
    {
        super();
        this.setArbol(arbol);

        this.jtree_arbol_visual.setLineasRectas(true);
        this.setActionCommands();
        this.iniciaGeometria();

        this.jtree_arbol_visual.setMuestraNodosOcultos(true);
        /*thisjPanel_sur.add(jPanel_grilla);
        this.jPanel_grilla.setLayout(new GridLayout(2, 2));
        jPanel_grilla.add(jr_Personalizado);
        jPanel_grilla.add(jButton_capturar);
        jPanel_grilla.add(jr_Tradicional);
        jPanel_grilla.add(this.jbutton_oculta);
        this.jbutton_oculta.setActionCommand("OCULTA");
        this.jbutton_oculta.addActionListener(this);
        jPanel_grilla.add(this.jbutton_muestraOcultos);
        this.jbutton_muestraOcultos.setActionCommand("MUESTRA");
        this.jbutton_muestraOcultos.addActionListener(this);
        this.jr_Personalizado.setSelected(true);
        this.jtree_arbol_visual.setMuestraNodosOcultos(true);
        this.inicia_visual(this.jtree_arbol_visual); */

    }


    public void setArbol(ArbolPerturbacion arbol)
    {
        this.arbol = arbol;
        if (arbol != null)
            this.jtree_arbol_visual.setModel(arbol.getTreeModel());
        else
            this.jtree_arbol_visual.setModel(null);
    }

    public ArbolPerturbacion getArbol()
    {

        return arbol;
    }


    public DefaultMutableTreeNode getNodoSeleccionado()
    {
        return this.jtree_arbol_visual.getNodoSeleccionado();
    }

    public void setOculto(DefaultMutableTreeNode nodo, boolean valor)
    {
        this.jtree_arbol_visual.setOculto(nodo, valor);
        this.jtree_arbol_visual.repaint();
    }

    public void addActionListener(ActionListener listener)
    {
        this.jbOcultar.addActionListener(listener);
        this.jbGuardar.addActionListener(listener);
        this.jbCancelar.addActionListener(listener);
        this.jchVerOcultos.addActionListener(listener);


    }

    private void setActionCommands()
    {
        this.jbGuardar.setActionCommand(UI_JPanel_Parcial.GUARDAR);
        this.jbCancelar.setActionCommand(UI_JPanel_Parcial.CANCELAR);
        this.jbOcultar.setActionCommand(UI_JPanel_Parcial.OCULTAR);
        this.jchVerOcultos.setActionCommand(UI_JPanel_Parcial.VEROCULTOS);
    }

    private void iniciaGeometria()
    {

        this.setLayout(new BorderLayout());
        this.panelSur.add(this.panelIzquierda);
        this.panelSur.add(this.panelDerecha);

        this.add(this.jtree_arbol_visual, BorderLayout.CENTER);
        this.add(this.panelSur, BorderLayout.SOUTH);
        this.panelIzquierda.add(this.jbOcultar);
        this.panelIzquierda.add(this.jchVerOcultos);
        this.panelDerecha.add(jbGuardar);
        this.panelDerecha.add(jbCancelar);
        this.panelDerecha.setBorder(javax.swing
                                         .BorderFactory
                                         .createBevelBorder(javax.swing
                                                                 .border
                                                                 .BevelBorder
                                                                 .RAISED));
        this.panelIzquierda.setBorder(javax.swing
                                           .BorderFactory
                                           .createBevelBorder(javax.swing
                                                                   .border
                                                                   .BevelBorder
                                                                   .RAISED));


    }

    public void setMuestraOcultos()
    {
        this.jtree_arbol_visual.setMuestraNodosOcultos(this.jchVerOcultos.isSelected());
    }

    public void setModoEdicion(boolean valor)
    {
        this.jbCancelar.setEnabled(valor);
        this.jbGuardar.setEnabled(valor);
        this.jbOcultar.setEnabled(valor);


    }


}
