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
        } else if (!this.getEtiqueta().isValid())
            aux++;
        return aux;
    }

    /**Determina si un árbol es semejante a otro.
     * La semejanza está determinada por las siguientes condiciones:
     * - ambos null
     * - la igualdad entre los campos dato->idDato de cada nodo correspondiente
     * - la misma estructura de árbol.
     * @param otraRaiz
     * @return
     * precondición: otraRaiz != null
     */
    public boolean isSemejante(Nodo_Perturbacion otraRaiz)
    {
        boolean resultado = false;
        if (otraRaiz != null) //otraRaiz (principal) distinto de null
        {
            //Si los dos nodos referencian al mismo objeto, entonces son semejantes. Semejante a sí mismo.
            if (this == otraRaiz)
                resultado = true;
            //si no referencian al mismo objeto

            else
            {
                //si sus id de dato contienen el mismo texto
                if (this.getDato().getIdDato().equals(otraRaiz.getDato().getIdDato()))
                { //si no tienen la misma cantidad de hijos NO son semejantes
                    if (this.getChildCount() != otraRaiz.getChildCount())
                    {
                        resultado = false;
                    }
                    //si es rama entonces
                    else
                    { //si tienen la misma cantidad de hijos
                        if (this.esHoja()) //si ambos nodos son hojas
                            resultado = true;
                        else
                        //si ambos son ramas con igual cantidad de hijos, entonces debo verificar la semenjanza de cada hijo
                        {
                            Enumeration hijos_propios = this.children();
                            Enumeration hijos_otraRaiz = otraRaiz.children();
                            Nodo_Perturbacion proximo_otraRaiz;
                            Nodo_Perturbacion proximo_propios;
                            boolean semejanzaparcial = true;
                            while (hijos_propios.hasMoreElements() && hijos_otraRaiz.hasMoreElements() &&
                                   semejanzaparcial)
                            {
                                proximo_otraRaiz = (Nodo_Perturbacion) hijos_otraRaiz.nextElement();
                                proximo_propios = (Nodo_Perturbacion) hijos_propios.nextElement();
                                semejanzaparcial = proximo_propios.isSemejante(proximo_otraRaiz);
                            }
                            resultado = semejanzaparcial;
                        }
                    }
                }
            }
        }
        return resultado;
    }

    public void suma(Nodo_Perturbacion sumando)
    {
        this.getEtiqueta().suma(sumando.getEtiqueta());
        if (!this.esHoja())
        {
            Enumeration hijos_propios = this.children();
            Enumeration hijos_sumando = sumando.children();
            Nodo_Perturbacion proximo_sumando;
            Nodo_Perturbacion proximo_propios;
            while (hijos_propios.hasMoreElements() && hijos_sumando.hasMoreElements())
            {
                proximo_sumando = (Nodo_Perturbacion) hijos_sumando.nextElement();
                proximo_propios = (Nodo_Perturbacion) hijos_propios.nextElement();
                proximo_propios.suma(proximo_sumando);
            }
        }
    }


    public void resta(Nodo_Perturbacion sustraendo)
    {
        this.getEtiqueta().resta(sustraendo.getEtiqueta());
        if (!this.esHoja())
        {
            Enumeration hijos_propios = this.children();
            Enumeration hijos_sustraendo = sustraendo.children();
            Nodo_Perturbacion proximo_sustraendo;
            Nodo_Perturbacion proximo_propios;
            while (hijos_propios.hasMoreElements() && hijos_sustraendo.hasMoreElements())
            {
                proximo_sustraendo = (Nodo_Perturbacion) hijos_sustraendo.nextElement();
                proximo_propios = (Nodo_Perturbacion) hijos_propios.nextElement();
                proximo_propios.resta(proximo_sustraendo);
            }
        }
    }

    public void multiplica(double factor)
    {
        this.getEtiqueta().multiplica(factor);
        if (!this.esHoja())
        {
            Enumeration hijos_propios = this.children();
            Nodo_Perturbacion proximo_propios;
            while (hijos_propios.hasMoreElements())
            {
                proximo_propios = (Nodo_Perturbacion) hijos_propios.nextElement();
                proximo_propios.multiplica(factor);
            }
        }
    }

    public void dividir(double divisor)
    {
        this.getEtiqueta().dividir(divisor);
        if (!this.esHoja())
        {
            Enumeration hijos_propios = this.children();
            Nodo_Perturbacion proximo_propios;
            while (hijos_propios.hasMoreElements())
            {
                proximo_propios = (Nodo_Perturbacion) hijos_propios.nextElement();
                proximo_propios.dividir(divisor);
            }
        }
    }
    
    
}


