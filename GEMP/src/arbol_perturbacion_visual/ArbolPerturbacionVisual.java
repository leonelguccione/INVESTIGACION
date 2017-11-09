package arbol_perturbacion_visual;


import arbolvisual.ArbolVisual;
import arbolvisual.NodoVisual;

import java.awt.Color;
import java.awt.Graphics;

import java.util.Enumeration;
import java.util.Iterator;

import javax.swing.event.TreeModelEvent;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;

import modelo.NodoPerturbacion;
import modelo.RelacionImpacto;


public abstract class ArbolPerturbacionVisual extends ArbolVisual
{
    private Paleta paleta = new Paleta();
    {
        this.paleta.addColor(new Color(255, 128, 128));
        this.paleta.addColor(new Color(255, 255, 128));
        this.paleta.addColor(new Color(128, 255, 128));
    }

    private Color colorLineasRelacionalesOrigen = Color.cyan;
    private Color colorLineasRelacionalesDestino = Color.red;

    private void  borraNodoDestinoEnRelaciones(NodoPerturbacion nodo_actual, NodoPerturbacion nodo_a_borrar)
    {
        
        RelacionImpacto rel=nodo_actual.getRelacionImpacto(nodo_a_borrar);
        if(rel!=null) nodo_actual.removeImpacto(rel);
        Enumeration hijos = nodo_actual.children();
     while (hijos.hasMoreElements())
        {
            NodoPerturbacion hijo = (NodoPerturbacion) hijos.nextElement();
            this.borraNodoDestinoEnRelaciones(hijo, nodo_a_borrar);
        }
    }

    public class ArbolPerturbacionListener extends ArbolVisual.ArbolListener
    {

        @Override
        public void treeNodesRemoved(TreeModelEvent e)
        {
            NodoPerturbacion nodo_a_borrar = (NodoPerturbacion) e.getChildren()[0];
            NodoPerturbacion raiz = (NodoPerturbacion) ArbolPerturbacionVisual.this.getModel().getRoot();
            ArbolPerturbacionVisual.this.borraNodoDestinoEnRelaciones(raiz, nodo_a_borrar);
            super.treeNodesRemoved(e);
        }
    };

    protected class LienzoRelacional extends Lienzo
    {

        @Override
        public void paint(Graphics g)
        {
            // TODO Implement this method

            Iterator<NodoVisual> nodosVisuales = ArbolPerturbacionVisual.this.iteratorNodosVisuales();
            while (nodosVisuales.hasNext())
            {
                NodoVisual origen = nodosVisuales.next();

                if (origen.isVisible())
                {
                    if (!origen.isOculto() || ArbolPerturbacionVisual.this.isMuestraNodosOcultos())
                    {
                        NodoPerturbacion nodo = (NodoPerturbacion) origen.getNodoReal();
                        NodoPerturbacion impactado;

                        Iterator<RelacionImpacto> impactados = nodo.iteratorImpactos();

                        while (impactados.hasNext())
                        {
                            impactado = impactados.next().getNodo();
                            NodoVisual destino = ArbolPerturbacionVisual.this.getNodoVisual(impactado);
                            if (destino.isVisible() &&
                                (!destino.isOculto() || ArbolPerturbacionVisual.this.isMuestraNodosOcultos()))

                            {
                                Color colorOrigen;
                                Color colorDestino;
                                if (origen.isOculto())
                                    colorOrigen =
                                        UtilGraphics.getColorAlpha(ArbolPerturbacionVisual.this
                                                                   .colorLineasRelacionalesOrigen, 32);
                                else
                                    colorOrigen = ArbolPerturbacionVisual.this.colorLineasRelacionalesOrigen;
                                if (destino.isOculto())
                                    colorDestino =
                                        UtilGraphics.getColorAlpha(ArbolPerturbacionVisual.this
                                                                   .colorLineasRelacionalesDestino, 32);
                                else
                                    colorDestino = ArbolPerturbacionVisual.this.colorLineasRelacionalesDestino;

                                UtilGraphics.lineaDegrade(g,
                                                          origen.getX() +
                                                          ArbolPerturbacionVisual.this.getAnchoNodo() / 2,
                                                          origen.getY() +
                                                          ArbolPerturbacionVisual.this.getAltoNodo() / 2,
                                                          destino.getX() +
                                                          ArbolPerturbacionVisual.this.getAnchoNodo() / 2,
                                                          destino.getY() +
                                                          ArbolPerturbacionVisual.this.getAltoNodo() / 2, colorOrigen,
                                                          colorDestino, 100);
                            }
                        }
                    }
                }

            }
            super.paint(g);
        }
    }


    public ArbolPerturbacionVisual(DefaultTreeModel arbol, int anchoNodo, int altoNodo)
    {
        // TODO Implement this method
        super(arbol, anchoNodo, altoNodo);
        this.setLienzo(new LienzoRelacional());
        this.arbolListener = new ArbolPerturbacionListener();
    }

    public ArbolPerturbacionVisual(DefaultTreeModel defaultTreeModel)
    {
        super(defaultTreeModel);
        this.setLienzo(new LienzoRelacional());
        this.arbolListener = new ArbolPerturbacionListener();
    }

    public ArbolPerturbacionVisual()
    {
        super();
        this.setLienzo(new LienzoRelacional());
        this.arbolListener = new ArbolPerturbacionListener();
    }

    @Override
    protected void dibujaNodoVisual(NodoVisual nodoVisual, Graphics g)
    {
        // TODO Implement this method
        NodoPerturbacion nodo = (NodoPerturbacion) nodoVisual.getNodoReal();

        if (this.paleta != null)
        {
            if (!nodo.isCero())
            {
                Color color = this.paleta.getColor(nodo.getNota());
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

    public void setColorLineasRelacionalesOrigen(Color colorLineasRelacionalesOrigen)
    {
        this.colorLineasRelacionalesOrigen = colorLineasRelacionalesOrigen;
    }

    public Color getColorLineasRelacionalesOrigen()
    {
        return colorLineasRelacionalesOrigen;
    }

    public void setColorLineasRelacionalesDestino(Color colorLineasRelacionalesDestino)
    {
        this.colorLineasRelacionalesDestino = colorLineasRelacionalesDestino;
    }

    public Color getColorLineasRelacionalesDestino()
    {
        return colorLineasRelacionalesDestino;
    }

    public abstract void verificaOcultos();


    @Override
    public void setModel(TreeModel arbol)
    {
        // TODO Implement this method
        super.setModel(arbol);
        this.verificaOcultos();
        this.recalcular();
    }


}
