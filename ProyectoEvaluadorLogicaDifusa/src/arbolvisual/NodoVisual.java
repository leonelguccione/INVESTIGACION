package arbolvisual;

import java.awt.Color;

import java.io.Serializable;

import javax.swing.tree.DefaultMutableTreeNode;

import modelo.NodoPerturbacionEvaluable;

/**
 * Clase que representa un nodo visual dentro del arbol visual, a cada DefaultMutableTreeNode le corresponde un nodo visual.
 */
public class NodoVisual implements Serializable, Cloneable
{
    @SuppressWarnings("compatibility:894136378009397574")
    private static final long serialVersionUID = 1601652017252495439L;


    private DefaultMutableTreeNode nodoReal;
    private boolean expandido;
    private boolean visible;
    private boolean seleccionado;
    private boolean oculto;
    private int x, y, anchoreservado;
    private Color colorRelleno;
    private Color colorBorde;
    private Color colorSeleccionado;
    private Color colorLinea;
    private Color colorTexto;
    private Color colorControl;


    public void setColorControl(Color colorControl)
    {
        this.colorControl = colorControl;
    }

    public Color getColorControl()
    {
        return colorControl;
    }

    public void setColorTexto(Color colorTexto)
    {
        this.colorTexto = colorTexto;
    }

    public Color getColorTexto()
    {
        return colorTexto;
    }


    public void setColorLinea(Color colorLinea)
    {
        this.colorLinea = colorLinea;
    }

    public Color getColorLinea()
    {
        return colorLinea;
    }

    public void setOculto(boolean oculto)
    {
        this.oculto = oculto;
        if (oculto)
            this.setAlpha(32);
        else
            this.setAlpha(255);

    }

    public boolean isOculto()
    {
        return oculto;
    }

    public void setColorRelleno(Color colorRelleno)
    {
        this.colorRelleno = colorRelleno;
    }

    public Color getColorRelleno()
    {
        return colorRelleno;
    }

    public void setColorBorde(Color colorBorde)
    {
        this.colorBorde = colorBorde;
    }

    public Color getColorBorde()
    {
        return colorBorde;
    }

    public void setColorSeleccionado(Color colorSeleccionado)
    {
        this.colorSeleccionado = colorSeleccionado;
    }

    public Color getColorSeleccionado()
    {
        return colorSeleccionado;
    }

    /**
     * @param nodoReal Crea un nodo visual relacionado con un DefalutMutableTreNode
     */
    public NodoVisual(DefaultMutableTreeNode nodoReal)
    {
        this.nodoReal = nodoReal;
        this.setVisible(true);
        this.setOculto(false);

        this.expandido = !this.nodoReal.isLeaf();
        ;
    }


    /**
     * Metodo para contraer o expandir un nodo visual siempre y cuando el nodo no sea hoja.
     * Si el nodo es hoja, no se realiza ninugna operacion.
     * @param expandido true para que el nodo este expandido, false si el nodo esta contraido
     */
    public void setExpandido(boolean expandido)
    {
        if (!this.isHoja())
            this.expandido = expandido;
    }


    /**
     * Indica si el nodo esta contraido o expandido.
     * @return true si el nodo esta expandido, false si esta contraido
     */
    public boolean isExpandido()
    {
        return expandido;
    }


    /**
     * Indica si el nodo es o no una hoja.
     * @return true si el nodo es hoja, false si no lo es
     */
    public boolean isHoja()
    {
        return this.nodoReal.isLeaf();
    }

    /**
     * Metodo que cambia el estado de la coordenada x del nodo visual.
     * @param x coordenada x del nodo visual
     */
    public void setX(int x)
    {
        this.x = x;
    }

    /**
     * Metodo que retorna la coordenada x del nodo visual.
     * @return cooredenada x del nodo visual
     */
    public int getX()
    {
        return x;
    }

    /**
     * Metodo que cambia el estado de la coordenada y del nodo visual.
     * @param y coordenada y del nodo visual
     */
    public void setY(int y)
    {
        this.y = y;
    }

    /**
     * Metodo que retorna la coordenada y del nodo visual.
     * @return cooredenada y del nodo visual
     */
    public int getY()
    {
        return y;
    }


    /**
     * Metodo que cambia el ancho que ocupa el nodo visual, en caso de que el nodo este expandido, el nodo debe reservar el ancho necesario para que sus nodos hijos no se solapen con otros nodos.
     * @param anchoreservado cantidad de pixeles que ocupara el nodo
     */
    public void setAnchoreservado(int anchoreservado)
    {
        this.anchoreservado = anchoreservado;
    }

    /**
     * Metodo que retorna el ancho que tiene reservado el nodo.
     * @return cantidad de pixeles que reserva el nodo
     */
    public int getAnchoreservado()
    {
        return anchoreservado;
    }


    /**
     * Metodo para indicar el DefaultMutableTreeNode que esta emparejado con el nodo visual.
     * @param nodoReal DefaultMutableTreeNode con el que se emparejara el nodo visual
     */
    public void setNodoReal(DefaultMutableTreeNode nodoReal)
    {
        this.nodoReal = nodoReal;
    }

    /**
     * Metodo para obtener el DefaulMutableTreeNode con el que esta emparejado el nodo visual.
     * @return DefaulMutableTreeNode con el que esta emparejado el nodo visual
     */
    public DefaultMutableTreeNode getNodoReal()
    {
        return nodoReal;
    }

    /**
     * Metodo para hacer visible o invisible el nodo.
     * @param visible true para hacer visible al nodo, false para ocultarlo
     */
    public void setVisible(boolean visible)
    {
        this.visible = visible;
    }

    /**
     * Metodo que indica si el nodo esta visible u oculto.
     * @return true si el nodo esta visible, false si esta oculto.
     */
    public boolean isVisible()
    {
        return visible;
    }

    /**
     * Metodo para indicar si el nodo esta o no seleccionado.
     * @param seleccionado true para indicar que el nodo esta seleccionado, false para indicar que no lo esta
     */
    public void setSeleccionado(boolean seleccionado)
    {
        this.seleccionado = seleccionado;
    }

    /**
     * Metodo que informa si el nodo esta o no seleccionado.
     * @return true si el nodo esta seleccionado, false si no lo esta.
     */
    public boolean isSeleccionado()
    {
        return seleccionado;
    }


    @Override
    protected NodoVisual clone()
    {
        // TODO Implement this method
        NodoVisual resp = null;
        try
        {
            resp = (NodoVisual) super.clone();
        } catch (CloneNotSupportedException e)
        {
        }
        return resp;
    }


    private void setAlpha(int alpha)
    {
        Color c = this.getColorBorde();
        if (c != null)
            this.setColorBorde(new Color(c.getRed(), c.getGreen(), c.getBlue(), alpha));
        c = this.getColorRelleno();
        if (c != null)
            this.setColorRelleno(new Color(c.getRed(), c.getGreen(), c.getBlue(), alpha));
        c = this.getColorLinea();
        if (c != null)
            this.setColorLinea(new Color(c.getRed(), c.getGreen(), c.getBlue(), alpha));
        c = this.getColorTexto();
        if (c != null)
            this.setColorTexto(new Color(c.getRed(), c.getGreen(), c.getBlue(), alpha));
        c = this.getColorSeleccionado();
        if (c != null)
            this.setColorSeleccionado(new Color(c.getRed(), c.getGreen(), c.getBlue(), alpha));
        c = this.getColorControl();
        if (c != null)
            this.setColorControl(new Color(c.getRed(), c.getGreen(), c.getBlue(), alpha));
    }
}
