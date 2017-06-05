package UI;


import arbol_perturbacion_visual.ArbolPerturbacionVisual;

import java.awt.BorderLayout;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import modelo.ArbolPerturbacion;



public class UI_PanelArbol extends JPanel
{
    private ArbolPerturbacionVisual tree;
    private DefaultTreeModel dtmodel;
    private ArbolPerturbacion arbol_perturbacion; 
   


    public UI_PanelArbol()
    {
        super();
       
        removeAll();
        setLayout(new BorderLayout());
    }


    public void setArbol_perturbacion(ArbolPerturbacion arbol_perturbacion)
    {
        this.arbol_perturbacion = arbol_perturbacion;
    }

    public ArbolPerturbacion getArbol_perturbacion()
    {
        return arbol_perturbacion;
    }

    public void setModel(DefaultTreeModel modeloArbol)
    {
        this.dtmodel = modeloArbol;
    }
    
    public void crearRaiz()
    {
        removeAll();
        setLayout(new BorderLayout());

        //this.modeloarbol = new DefaultTreeModel(new DefaultMutableTreeNode(raiz));
        tree = new ArbolPerturbacionVisual(dtmodel);
        this.rectas();
        this.add(tree);
    }

    public void limpiar_panel()
    {
        removeAll();
        setLayout(new BorderLayout());
        this.dtmodel = null;
    }

    public void addMouseListener(MouseListener ml)
    {
        this.tree.addMouseListener(ml);
    }

    public DefaultMutableTreeNode getNodo()
    {
        return tree.getNodoSeleccionado();
    }

    public void agregar(String nombre)
    {
        DefaultMutableTreeNode nodoSeleccionado = tree.getNodoSeleccionado();
        if (nodoSeleccionado != null)
        {
            DefaultMutableTreeNode dmtn = new DefaultMutableTreeNode(nombre);
            //modeloarbol.insertNodeInto(new DefaultMutableTreeNode (nombre, nodoSeleccionado,nodoSeleccionado.getChildCount()));
            dtmodel.insertNodeInto(dmtn, nodoSeleccionado, nodoSeleccionado.getChildCount());
        }
    }

    public void borrar()
    {
        DefaultMutableTreeNode nodoSeleccionado = tree.getNodoSeleccionado();
        if (nodoSeleccionado != null)
        {
            if (!nodoSeleccionado.isRoot())
            {
                dtmodel.removeNodeFromParent(nodoSeleccionado);
            }
        }
    }

    private void rectas()
    {
        tree.setLineasRectas(true);
        repaint();
    }
}
