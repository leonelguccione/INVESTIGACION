package arbol_perturbacion_visual;

import javax.swing.tree.DefaultTreeModel;

public class ANoEvaluableVisual extends ArbolPerturbacionVisual
{
    public ANoEvaluableVisual()
    {
        super();
    }

    public ANoEvaluableVisual(DefaultTreeModel defaultTreeModel)
    {
        super(defaultTreeModel);
    }

    public ANoEvaluableVisual(DefaultTreeModel defaultTreeModel, int i, int i1)
    {
        super(defaultTreeModel, i, i1);
    }

    @Override
    public void verificaOcultos()
    {
        // TODO Implement this method
    }
}
