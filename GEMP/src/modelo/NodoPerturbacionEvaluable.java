package modelo;

import java.util.Enumeration;
import java.util.HashMap;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class NodoPerturbacionEvaluable extends NodoPerturbacion
{

    private boolean evaluado = true;


    public void setEvaluado(boolean evaluado)
    {
        this.evaluado = evaluado;

        if (!evaluado && !this.esHoja())
        {
            for (int i = 0; i < this.getChildCount(); i++)
            {
                NodoPerturbacionEvaluable hijo = (NodoPerturbacionEvaluable) this.getChildAt(i);
                hijo.setEvaluado(false);
            }
        }
    }

    public boolean isEvaluado()
    {
        return evaluado;
    }

    public NodoPerturbacionEvaluable(String string)
    {
        super(string);
    }

    public NodoPerturbacionEvaluable(Object object)
    {
        super(object);
    }

    public NodoPerturbacionEvaluable(Object object, boolean b)
    {
        super(object, b);
    }

    public NodoPerturbacionEvaluable(NodoPerturbacion base)
    {

        this(base.getUserObject());
        if (!base.esHoja())
            for (int i = 0; i < base.getChildCount(); i++)
            {
                this.add(new NodoPerturbacionEvaluable((NodoPerturbacion) base.getChildAt(i)));
            }
    }


    public NodoPerturbacionEvaluable(NodoPerturbacion base,
                                     HashMap<NodoPerturbacion, NodoPerturbacionEvaluable> hashmap)
    {

        this(base.getUserObject());
        hashmap.put(base, this);

        if (!base.esHoja())
            for (int i = 0; i < base.getChildCount(); i++)
            {
                this.add(new NodoPerturbacionEvaluable((NodoPerturbacion) base.getChildAt(i), hashmap));
            }
    }

    public boolean tieneHijoEvaluable()
    {
        boolean respuesta = false;
        int tam = this.getChildCount();
        int i = 0;
        while (i < tam && !respuesta)
        {
            NodoPerturbacionEvaluable hijo = (NodoPerturbacionEvaluable) this.getChildAt(i);
            respuesta = hijo.isEvaluado();
            i++;
        }
        return respuesta;
    }

    /**
     * Completa la etiqueta del nodo con el promedio de los valores de las etiquetas de los hijos.
     * En caso de ser una hoja, deja el nodo como está¡.
     */
    public void procesar_Nodo()
    {
        if (this.tieneHijoEvaluable())
        {
            EtiquetaBean etiqueta = getEtiqueta();
            Enumeration hijos = children();
            int cantidad_hijos_ev = this.contarHijosEvaluados();
            while (hijos.hasMoreElements())
            {
                NodoPerturbacionEvaluable nodo_hijo = (NodoPerturbacionEvaluable) hijos.nextElement();
                nodo_hijo.procesar_Nodo();
                etiqueta.suma(nodo_hijo.getEtiqueta());
            }
            etiqueta.dividir(cantidad_hijos_ev);
        }
    }

    protected int contarHijosEvaluados()
    {
        Enumeration<NodoPerturbacionEvaluable> hijos = children();
        int cont = 0;
        while (hijos.hasMoreElements())
        {
            NodoPerturbacionEvaluable nodo_hijo = hijos.nextElement();
            if (nodo_hijo.isEvaluado())
                cont++;
        }
        return cont;

    }

    /**
     * @return Cantidad de hojas con valores de etiqueta no validos.
     */
    public int contarHojasInvalidas()
    {
        int aux = 0;
        if (this.isEvaluado())
        {
            if (this.tieneHijoEvaluable())
            {
                Enumeration hijos = children();
                while (hijos.hasMoreElements())
                {
                    NodoPerturbacionEvaluable nodo_hijo = (NodoPerturbacionEvaluable) hijos.nextElement();
                    aux += nodo_hijo.contarHojasInvalidas();
                }
            } else if (!this.getEtiqueta().isValid())
                aux++;
        }
        return aux;
    }

    public int contarNodosCorregibles()
    {
        int aux = 0;
        if (this.isEvaluado())
        {
            if (this.tieneHijoEvaluable())
            {
                Enumeration hijos = children();
                while (hijos.hasMoreElements())
                {
                    NodoPerturbacionEvaluable nodo_hijo = (NodoPerturbacionEvaluable) hijos.nextElement();
                    aux += nodo_hijo.contarNodosCorregibles();
                }
            } else
                aux++;
        }
        return aux;
    }


}
