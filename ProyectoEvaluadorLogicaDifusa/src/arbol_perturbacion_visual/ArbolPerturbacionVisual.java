package arbol_perturbacion_visual;


import arbolVisual.ArbolVisual;
import arbolVisual.NodoVisual;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.tree.DefaultTreeModel;

import modelo.Nodo_Perturbacion;

public class ArbolPerturbacionVisual extends ArbolVisual
{
    private Paleta paleta = new Paleta();
    {
        this.paleta.addColor(new Color(255, 128, 128));
        this.paleta.addColor(new Color(255, 255, 128));
        this.paleta.addColor(new Color(128, 255, 128));
    }

    public ArbolPerturbacionVisual(DefaultTreeModel arbol, int anchoNodo, int altoNodo)
    {
        // TODO Implement this method
        super(arbol, anchoNodo, altoNodo);

    }

    public ArbolPerturbacionVisual(DefaultTreeModel defaultTreeModel)
    {
        super(defaultTreeModel);
    }

    public ArbolPerturbacionVisual()
    {
        super();
    }

    @Override
    protected void dibujaNodoVisual(NodoVisual nodoVisual, Graphics g)
    {
        // TODO Implement this method
        if (this.paleta != null)
        {
            Nodo_Perturbacion nodo = (Nodo_Perturbacion) nodoVisual.getNodoReal();
            if (!nodo.getEtiqueta().isCero())
            {
                Color color = this.paleta.getColor(nodo.getEtiqueta().getNota());
                nodoVisual.setColorRelleno(color);
                nodoVisual.setColorBorde(color.darker());
            }
        }
        super.dibujaNodoVisual(nodoVisual, g);
    }


    public void setPaleta(Paleta paleta)
    {
        this.paleta = paleta;
    }

    public Paleta getPaleta()
    {
        return paleta;
    }
}
