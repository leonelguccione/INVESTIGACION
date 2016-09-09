/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Enumeration;

import javax.swing.tree.DefaultMutableTreeNode;


/**
 * Nodo del árbol, que almacena un datoBean.
 *
 * @author leonel
 */
public class Nodo_Perturbacion extends DefaultMutableTreeNode
{
    @SuppressWarnings("compatibility:-6181443248382898133")
    private static final long serialVersionUID = -945102238251152449L;

    public Nodo_Perturbacion(Object object, boolean b)
    {
        super(object, b);
    }

    public Nodo_Perturbacion(Object object)
    {
        super(object);
    }

    /**constructor
     * Crea un nodo de perturbación con un datoBean que contiene su idDato y la Etiqueta vacía.
     */
    public Nodo_Perturbacion(String idDato)
    {
        super();
        DatoBean dato = new DatoBean(idDato);
        setDato(dato);
    }

    /**
     * @return retorna la etiqueta. Ojbeto con los cuatro conjuntos difusos con sus valores
     */
    public EtiquetaBean getEtiqueta()
    {
        return getDato().getEtiquetaBean();
    }

    public DatoBean getDato()
    {
        return (DatoBean) getUserObject();
    }

    public boolean esHoja()
    {
        return isLeaf();
    }

    /**
     * establece el dato que contiene el nodo. Es un objeto de tipo DatoBean
     * @param dato Dato a guardar en el nodo.
     */
    public void setDato(DatoBean dato)
    {
        setUserObject(dato);
    }

    /**crea un nuevo nodo a partir del idNodo.
     * Agrega el datoBean como dato del nodo.
     * @param idNodo
     */
    public void crearNodo(String idNodo)
    {
        EtiquetaBean e = new EtiquetaBean();
        DatoBean datoBean = new DatoBean(idNodo, e);
        setDato(datoBean);
    }

    /**
     * Completa la etiqueta del nodo con el promedio de los valores de las etiquetas de los hijos.
     * En caso de ser una hoja, deja el nodo como está.
     */
    public void procesar_Nodo()
    {
        if (!esHoja())
        {
            EtiquetaBean etiqueta = getEtiqueta();
            Enumeration hijos = children();
            int cantidad_hijos = getChildCount();
            while (hijos.hasMoreElements())
            {
                Nodo_Perturbacion nodo_hijo = (Nodo_Perturbacion) hijos.nextElement();
                nodo_hijo.procesar_Nodo();
                etiqueta.suma(nodo_hijo.getEtiqueta());
            }
            etiqueta.dividir(cantidad_hijos);
        }
    }

    /**
     * @return Cantidad de hojas con valores de etiqueta no validos.
     */
    public int contarHojasInvalidas()
    {
        int aux = 0;
        if (!esHoja())
        {
            Enumeration hijos = children();
            while (hijos.hasMoreElements())
            {
                Nodo_Perturbacion nodo_hijo = (Nodo_Perturbacion) hijos.nextElement();
                aux += nodo_hijo.contarHojasInvalidas();
            }
        }
        else if (!this.getEtiqueta().isValid())
            aux++;
        return aux;
    }


}

