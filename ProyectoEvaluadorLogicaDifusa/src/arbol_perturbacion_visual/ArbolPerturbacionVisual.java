package arbol_perturbacion_visual;


import arbolvisual.ArbolVisual;
import arbolvisual.NodoVisual;

import arbolvisual.ArbolVisual;

import java.awt.Color;
import java.awt.Graphics;

import java.util.Iterator;

import javax.swing.tree.DefaultTreeModel;

import modelo.Nodo_Perturbacion;

public class ArbolPerturbacionVisual extends ArbolVisual {
    private Paleta paleta = new Paleta();
    {
        this.paleta.addColor(new Color(255, 128, 128));
        this.paleta.addColor(new Color(255, 255, 128));
        this.paleta.addColor(new Color(128, 255, 128));

    }
    private Color colorLineasRelacionales = Color.magenta;

    protected class LienzoRelacional extends Lienzo {

        @Override
        public void paint(Graphics g) {
            // TODO Implement this method
           
            Iterator<NodoVisual> nodosVisuales = ArbolPerturbacionVisual.this.iteratorNodosVisuales();
            while (nodosVisuales.hasNext()) {
                NodoVisual origen = nodosVisuales.next();

                if (origen.isVisible()) {
                    Nodo_Perturbacion nodo = (Nodo_Perturbacion) origen.getNodoReal();
                    if (nodo.getImpacta() != null &&
                        ArbolPerturbacionVisual.this.getNodoVisual(nodo.getImpacta()).isVisible())

                    {
                        NodoVisual destino = ArbolPerturbacionVisual.this.getNodoVisual(nodo.getImpacta());
                        g.setColor(ArbolPerturbacionVisual.this.getColorLineasRelacionales());
                        g.drawLine(origen.getX() + ArbolPerturbacionVisual.this.getAnchoNodo() / 2,
                                   origen.getY() + ArbolPerturbacionVisual.this.getAltoNodo() / 2,
                                   destino.getX() + ArbolPerturbacionVisual.this.getAnchoNodo() / 2,
                                   destino.getY() + ArbolPerturbacionVisual.this.getAltoNodo() / 2);

                    }
                }


            }
            super.paint(g);
        }
    }

    public void setColorLineasRelacionales(Color colorLineasRelacionales) {
        this.colorLineasRelacionales = colorLineasRelacionales;
    }

    public Color getColorLineasRelacionales() {
        return colorLineasRelacionales;

    }

    public ArbolPerturbacionVisual(DefaultTreeModel arbol, int anchoNodo, int altoNodo) {
        // TODO Implement this method
        super(arbol, anchoNodo, altoNodo);
        this.setLienzo(new LienzoRelacional());
    }

    public ArbolPerturbacionVisual(DefaultTreeModel defaultTreeModel) {
        super(defaultTreeModel);
        this.setLienzo(new LienzoRelacional());
    }

    public ArbolPerturbacionVisual() {
        super();
        this.setLienzo(new LienzoRelacional());
    }

    @Override
    protected void dibujaNodoVisual(NodoVisual nodoVisual, Graphics g) {
        // TODO Implement this method
        Nodo_Perturbacion nodo = (Nodo_Perturbacion) nodoVisual.getNodoReal();

        if (this.paleta != null) {
            if (!nodo.getEtiqueta().isCero()) {
                Color color = this.paleta.getColor(nodo.getEtiqueta().getNota());
                nodoVisual.setColorRelleno(color);
                nodoVisual.setColorBorde(color.darker());
            }
        }


        super.dibujaNodoVisual(nodoVisual, g);


    }


    public void setPaleta(Paleta paleta) {
        this.paleta = paleta;
    }

    public Paleta getPaleta() {
        return paleta;
    }

    public void algo() {

    }
}
