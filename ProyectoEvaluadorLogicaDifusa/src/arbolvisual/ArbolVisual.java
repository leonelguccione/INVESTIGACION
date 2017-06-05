package arbolvisual;

import arbol_perturbacion_visual.ArbolPerturbacionVisual;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

/**
 * Clase que representa un arbol visualmente, la logica del arbol se maneja desde un DefaultTreeModel
 * este clase representa visualmente cada nodo utilizando objetos de tipo <b>NodoVisual</b>.
 */
public class ArbolVisual extends JScrollPane {
    @SuppressWarnings("compatibility:3365911210634184474")
    private static final long serialVersionUID = -6792543746530702504L;
    private HashMap<DefaultMutableTreeNode, NodoVisual> hashmap = new HashMap<DefaultMutableTreeNode, NodoVisual>();
    private TreeModel arbol;
    private int anchoNodo, altoNodo, separacionHorizontal, separacionVertical, diametroControl, anchoArco, altoArco, anchoLinea;
    private Color colorFondo, colorTexto, colorLineas, colorRelleno, colorBorde, colorSeleccionado, colorControl;
    private DefaultMutableTreeNode nodoSeleccionado = null;
    private boolean lineasRectas = false;
    private int margenDerecho = 50;
    private int margenInferior = 0;
    private Lienzo lienzo = new Lienzo();
    private transient ArbolListener arbolListener = new ArbolListener();
    private transient MiMouseListener miMouseListener = new MiMouseListener();
    private ArrayList<ActionListener> actionListeners = new ArrayList<ActionListener>();
    public static final String NODO_SELECCIONADO = "NODO_SELECCIONADO";


    protected void setLienzo(ArbolVisual.Lienzo lienzo) {
        this.lienzo = lienzo;
        this.setViewportView(this.lienzo);
        this.lienzo.addMouseListener(this.miMouseListener);
    }

    protected ArbolVisual.Lienzo getLienzo() {
        return lienzo;
    }

