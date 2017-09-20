package arbol_perturbacion_visual;

import java.util.Iterator;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import modelo.NodoPerturbacion;
import modelo.NodoPerturbacionEvaluable;

public class AEvaluableVisual extends ArbolPerturbacionVisual
{
    public AEvaluableVisual()
    {
        super();
    }

    public AEvaluableVisual(DefaultTreeModel defaultTreeModel)
    {
        super(defaultTreeModel);
    }

    public AEvaluableVisual(DefaultTreeModel defaultTreeModel, int i, int i1)
    {
        super(defaultTreeModel, i, i1);
    }

    @Override
    public void verificaOcultos()
    {
        Iterator<DefaultMutableTreeNode> it = this.iteratorDefaultMutableTreeNodes();
        NodoPerturbacionEvaluable nodo;

        while (it.hasNext())
        {
            nodo = (NodoPerturbacionEvaluable) it.next();

            this.getNodoVisual(nodo).setOculto(!nodo.isEvaluado());

        }
        this.repaint();

    }


    @Override
    public void setOculto(DefaultMutableTreeNode nodo, boolean valor)
    {
        // TODO Implement this method
        NodoPerturbacionEvaluable npe = (NodoPerturbacionEvaluable) nodo;
        npe.setEvaluado(!valor);
        super.setOculto(nodo, valor);

    }


}
