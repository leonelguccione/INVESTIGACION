package arbol_perturbacion_visual;


import arbolvisual.ArbolVisual;
import arbolvisual.NodoVisual;

import arbolvisual.ArbolVisual;

import java.awt.Color;
import java.awt.Graphics;

import java.util.Iterator;

import javax.swing.tree.DefaultTreeModel;

import modelo.NodoPerturbacion;
import modelo.RelacionImpacto;


public class ArbolPerturbacionVisual extends ArbolVisual
{
    private Paleta paleta = new Paleta();
    {
        this.paleta.addColor(new Color(255, 128, 128));
        this.paleta.addColor(new Color(255, 255, 128));
        this.paleta.addColor(new Color(128, 255, 128));
    }

    private Color colorLineasRelacionalesOrigen = Color.cyan;
    private Color colorLineasRelacionalesDestino = Color.red;


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
                    NodoPerturbacion nodo = (NodoPerturbacion) origen.getNodoReal();
                    NodoPerturbacion impactado;
                    
                                        Iterator<RelacionImpacto> impactados = nodo.IteratorImpactos();

                    while (impactados.hasNext())
                    {impactado=impactados.next().getNodo();
                        if (          ArbolPerturbacionVisual.this.getNodoVisual(impactado).isVisible())

                        {
                            NodoVisual destino = ArbolPerturbacionVisual.this.getNodoVisual(impactado);

                            UtilGraphics.lineaDegrade(g,
                                                      origen.getX() + ArbolPerturbacionVisual.this.getAnchoNodo() / 2,
                                                      origen.getY() + ArbolPerturbacionVisual.this.getAltoNodo() / 2,
                                                      destino.getX() + ArbolPerturbacionVisual.this.getAnchoNodo() / 2,
                                                      destino.getY() + ArbolPerturbacionVisual.this.getAltoNodo() / 2,
                                                      ArbolPerturbacionVisual.this.colorLineasRelacionalesOrigen,
                                                      ArbolPerturbacionVisual.this.colorLineasRelacionalesDestino,
                                                      1000);
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
    }

    public ArbolPerturbacionVisual(DefaultTreeModel defaultTreeModel)
    {
        super(defaultTreeModel);
        this.setLienzo(new LienzoRelacional());
    }

    public ArbolPerturbacionVisual()
    {
        super();
        this.setLienzo(new LienzoRelacional());
    }

    @Override
    protected void dibujaNodoVisual(NodoVisual nodoVisual, Graphics g)
    {
        // TODO Implement this method
        NodoPerturbacion nodo = (NodoPerturbacion) nodoVisual.getNodoReal();

        if (this.paleta != null)
        {
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

}