    protected void setNodoSeleccionado(DefaultMutableTreeNode nodoSeleccionado) {
        this.nodoSeleccionado = nodoSeleccionado;
        for (int i = 0; i < this.actionListeners.size(); i++) {
            ActionEvent e = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, ArbolVisual.NODO_SELECCIONADO);
            this.actionListeners
                .get(i)
                .actionPerformed(e);
        }
    }

    protected class Lienzo extends JComponent {
        /**
         * Metodo sobreescrito que es llamado cuando el ArbolVisual se dibuja (heredado de Component).
         * @param g Objeto de tipo Graphics donde se dibuja el ArbolVisual
         */
        @Override
        public void paint(Graphics g) {
            // TODO Implement this method
            super.paint(g);
            if (ArbolVisual.this.arbol != null) {
                if (ArbolVisual.this.colorFondo != null) {
                    g.setColor(ArbolVisual.this.colorFondo);
                    g.fillRect(0, 0, this.getWidth(), this.getHeight());
                }
                if (ArbolVisual.this.getColorLineas() != null)
                    ArbolVisual.this.dibujaLineas(g, (DefaultMutableTreeNode) ArbolVisual.this.arbol.getRoot());

                ArbolVisual.this.dibujaImagenes(g);
                if (ArbolVisual.this.getColorTexto() != null)
                    ArbolVisual.this.dibujaTextos(g);
            }
        }
    };

    public void addActionListener(ActionListener al) {
        this.actionListeners.add(al);

    }

    public boolean removeActionListener(ActionListener al) {
        return this.actionListeners.remove(al);
    }

    private class MiMouseListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent mouseEvent) {
            if (ArbolVisual.this.arbol != null) {
                DefaultMutableTreeNode nodo = null;
                Point p = mouseEvent.getPoint();
                nodo = buscaControlNodo(p);
                if (nodo != null) {
                    NodoVisual nv = hashmap.get(nodo);
                    if (nv.isExpandido())
                        contraeNodo(nodo);
                    else
                        expandeNodo(nodo);

                } else {
                    nodo = buscaNodo(p);
                    if (nodo != null) {
                        if (getNodoSeleccionado() != null)
                            hashmap.get(getNodoSeleccionado()).setSeleccionado(false);
                        hashmap.get(nodo).setSeleccionado(true);
                        setNodoSeleccionado(nodo);
                    }
                }
                recalcular();
                lienzo.repaint();
            }
        }
    }

    private class ArbolListener implements TreeModelListener {
        @Override
        public void treeNodesInserted(TreeModelEvent e) {
            DefaultMutableTreeNode nodo = (DefaultMutableTreeNode) e.getChildren()[0];
            DefaultMutableTreeNode padre = (DefaultMutableTreeNode) nodo.getParent();
            crearNodoVisual(nodo);
            expandeNodo(padre);
            recalcular();
            lienzo.repaint();
        }

        @Override
        public void treeNodesRemoved(TreeModelEvent e) {
            DefaultMutableTreeNode nodo = (DefaultMutableTreeNode) e.getChildren()[0];
            borraNodosVisuales(nodo);
            recalcular();
            lienzo.repaint();
        }


        @Override
        public void treeNodesChanged(TreeModelEvent e) {
            // TODO Implement this method
        }

        @Override
        public void treeStructureChanged(TreeModelEvent e) {
            // TODO Implement this method
        }
    }


    /**
     * Constructor que crea un arbol visual con un DefaultTreeModel null<br>
     * Por defecto el color de fondo es null, el color del texto y las lineas es negro, la separacion horizontal es de 4 pixeles, y la separacion vertical de 16 pixeles.
     */
    public ArbolVisual() {
        this(null);
    }

    /**
     * Constructor que crea un arbol visual relaciondolo con un DefaultTreeModel<br>
     * Por defecto el color de fondo es null, el color del texto y las lineas es negro, la separacion horizontal es de 4 pixeles, y la separacion vertical de 16 pixeles.
     * @param arbol, el DefaultTreeModel con el que esta relacionado el arbol visual
     */
    public ArbolVisual(DefaultTreeModel arbol) {
        this(arbol, 100, 56);
    }

    public ArbolVisual(DefaultTreeModel arbol, int anchoNodo, int altoNodo) {
        this.setModel(arbol);
        this.colorFondo = null;
        this.colorTexto = Color.black;
        this.colorLineas = Color.black;
        this.colorRelleno = Color.lightGray;
        this.colorBorde = Color.black;
        this.colorSeleccionado = Color.yellow;
        this.colorControl = Color.white;
        this.anchoArco = 10;
        this.altoArco = 10;
        this.anchoLinea = 2;
        this.anchoNodo = anchoNodo;
        this.altoNodo = altoNodo;
        this.diametroControl = 15;
        this.setSeparacionHorizontal(4);
        this.setSeparacionVertical(16);
        this.setViewportView(this.lienzo);
        this.lienzo.addMouseListener(this.miMouseListener);


    }

    protected void dibujaNodoVisual(NodoVisual nodoVisual, Graphics g) {
        if (nodoVisual.isSeleccionado()) {
            g.setColor(nodoVisual.getColorSeleccionado());
            g.fillRoundRect(nodoVisual.getX(), nodoVisual.getY(), this.anchoNodo, this.altoNodo, this.anchoArco,
                            this.altoArco);
            g.setColor(nodoVisual.getColorBorde());
            g.fillRoundRect(nodoVisual.getX() + this.anchoLinea, nodoVisual.getY() + this.anchoLinea,
                            this.anchoNodo - this.anchoLinea * 2, this.altoNodo - this.anchoLinea * 2, this.anchoArco,
                            this.altoArco);
            g.setColor(nodoVisual.getColorRelleno());
            g.fillRoundRect(nodoVisual.getX() + this.anchoLinea * 2, nodoVisual.getY() + this.anchoLinea * 2,
                            this.anchoNodo - this.anchoLinea * 4, this.altoNodo - this.anchoLinea * 4, this.anchoArco,
                            this.altoArco);

        } else {
            g.setColor(nodoVisual.getColorBorde());
            g.fillRoundRect(nodoVisual.getX(), nodoVisual.getY(), this.anchoNodo, this.altoNodo, this.anchoArco,
                            this.altoArco);
            g.setColor(nodoVisual.getColorRelleno());
            g.fillRoundRect(nodoVisual.getX() + this.anchoLinea, nodoVisual.getY() + this.anchoLinea,
                            this.anchoNodo - this.anchoLinea * 2, this.altoNodo - this.anchoLinea * 2, this.anchoArco,
                            this.altoArco);
        }
    }

    protected Iterator<NodoVisual> iteratorNodosVisuales() {
        return this.hashmap
                   .values()
                   .iterator();
    }

    /**
     * Metodo que dibuja las imagenes en un Objeto Graphics
     * @param g Objeto Graphics donde se dibujaran las imagenes
     */
    private void dibujaImagenes(Graphics g) {
        Iterator<NodoVisual> nodosVisuales = this.iteratorNodosVisuales();
        NodoVisual nodovisualactual;

        while (nodosVisuales.hasNext()) {
            nodovisualactual = nodosVisuales.next();
            if (nodovisualactual.isVisible()) {
                this.dibujaNodoVisual(nodovisualactual, g);
                if (!nodovisualactual.isHoja()) {
                    g.setColor(this.colorControl);

                    g.fillOval(nodovisualactual.getX() + (this.anchoNodo - this.diametroControl) / 2,
                               nodovisualactual.getY() + this.altoNodo - this.diametroControl / 2, this.diametroControl,
                               this.diametroControl);
                    g.setColor(this.colorLineas);

                    g.drawOval(nodovisualactual.getX() + (this.anchoNodo - this.diametroControl) / 2,
                               nodovisualactual.getY() + this.altoNodo - this.diametroControl / 2, this.diametroControl,
                               this.diametroControl);
                    g.fillRect(nodovisualactual.getX() + (this.anchoNodo - this.diametroControl) / 2 + 2,
                               nodovisualactual.getY() + this.altoNodo, this.diametroControl - 3, 2);
                    if (!nodovisualactual.isExpandido()) {
                        g.fillRect(nodovisualactual.getX() + this.anchoNodo / 2 - 1,
                                   nodovisualactual.getY() + this.altoNodo - this.diametroControl / 2 + 2, 2,
                                   this.diametroControl - 3);
                    }
                }
            }
        }
    }


    /**
     * Metodo que dibuja los textos en un Objeto Graphics con el color indicado por setColorTexto, el color del texto es null, los textos no se dibujan
     * @param g Objeto Graphics donde se dibujaran los textos
     */
    private void dibujaTextos(Graphics g) {
        Iterator<NodoVisual> nodosVisuales = this.hashmap
                                                 .values()
                                                 .iterator();
        FontMetrics metrics = g.getFontMetrics(this.getFont());
        NodoVisual nodovisualactual;
        g.setColor(this.colorTexto);
        String eltexto;
        String[] arraytexto;
        int x, y;
        while (nodosVisuales.hasNext()) {
            nodovisualactual = nodosVisuales.next();
            if (nodovisualactual.isVisible())

            {
                eltexto = nodovisualactual.getNodoReal().toString();
                arraytexto = eltexto.split("\n");
                for (int i = 0; i < arraytexto.length; i++) {
                    x = nodovisualactual.getX() + (this.getAnchoNodo() - metrics.stringWidth(arraytexto[i])) / 2;
                    y = (nodovisualactual.getY() + (this.getAltoNodo() - metrics.getHeight() * arraytexto.length) / 2) +
                        metrics.getHeight() * i + metrics.getAscent();
                    g.drawString(arraytexto[i], x, y);
                }
            }
        }
    }

    /**
     * Metodo que dibuja las lineas del ArbolVisual en forma recursiva a partir de un nodo, en un objeto de tipo Graphics. Las lineas se dibujan con el color especificado por setColorLineas. Si el color de las lines es null, las lineas no se dibujan
     * @param g Objeto Graphics donde se dibujaran las lineas
     * @param nodo Objeto DefaultMutableTreeNode que indica el nodo a partir del cual se dibujaran las lineas. Para dibujar todas lineas, este nodo debe ser la raiz del arbol
     */
    private void dibujaLineas(Graphics g, DefaultMutableTreeNode nodo) {
        NodoVisual nodoVisualPadre = this.hashmap.get(nodo);
        g.setColor(this.colorLineas);
        if (nodoVisualPadre.isExpandido()) {
            Enumeration hijos = nodo.children();
            while (hijos.hasMoreElements()) {
                DefaultMutableTreeNode hijo = (DefaultMutableTreeNode) hijos.nextElement();
                NodoVisual nodoVisualHijo = this.hashmap.get(hijo);
                int x1 = nodoVisualPadre.getX() + this.anchoNodo / 2;
                int y1 = nodoVisualPadre.getY() + this.altoNodo;
                int x2 = nodoVisualHijo.getX() + this.anchoNodo / 2;
                int y2 = nodoVisualHijo.getY();
                if (this.isLineasRectas()) {
                    int ym = (y2 + y1) / 2;
                    g.drawLine(x1, y1, x1, ym);
                    g.drawLine(x1, ym, x2, ym);
                    g.drawLine(x2, ym, x2, y2);
                } else
                    g.drawLine(x1, y1, x2, y2);
                if (this.hashmap
                        .get(hijo)
                        .isExpandido())
                    dibujaLineas(g, hijo);
            }
        }
    }


    /**
     * Metodo para indicar cual sera el DefaultTreeModel con el cualse emparejara el ArbolVisual<br>
     * <b>Pre: </b> arbol debe ser diferente de null <br>.
     * @param arbol Objeto de tipo DefaultTreeModel con el cual se asociara el ArbolVisual
     */
    public void setModel(TreeModel arbol) {
        this.hashmap.clear();
        this.setNodoSeleccionado(null);
        if (this.arbol != null)
            this.arbol.removeTreeModelListener(this.arbolListener);
        this.arbol = arbol;
        if (this.arbol != null) {
            this.arbol.addTreeModelListener(this.arbolListener);
            DefaultMutableTreeNode raiz = (DefaultMutableTreeNode) arbol.getRoot();
            this.crearNodoVisual(raiz);
            this.recalcular();
        }
        this.repaint();
    }

    /**
     * Metodo que indica cual es el TreeModel con el cual esta emparejado el ArbolVisual.
     * @return Objeto de tipo TreeModel con el cual esta emparejado el ArbolVisual.
     */
    public TreeModel getModel() {
        return arbol;
    }

    /**
     * Metodo para indicar el ancho que tendra cada NodoVisual, deberia ser el mismo que el ancho de la imagen con la cual se representara el nodo.
     * @param anchoNodo ancho en pixeles que tendra cada NodoVisual
     */
    public void setAnchoNodo(int anchoNodo) {
        this.anchoNodo = anchoNodo;
        this.recalcular();
    }

    /**
     * Metodo que informa el ancho que tiene cada NodoVisual.
     * @return el ancho en pixeles de cada NodoVisual.
     */
    public int getAnchoNodo() {
        return anchoNodo;
    }

    /**
     * Metodo para indicar el alto que tendra cada NodoVisual, deberia ser el mismo que el alto de la imagen con la cual se representara el nodo.
     * @param altoNodo el alto en pixeles de cada NodoVisual
     */
    public void setAltoNodo(int altoNodo) {
        this.altoNodo = altoNodo;
        this.recalcular();
    }

    /**
     * Metodo que informa el alto que tiene cada NodoVisual.
     * @return el alto en pixeles de cada NodoVisual.
     */
    public int getAltoNodo() {
        return altoNodo;
    }

    /**
     * Metodo para indicar la separacion horizontal minima que habra entre cada NodoVisual.
     * @param separacionHorizontal Cantidad de pixeles que tendra como separacion minima horizontal cada NodoVisual
     */
    public void setSeparacionHorizontal(int separacionHorizontal) {
        this.separacionHorizontal = separacionHorizontal;
        this.recalcular();
    }

    /**
     * Metodo que informa la separacion horizontal minima que hay entre cada NodoVisual.
     * @return separacion minima horizontal que hay entre cada NodoVisual medida en pixeles
     */
    public int getSeparacionHorizontal() {
        return separacionHorizontal;
    }

    /**
     * Metodo para indicar la separacion vertical que habra entre cada NodoVisual.
     * @param separacionVertical separacion vertical que hay entre cada NodoVisual medida en pixeles
     */
    public void setSeparacionVertical(int separacionVertical) {
        this.separacionVertical = separacionVertical;
        this.recalcular();
    }

    /**
     * Metodo que informa la separacion vertical que hay entre cada NodoVisual.
     * @return separacion vertical que hay entre cada NodoVisual medida en pixeles
     */
    public int getSeparacionVertical() {
        return separacionVertical;
    }

    /**
     * Creo cada objeto de tipo NodoVisual en forma recursiva a partir de un DefaultMutableTreeNode
     * @param nodo Objeto de tipo DefaultMutableTreeNode que indica desde que nodo debe comenzar la creacion recursiva de los nodos visuales
     */
    private void crearNodoVisual(DefaultMutableTreeNode nodo) {
        NodoVisual nodoAux = new NodoVisual(nodo);
        nodoAux.setColorRelleno(this.colorRelleno);
        nodoAux.setColorBorde(this.colorBorde);
        nodoAux.setColorSeleccionado(this.colorSeleccionado);
        this.hashmap.put(nodo, nodoAux);
        if (!nodo.isLeaf()) {
            Enumeration hijos = nodo.children();
            while (hijos.hasMoreElements()) {
                this.crearNodoVisual((DefaultMutableTreeNode) hijos.nextElement());
            }
        }
    }

    /**
     * Calcula en forma recursiva los anchos reservados de cada NodoVisual, para que al expandir un nodo, sus hijos no se solapen con otro nodos
     * @param nodo Objeto de tipo DefaultMutableTreeNode a partir del cual se calculan los anchos reservados. Para calcular todos los anchos, este nodo debe ser la raiz del arbol.
     */
    private void calcularAnchosReservados(DefaultMutableTreeNode nodo) {
        NodoVisual nodoVisualActual = this.hashmap.get(nodo);
        if (nodo.isLeaf()) {
            nodoVisualActual.setAnchoreservado(this.getAnchoNodo() + this.getSeparacionHorizontal() * 2);
        } else {
            Enumeration hijos = nodo.children();
            int anchoAcumulado = 0;
            while (hijos.hasMoreElements()) {
                DefaultMutableTreeNode hijo = (DefaultMutableTreeNode) hijos.nextElement();
                NodoVisual nodoVisualHijo = this.hashmap.get(hijo);
                if (nodoVisualHijo.isExpandido())
                    calcularAnchosReservados(hijo);
                else
                    nodoVisualHijo.setAnchoreservado(this.getAnchoNodo() + this.getSeparacionHorizontal() * 2);
                anchoAcumulado += nodoVisualHijo.getAnchoreservado();
            }
            nodoVisualActual.setAnchoreservado(anchoAcumulado);
        }
    }

    /**
     * Expade un NodoVisual a partir de su DefaultMutableTreeNode asociado
     * @param nodo Objeto de tipo DefaultMutableTreeNode a cuyo NodoVisual asociado se desea expandir
     */
    private void expandeNodo(DefaultMutableTreeNode nodo) {
        NodoVisual nodoVisualActual = this.hashmap.get(nodo);
        Enumeration hijos = nodo.children();
        nodoVisualActual.setExpandido(true);
        while (hijos.hasMoreElements()) {
            DefaultMutableTreeNode hijo = (DefaultMutableTreeNode) hijos.nextElement();
            this.hashmap
                .get(hijo)
                .setVisible(true);
        }
    }

    /**
     * Contrae un NodoVisual a partir de su DefaultMutableTreeNode asociado
     * @param nodo Objeto de tipo DefaultMutableTreeNode a cuyo NodoVisual asociado se desea contraer
     */
    private void contraeNodo(DefaultMutableTreeNode nodo) {
        if (nodo.isNodeDescendant(this.getNodoSeleccionado())) {
            hashmap.get(getNodoSeleccionado()).setSeleccionado(false);
            hashmap.get(nodo).setSeleccionado(true);
            this.setNodoSeleccionado(nodo);
        }

        NodoVisual nodoVisualActual = this.hashmap.get(nodo);
        Enumeration hijos = nodo.children();
        nodoVisualActual.setExpandido(false);
        while (hijos.hasMoreElements()) {
            DefaultMutableTreeNode hijo = (DefaultMutableTreeNode) hijos.nextElement();
            NodoVisual nodoVisualHijo = this.hashmap.get(hijo);
            nodoVisualHijo.setVisible(false);
            if (nodoVisualHijo.isExpandido())
                this.contraeNodo(hijo);
        }
    }

    /**
     * Calcula las coordenadas de cada NodoVisual en forma recursiva a partir de un DefaultMutableTreeNode
     * @param nodo Objeto DefaultMutableTreeNode que indica cual es el primer nodo a partir del cual debe iniciarse el calculo, la primer llamada utilizará el nodo raiz
     * @param desplazamientoX parametro int que indica un desplazamiento horizantal, necesario para el correcto calculo de coordenadas
     */
    private void calcularCoordenadas(DefaultMutableTreeNode nodo, int desplazamientoX) {
        NodoVisual nodoVisualActual = this.hashmap.get(nodo);

        nodoVisualActual.setY((this.getAltoNodo() + this.getSeparacionVertical() * 2) * nodo.getLevel() +
                              this.getSeparacionVertical());
        nodoVisualActual.setX(desplazamientoX + nodoVisualActual.getAnchoreservado() / 2 - this.getAnchoNodo() / 2);
        if (!nodo.isLeaf()) {
            Enumeration hijos = nodo.children();
            int desplazamientoAcumulado;
            desplazamientoAcumulado = desplazamientoX;
            while (hijos.hasMoreElements()) {
                DefaultMutableTreeNode hijo = (DefaultMutableTreeNode) hijos.nextElement();
                calcularCoordenadas(hijo, desplazamientoAcumulado);
                desplazamientoAcumulado += this.hashmap
                                               .get(hijo)
                                               .getAnchoreservado();
            }
        }
    }

    /**
     * Verifica si en el punto indicado hay un control de un NodoVisual
     * @param p Objeto de tipo Point en el que deseamos verificar si hay un control de nodo
     * @return Objeto de tipo DefaultMutableTreeNode que esta en el punto indicado, si en el punto indicado no hay un control de nodo, se retorna null
     */
    private DefaultMutableTreeNode buscaControlNodo(Point p) {
        Iterator<NodoVisual> nodosVisuales = this.hashmap
                                                 .values()
                                                 .iterator();
        DefaultMutableTreeNode nodo = null;
        while (nodosVisuales.hasNext()) {
            NodoVisual nv = nodosVisuales.next();
            if (nv.isVisible() && !nv.isHoja() &&
                p.distance(nv.getX() + this.anchoNodo / 2, nv.getY() + this.altoNodo) <= this.diametroControl / 2) {
                nodo = nv.getNodoReal();
            }
        }
        return nodo;
    }

    /**
     * Verifica si en el punto indicado hay un NodoVisual
     * @param p Objeto de tipo Point en el que deseamos verificar si hay un nodo
     * @return Objeto de tipo DefaultMutableTreeNode , si en el punto indicado no hay un nodo, se retorna null
     */
    private DefaultMutableTreeNode buscaNodo(Point p) {
        DefaultMutableTreeNode nodo = null;

        nodo = this.buscaNodoRectangular(p);
        return nodo;
    }

    private DefaultMutableTreeNode buscaNodoRectangular(Point p) {
        Iterator<NodoVisual> nodosVisuales = this.hashmap
                                                 .values()
                                                 .iterator();
        DefaultMutableTreeNode nodo = null;
        while (nodosVisuales.hasNext()) {
            NodoVisual nv = nodosVisuales.next();
            if (nv.isVisible() && nv.getX() <= p.getX() && nv.getX() + this.anchoNodo >= p.getX() &&
                nv.getY() <= p.getY() && nv.getY() + this.altoNodo >= p.getY()) {
                nodo = nv.getNodoReal();
            }
        }
        return nodo;
    }

    /*  private DefaultMutableTreeNode buscaNodoCircular(Point p)
    {
        Iterator<NodoVisual> nodosVisuales = this.hashmap.values().iterator();
        DefaultMutableTreeNode nodo = null;
        while (nodosVisuales.hasNext())
        {
            NodoVisual nv = nodosVisuales.next();
            if (nv.isVisible() &&
                p.distance(nv.getX() + this.anchoNodo / 2, nv.getY() + this.altoNodo / 2) <= this.anchoNodo / 2)
                nodo = nv.getNodoReal();
        }
        return nodo;

    } */


    /**
     * realiza un calculo completo de las coordenadas de los nodos visuales
     */
    private void recalcular() {
        if (this.arbol != null) {
            this.calcularAnchosReservados((DefaultMutableTreeNode) this.arbol.getRoot());
            this.calcularCoordenadas((DefaultMutableTreeNode) this.arbol.getRoot(), 0);
            this.calcularDimension();
        }

    }


    /**
     * Devuelve un String con informacion del ArbolVisual.
     * @return Un String con informacion del ArbolVisual
     */
    @Override
    public String toString() {
        Iterator<NodoVisual> nodosVisuales = this.hashmap
                                                 .values()
                                                 .iterator();
        String aux = "";
        NodoVisual nv;
        while (nodosVisuales.hasNext()) {
            nv = nodosVisuales.next();
            aux =
                aux + nv.getNodoReal().toString() + " " + nv.getAnchoreservado() + " X= " + nv.getX() + " Y= " +
                nv.getY();
            aux = aux + "\n";
        }
        return aux;
    }


    /**
     * Metodo para indicar el color que tendra el fondo del ArbolVisual, si es null, no se dibujara ningun color de fondo.
     * @param colorFondo Objeto de tipo Color para indicar el color de fondo
     */
    public void setColorFondo(Color colorFondo) {
        this.colorFondo = colorFondo;
    }

    /**
     * Metodo para informar el color que tiene el fondo del ArbolVisual, si es null, no se dibuja ningun color de fondo.
     * @return Objeto de tipo Color para indicar el color de fondo
     */
    public Color getColorFondo() {
        return colorFondo;
    }

    /**
     * Metodo para indicar el color que tendran los textos del ArbolVisual, si es null, no se dibujara ningun texto.
     * @param colorTexto Objeto de tipo Color para indicar el color de los textos
     */
    public void setColorTexto(Color colorTexto) {
        this.colorTexto = colorTexto;
    }

    /**
     * Metodo para informar el color que tienen los textos del ArbolVisual, si es null, no se dibua ningun texto.
     * @return Objeto de tipo Color para indicar el color de los textos
     */
    public Color getColorTexto() {
        return colorTexto;
    }

    /**
     * Metodo para indicar el color que tendran las lineas del ArbolVisual, si es null, no se dibujara ninguna linea.
     * @param colorLineas Objeto de tipo Color para indicar el color de las lineas
     */
    public void setColorLineas(Color colorLineas) {
        this.colorLineas = colorLineas;
    }

    /**
     * Metodo para informar el color que tienen las lineas del ArbolVisual, si es null, no se dibuja ninguna linea.
     * @return Objeto de tipo Color para indicar el color de las lineas
     */
    public Color getColorLineas() {
        return colorLineas;
    }

    /**
     * Metodo para indicar el ancho en pixeles que tendra el control de cada NodoVisual.
     * @param anchoControl cantidad de pixeles que tendra el ancho del control
     */
    public void setDiametroControl(int diametroControl) {
        this.diametroControl = diametroControl;
    }

    /**
     * Metodo para informar el ancho en pixeles que tiene el control de cada NodoVisual.
     * @return Cantidad de pixeles que tiene de ancho el control
     */
    public int getDiametroControl() {
        return diametroControl;
    }


    /**
     * Metodo para obtener el NodoVisual asociado a un DefaulMutableTreeNode.
     * @param nodo Objeto de tipo DefaulMutableTreeNode del cual se quiere obtener el NodoVisual asociado.
     * @return Objeto de tipo NodoVisual asociado al nodo pasado como parmetro. Si no el nodo no tiene ningun nodo visual asociados, se retorna null
     */
    public NodoVisual getNodoVisual(DefaultMutableTreeNode nodo) {
        return this.hashmap.get(nodo);
    }

    /**
     * Metodo para obtener el DefaultMutableTreeNode que esta seleccionado en este momento.
     * @return Objeto de tipo DefaultMutableTreeNode que esta seleccionado. Si no hay ningun nodo seleccionado se retorna null.
     */
    public DefaultMutableTreeNode getNodoSeleccionado() {
        return nodoSeleccionado;
    }

    /**
     * Metodo para indicar si las lineas que se dibujan en el ArbolVisual son oblicuas o son lineas horizontales y verticales.
     * @param lineasRectas true para lineas horizontales y verticales <br> false para lineas oblicuas
     */
    public void setLineasRectas(boolean lineasRectas) {
        this.lineasRectas = lineasRectas;
    }

    /**
     * Metodo para informar si las lineas que se dibujan en el ArbolVisual son oblicuas o son lineas horizontales y verticales.
     * @return true si las lineas son horizontales y verticales <br> false si las lineas son oblicuas
     */
    public boolean isLineasRectas() {
        return lineasRectas;
    }

    /**
     * Borra en forma recursiva el NodoVisual asociado a un DefaultMutableTreeNode y de sus hijos.
     * @param nodo Objeto de tipo DefaultMutableTreeNode al cual se le borrara su NodoNisual asociado y a sus hijos.
     */
    private void borraNodosVisuales(DefaultMutableTreeNode nodo) {
        this.hashmap.remove(nodo);
        if (nodo.isNodeDescendant(this.getNodoSeleccionado()))
            this.setNodoSeleccionado(null);
        Enumeration hijos = nodo.children();
        while (hijos.hasMoreElements()) {
            DefaultMutableTreeNode hijo = (DefaultMutableTreeNode) hijos.nextElement();
            this.borraNodosVisuales(hijo);
        }
    }

    /**
     * Indica la cantidad de pixeles que tendra el ArbolVisual como margen derecho.
     * @param margenDerecho entero que representa la cantidad de pixeles que tendra el ArbolVisual como margen derecho
     */
    public void setMargenDerecho(int margenDerecho) {
        this.margenDerecho = margenDerecho;
    }

    /**
     * Informa la cantidad de pixeles que tendra el ArbolVisual como margen derecho.
     * @return Cantidad de pixeles que tiene el ArbolVisual como margen derecho
     */
    public int getMargenDerecho() {
        return margenDerecho;
    }

    /**
     * Indica la cantidad de pixeles que tendra el ArbolVisual como margen inferior.
     * @param margenInferior entero que representa la cantidad de pixeles que tendra el ArbolVisual como margen inferior
     */
    public void setMargenInferior(int margenInferior) {
        this.margenInferior = margenInferior;
    }

    /**
     * Informa la cantidad de pixeles que tendra el ArbolVisual como margen inferior.
     * @return Cantidad de pixeles que tiene el ArbolVisual como margen inferior
     */
    public int getMargenInferior() {
        return margenInferior;
    }

    /**
     * Recalcula la dimension del ArbolVisual
     */
    private void calcularDimension() {
        int ancho = 0;
        int alto = 0;
        Iterator<NodoVisual> it = this.hashmap
                                      .values()
                                      .iterator();
        NodoVisual actual = null;
        while (it.hasNext()) {
            actual = it.next();
            if (actual.getX() > ancho)
                ancho = actual.getX();
            if (actual.getY() > alto)
                alto = actual.getY();
        }
        ancho += this.anchoNodo + this.margenDerecho;
        alto += this.altoNodo + this.margenInferior;
        this.lienzo.setPreferredSize(new Dimension(ancho, alto));
        this.lienzo.revalidate();
    }

    /**
     * Igual que getNosoSeleccionado pero devuelve un Object.
     * @return nodo seleccionado
     */
    public Object getLastSelectedPathComponent() {
        return this.getNodoSeleccionado();
    }

    /**
     * Devuelve el TreePath correspondiente al nodo seleccionado.
     * @return Objecto de tipo TreePath correspondiente al nodo seleccionado
     */

    public TreePath getSelectionPath() {
        TreePath r = null;
        if (this.getNodoSeleccionado() != null)
            r = new TreePath(this.getNodoSeleccionado().getPath());
        return r;
    }


    /**
     * Registra un MouseListener pasadocomo parmatetro
     * @param mouselistener objeto de tipo MosueListener que se quiere registrar
     */
    @Override
    public synchronized void addMouseListener(MouseListener mouselistener) {
        // TODO Implement this method
        super.addMouseListener(mouselistener);
        this.lienzo.addMouseListener(mouselistener);
    }

    /**
     * Desvincula un MouseListener pasado como parametro, si el MouseLisetener pasado como parametro no estaba registrado, no se realiza ninguna accion
     * @param mouselistener objeto de tipo MosueListener que se quiere desvincular
     */
    @Override
    public synchronized void removeMouseListener(MouseListener mouselistener) {
        // TODO Implement this method
        super.removeMouseListener(mouselistener);
        this.lienzo.removeMouseListener(mouselistener);
    }

    /**
     * Expande todos los nodos en el TreePath pasado como parametro.
     * @param treepath Objecto de tipo TreePath al cual se le expandiran todos sus nodos
     */
    public void expandPath(TreePath treepath) {
        Object[] nodos = treepath.getPath();
        for (int i = 0; i < nodos.length; i++)
            this.expandeNodo((DefaultMutableTreeNode) nodos[i]);
        lienzo.repaint();
    }


    public void setAnchoArco(int anchoArco) {
        this.anchoArco = anchoArco;
    }

    public int getAnchoArco() {
        return anchoArco;
    }

    public void setAltoArco(int altoArco) {
        this.altoArco = altoArco;
    }

    public int getAltoArco() {
        return altoArco;
    }

    public void setAnchoLinea(int anchoLinea) {
        this.anchoLinea = anchoLinea;
    }

    public int getAnchoLinea() {
        return anchoLinea;
    }

    public void setColorRelleno(Color colorRelleno) {
        this.colorRelleno = colorRelleno;
    }

    public Color getColorRelleno() {
        return colorRelleno;
    }

    public void setColorBorde(Color colorBorde) {
        this.colorBorde = colorBorde;
    }

    public Color getColorBorde() {
        return colorBorde;
    }

    public void setColorSeleccionado(Color colorSeleccionado) {
        this.colorSeleccionado = colorSeleccionado;
    }

    public Color getColorSeleccionado() {
        return colorSeleccionado;
    }

    public void setColorControl(Color colorControl) {
        this.colorControl = colorControl;
    }

    public Color getColorControl() {
        return colorControl;
    }


}
