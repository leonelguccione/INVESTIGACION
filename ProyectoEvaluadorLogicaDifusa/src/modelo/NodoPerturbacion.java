/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.Enumeration;

import java.util.Iterator;
import java.util.LinkedHashSet;

import javax.swing.tree.DefaultMutableTreeNode;


/**
 * Nodo del Ã¡rbol, que almacena un datoBean.
 *
 * @author leonel
 */
public class NodoPerturbacion extends DefaultMutableTreeNode implements IConjuntoDifuso
{
    @SuppressWarnings("compatibility:-6181443248382898133")
    private static final long serialVersionUID = -945102238251152449L;
    private LinkedHashSet<RelacionImpacto> impacta = new LinkedHashSet<RelacionImpacto>();
  
    

    public void addImpacta(NodoPerturbacion np, double valor)
    {
    
        this.impacta.add(new RelacionImpacto(np, valor));

    }

    public void addImpacta(NodoPerturbacion np)
    {
        this.addImpacta(np, 0.0);

    }

    public Iterator<RelacionImpacto> iteratorImpactos()
    {
        return this.impacta.iterator();

    }


    public NodoPerturbacion(Object object, boolean b)
    {
        super(object, b);
    }

    public NodoPerturbacion(Object object)
    {
        super(object);
    }

    /**constructor
     * Crea un nodo de perturbaciÃ³n con un datoBean que contiene su idDato y la Etiqueta vacÃ­a.
     */
    public NodoPerturbacion(String idDato)
    {
        super();
        DatoBean dato = new DatoBean(idDato);
        setUserObject(dato);
    }

    /**
     * @return retorna la etiqueta. Ojbeto con los cuatro conjuntos difusos con sus valores
     */
    private EtiquetaBean getEtiqueta()
    {
        return getDato().getEtiquetaBean();
    }

    private DatoBean getDato()
    {
        return (DatoBean) getUserObject();
    }

    public boolean esHoja()
    {
        return isLeaf();
    }


    /**crea un nuevo nodo a partir del idNodo.
     * Agrega el datoBean como dato del nodo.
     * @param idNodo
     */
    public void crearNodo(String idNodo)
    {
        EtiquetaBean e = new EtiquetaBean();
        DatoBean datoBean = new DatoBean(idNodo, e);
        setUserObject(datoBean);
        
    }

    /**
     * Completa la etiqueta del nodo con el promedio de los valores de las etiquetas de los hijos.
     * En caso de ser una hoja, deja el nodo como estÃ¡.
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
                NodoPerturbacion nodo_hijo = (NodoPerturbacion) hijos.nextElement();
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
                NodoPerturbacion nodo_hijo = (NodoPerturbacion) hijos.nextElement();
                aux += nodo_hijo.contarHojasInvalidas();
            }
        } else if (!this.getEtiqueta().isValid())
            aux++;
        return aux;
    }


    /**
     * @return Cantidad de hojas
     */
    public int contarHojas()
    {
        int aux = 0;
        if (!esHoja())
        {
            Enumeration hijos = children();
            while (hijos.hasMoreElements())
            {
                NodoPerturbacion nodo_hijo = (NodoPerturbacion) hijos.nextElement();
                aux += nodo_hijo.contarHojas();
            }
        } else
            aux++;
        return aux;
    }


