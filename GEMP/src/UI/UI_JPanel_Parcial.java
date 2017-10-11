package UI;

import arbol_perturbacion_visual.AEvaluableVisual;

import arbolvisual.NodoVisual;

import java.awt.BorderLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.tree.DefaultMutableTreeNode;

import modelo.ArbolPerturbacion;

public class UI_JPanel_Parcial extends JPanel implements ActionListener
{
    private AEvaluableVisual jtree_arbol_visual = new AEvaluableVisual();
    private JButton jbGuardar = new JButton("Guardar");
    private JButton jbCancelar = new JButton("Cancelar");
    private JButton jbOcultar = new JButton("Ocultar Nodo");
    private JButton jbMaximizar = null;
    private JCheckBox jchVerOcultos = new JCheckBox("Ver Nodos Ocultos", true);
    private JPanel panelSur = new JPanel();
    private JPanel panelIzquierda = new JPanel();
    private JPanel panelDerecha = new JPanel();
    public static final String GUARDAR = "GUARDAR";
    public static final String CANCELAR = "CANCELAR";
    public static final String OCULTAR = "OCULTAR";
    public static final String VEROCULTOS = "VEROCULTOS";
    public static final String MAXIMIZAR = "MAXIMIZAR";
    private ArbolPerturbacion arbol;
    private ActionListener actionListener;


    public UI_JPanel_Parcial(ArbolPerturbacion arbol, ActionListener actionListener, boolean valor)
    {
        super();
        this.setArbol(arbol);
        this.actionListener = actionListener;

        this.jtree_arbol_visual.setLineasRectas(true);
        this.setActionCommands();
        this.iniciaGeometria();
        this.jtree_arbol_visual.setMuestraNodosOcultos(true);
        this.jchVerOcultos.addActionListener(this);
        this.jbOcultar.addActionListener(this);
        this.addActionListener(this.actionListener);
        if (valor)
            this.agregaBotonMaximizar();
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
        this.setModoEdicion(false);
        this.jtree_arbol_visual.setInheritsPopupMenu(true);


    }

    public void setModoEdicion(boolean valor)
    {
        this.jbCancelar.setEnabled(valor);
        this.jbGuardar.setEnabled(valor);
        this.jbOcultar.setEnabled(valor);
        if (this.jbMaximizar != null)
            this.jbMaximizar.setEnabled(valor);
    }


    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getActionCommand().equals(UI_JPanel_Parcial.VEROCULTOS))
            this.jtree_arbol_visual.setMuestraNodosOcultos(this.jchVerOcultos.isSelected());
        if (e.getActionCommand().equals(UI_JPanel_Parcial.OCULTAR) &&
            this.jtree_arbol_visual.getNodoSeleccionado() != null)
        {
            NodoVisual nv = this.jtree_arbol_visual.getNodoVisual(this.jtree_arbol_visual.getNodoSeleccionado());

            this.jtree_arbol_visual.setOculto(this.jtree_arbol_visual.getNodoSeleccionado(), !nv.isOculto());

        }
    }


    private void agregaBotonMaximizar()
    {
        this.jbMaximizar = new JButton("Maximizar Árbol");
        jbMaximizar.addActionListener(this.actionListener);
        jbMaximizar.setActionCommand(UI_JPanel_Parcial.MAXIMIZAR);
        this.panelSur.add(jbMaximizar);
        this.jbMaximizar.setEnabled(false);
    }
}
