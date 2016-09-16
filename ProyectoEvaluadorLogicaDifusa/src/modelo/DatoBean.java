/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.beans.PropertyChangeSupport;

import java.io.Serializable;

/**
 *
 * @author leonel
 * Contiene un identificador y una etiqueta
 * Se utilizará como información de cada nodo del arbol evaluador
 */
public class DatoBean implements Cloneable, Serializable
{
    @SuppressWarnings("compatibility:4653495925617027892")
    private static final long serialVersionUID = -5905493094872796833L;

    private String idDato;
    private EtiquetaBean etiquetaBean;

    public Object clone() throws CloneNotSupportedException
    {
        DatoBean aux = (DatoBean)super.clone();
        aux.idDato = this.idDato;
        return aux;
    }

    /**
     * Get the value of etiquetaBeanProperty
     *
     * @return the value of etiquetaBeanProperty
     */
    public EtiquetaBean getEtiquetaBean()
    {
        return etiquetaBean;
    }

    /**
     * Set the value of etiquetaBeanProperty
     *
     * @param etiquetaBeanProperty new value of etiquetaBeanProperty
     */
    public void setEtiquetaBean(EtiquetaBean value)
    {

        etiquetaBean = value;

    }

    private PropertyChangeSupport propertySupport;

    public DatoBean()
    {
        propertySupport = new PropertyChangeSupport(this);
    }

    /**
     * @param id dato. Es el identificador/descriptor del nodo del árbol.
     */
    public DatoBean(String id)
    {
        etiquetaBean = new EtiquetaBean();
        idDato = id;   
    }
    
    public DatoBean(String id, EtiquetaBean e)
    {
        super();
        this.idDato = id;
        this.etiquetaBean = e;
    }

    public String getIdDato()
    {
        return idDato;
    }

    public void setIdDato(String value)
    {
        idDato = value;
    }

    @Override
    public String toString()
    {
        
        if(!getEtiquetaBean().isCero())
            return (getIdDato()+"\n"+getEtiquetaBean().toString());
        else
            return getIdDato();
    }

}