    /**Determina si un Ã¡rbol es semejante a otro.
     * La semejanza estÃ¡ determinada por las siguientes condiciones:
     * - ambos null
     * - la igualdad entre los campos dato->idDato de cada nodo correspondiente
     * - la misma estructura de Ã¡rbol.
     * @param otraRaiz
     * @return
     * precondiciÃ³n: otraRaiz != null
     */
    public boolean isSemejante(NodoPerturbacion otraRaiz)
    {
        boolean resultado = false;
        if (otraRaiz != null) //otraRaiz (principal) distinto de null
        {
            //Si los dos nodos referencian al mismo objeto, entonces son semejantes. Semejante a sÃ­ mismo.
            if (this == otraRaiz)
                resultado = true;
            //si no referencian al mismo objeto


            else
            {
                //si sus id de dato contienen el mismo texto
                if (this.getDato()
                        .getIdDato()
                        .equals(otraRaiz.getDato().getIdDato()))
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
                            NodoPerturbacion proximo_otraRaiz;
                            NodoPerturbacion proximo_propios;
                            boolean semejanzaparcial = true;
                            while (hijos_propios.hasMoreElements() && hijos_otraRaiz.hasMoreElements() &&
                                   semejanzaparcial)
                            {
                                proximo_otraRaiz = (NodoPerturbacion) hijos_otraRaiz.nextElement();
                                proximo_propios = (NodoPerturbacion) hijos_propios.nextElement();
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

    public void suma(NodoPerturbacion sumando)
    {

        this.getEtiqueta().suma(sumando.getEtiqueta());
        
        if (!this.esHoja())
        {
            Enumeration hijos_propios = this.children();
            Enumeration hijos_sumando = sumando.children();
            NodoPerturbacion proximo_sumando;
            NodoPerturbacion proximo_propios;
            while (hijos_propios.hasMoreElements() && hijos_sumando.hasMoreElements())
            {
                proximo_sumando = (NodoPerturbacion) hijos_sumando.nextElement();
                proximo_propios = (NodoPerturbacion) hijos_propios.nextElement();
                proximo_propios.suma(proximo_sumando);
            }
        }
    }


    public void resta(NodoPerturbacion sustraendo)
    {
        this.getEtiqueta().resta(sustraendo.getEtiqueta());
        
        if (!this.esHoja())
        {
            Enumeration hijos_propios = this.children();
            Enumeration hijos_sustraendo = sustraendo.children();
            NodoPerturbacion proximo_sustraendo;
            NodoPerturbacion proximo_propios;
            while (hijos_propios.hasMoreElements() && hijos_sustraendo.hasMoreElements())
            {
                proximo_sustraendo = (NodoPerturbacion) hijos_sustraendo.nextElement();
                proximo_propios = (NodoPerturbacion) hijos_propios.nextElement();
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
            NodoPerturbacion proximo_propios;
            while (hijos_propios.hasMoreElements())
            {
                proximo_propios = (NodoPerturbacion) hijos_propios.nextElement();
                proximo_propios.multiplica(factor);
            }
        }
    }

    public void dividir(double divisor) throws ArithmeticException
    {
        if (divisor != 0)
        {
            this.getEtiqueta().dividir(divisor);
            
            if (!this.esHoja())
            {
                Enumeration hijos_propios = this.children();
                NodoPerturbacion proximo_propios;
                while (hijos_propios.hasMoreElements())
                {
                    proximo_propios = (NodoPerturbacion) hijos_propios.nextElement();
                    proximo_propios.dividir(divisor);
                }
            }
        } else
            throw new ArithmeticException("divisiÃ³n por cero");
    }

    @Override
    public String toString()
    {

        return this.getDato().toString();
    }

    /*  @Override
    public String toString() {

        String cartel;
        if (this.getImpacta() != null)

            cartel = this.getDato().toString() + " impacta a " + this.getImpacta();
        else
            cartel = super.toString();
        return cartel;
    } */

    public String getIdDato()
    {
        return this.getDato().getIdDato();
    }

    @Override
    public double getDesconocido()
    {
        // TODO Implement this method
        return this.getEtiqueta().getDesconocido();
    }

    @Override
    public void setDesconocido(double desconocido)
    {
        this.getEtiqueta().setDesconocido(desconocido);
        
    }

    @Override
    public double getParcialmenteConocido()
    {
        // TODO Implement this method
        return this.getEtiqueta().getParcialmenteConocido();
    }

    @Override
    public void setParcialmenteConocido(double parcialmente)
    {
        this.getEtiqueta().setParcialmenteConocido(parcialmente);
        
    }

    @Override
    public double getConocido()
    {
        return this.getEtiqueta().getConocido();
    }

    @Override
    public void setConocido(double conocido)
    {
        this.getEtiqueta().setConocido(conocido);
        
    }

    @Override
    public double getAprendido()
    {
        return this.getEtiqueta().getAprendido();
    }

    @Override
    public void setAprendido(double aprendido)
    {
        this.getEtiqueta().setAprendido(aprendido);
        
    }

    @Override
    public boolean isValid()
    {
        // TODO Implement this method
        return this.getEtiqueta().isValid();
    }

    @Override
    public boolean isCero()
    {
        // TODO Implement this method
        return this.getEtiqueta().isCero();
    }

    @Override
    public void inicializar()
    {
        this.getEtiqueta().inicializar();
    }

    @Override
    public double getNota()
    {
        // TODO Implement this method
        return this.getEtiqueta().getNota();
    }

    @Override
    public void setEtiquetaDesdeNota(double nota) throws Exception
    {
        this.getEtiqueta().setEtiquetaDesdeNota(nota);
        
    }


    


   

}


