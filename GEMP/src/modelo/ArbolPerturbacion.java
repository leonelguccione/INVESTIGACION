/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import Excepciones.NoCompletoException;
import Excepciones.NotSemejanteException;
import Excepciones.RaizNulaException;

import arbolvisual.NodoVisual;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import java.util.ArrayList;

import java.util.HashMap;

import java.util.Iterator;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 * Clase que implementa el Arbol de Perturbación.
 * El arbol de perturbacion se utiliza para evaluar.
 *
 **/
public class ArbolPerturbacion implements Serializable, Cloneable
{
    @SuppressWarnings("compatibility:-4146374243641147900")
    private static final long serialVersionUID = 6425751349863975298L;

    /**Identificador del árbol. No repetirlo
     */
    private String nombre;

    /**descripción del árbol.
     */
    private String descripcion;

    /**Nodo raíz del árbol de perturbación
     */
    private NodoPerturbacion raiz;

    /**Model de la raíz del árbol de perturbación
     */
    DefaultTreeModel treeModel = null;


    public ArbolPerturbacion(String idArbol, String descripcion)
    {
        this.setNombre(idArbol);
        this.setDescripcion(descripcion);
    }


    /**
     * Agrega un nuevo nodo al arbol como hijo del nodo denominado padre. Si el árbol está vacío
     * lo agrega como raiz.
     * @param padre nodo sobre el cual se agregará el nuevo nodo
     * @param nuevoHijo nodo que se agregará al arbol
     */
    public void agregarNodo(NodoPerturbacion padre, NodoPerturbacion nuevoHijo)
    {
        if (isVacio())
        {
            this.raiz = padre;
            treeModel = new DefaultTreeModel(getRaiz());
        } else
        {
            //TODO: revisar esto !!
            treeModel.insertNodeInto(nuevoHijo, padre, padre.getChildCount());
            //padre.add(nuevoHijo);
        }
    }

    public void agregarNodo(NodoPerturbacion nodo_seleccionado_padre, String id_nuevo_nodo)
    {
        NodoPerturbacion nuevo_nodo = new NodoPerturbacion(id_nuevo_nodo);
        agregarNodo(nodo_seleccionado_padre, nuevo_nodo);

    }

   

    public boolean isVacio()
    {
        return (getRaiz() == null);
    }

    public void procesar() throws NoCompletoException
    {
        NodoPerturbacionEvaluable nodoRaiz = (NodoPerturbacionEvaluable) this.getRaiz();
        if (nodoRaiz.contarHojasInvalidas() != 0)
            throw new NoCompletoException("Hay hojas no validas o vacías");
        else
            nodoRaiz.procesar_Nodo();
    }

    public boolean isCompleto()
    {
        NodoPerturbacionEvaluable nodoRaiz = (NodoPerturbacionEvaluable) this.getRaiz();
        return (nodoRaiz.contarHojasInvalidas() == 0);
    }


    public NodoPerturbacion getRaiz()
    {
        return raiz;
    }


    /**Establece la raiz del árbol
     * Establece el Model con dicha raíz
     * @param raiz
     */
    public void setRaiz(NodoPerturbacion raiz)
    {
        this.raiz = raiz;
        treeModel = new DefaultTreeModel(getRaiz());
    }

    /**establece la raíz de un nuevo árbol de perturbación
     * @param idNodo identificador del nodo, es el idDato del DatoBean
     */
    public void setRaiz(String idNodo)
    {
        NodoPerturbacion np = new NodoPerturbacion(idNodo);
        this.setRaiz(np);
    }

    @Override
    public String toString()
    {
        return getNombre();
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String idArbol)
    {
        this.nombre = idArbol;
    }

    public String getDescripcion()
    {
        return descripcion;
    }

    public void setDescripcion(String descripcion)
    {
        this.descripcion = descripcion;
    }

    public DefaultTreeModel getTreeModel()
    {
        return treeModel;
    }


    /**
     * @return
     */
    public byte[] serializar()
    {
        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        ObjectOutputStream os;
        try
        {
            os = new ObjectOutputStream(bs);
            os.writeObject(this);
            os.close();
        } catch (IOException e)
        {
        }
        byte[] bytes = bs.toByteArray(); // devuelve byte[]
        return bytes;

    }

    public static ArbolPerturbacion deserializar(byte[] bytes)
    {
        ByteArrayInputStream bs = new ByteArrayInputStream(bytes);
        ArbolPerturbacion arbol_recuperado = null;
        ObjectInputStream is;
        try
        {
            is = new ObjectInputStream(bs);
            arbol_recuperado = (ArbolPerturbacion) is.readObject();
            //is.readObject();
            is.close();
        } catch (IOException e)
        {
        } catch (ClassNotFoundException e)
        {
        }
        return arbol_recuperado;
    }

    /**Determina si el árbol tiene la misma estructura que otroArbol.
     * Compara las estructuras (el mismo formato de árbol), que cada nodo ubicado en
     * el lugar semejante tenga el mismo identificador del dato.
     * @param otroArbol
     * @return true en caso de ser semejante, false en caso contrario
     */
    public boolean isSemejante(ArbolPerturbacion otroArbol)
    {
        boolean nombreIgual = this.getNombre().equals(otroArbol.getNombre());
        boolean descripcionIgual = this.getDescripcion().equals(otroArbol.getDescripcion());
        boolean rtaParcial = nombreIgual && descripcionIgual;
        if (rtaParcial == true)
        {
            rtaParcial = this.getRaiz().isSemejante(otroArbol.getRaiz());
        }
        return rtaParcial;
    }

    /**
     * Devuelve un clon del Arbol de Pertubación
     * @return
     */
    @Override
    public ArbolPerturbacion clone()
    {
        // TODO Implement this method
        return ArbolPerturbacion.deserializar(this.serializar());
    }
    
    
    public ArbolPerturbacion toEvaluable()
    {ArbolPerturbacion nuevo=new ArbolPerturbacion(this.getNombre(),this.getDescripcion());
     HashMap<NodoPerturbacion, NodoPerturbacionEvaluable> hashmap = new HashMap<NodoPerturbacion, NodoPerturbacionEvaluable> ();
     NodoPerturbacionEvaluable raizEvaluada=new NodoPerturbacionEvaluable(this.getRaiz(),hashmap);
     nuevo.setRaiz(raizEvaluada);
     Iterator <NodoPerturbacion> iterator_nodos=hashmap.keySet().iterator();
     
     
     
     
     while(iterator_nodos.hasNext())
     {
      NodoPerturbacion n=iterator_nodos.next();
     Iterator<RelacionImpacto>it_relaciones=n.iteratorImpactos();
         while(it_relaciones.hasNext()) 
         {
             RelacionImpacto rel=it_relaciones.next();
             hashmap.get(n).addImpacto(hashmap.get(rel.getNodo()), rel.getValor());
             
             }
        
               
         
         }
     
     
     
     
     
     return nuevo;
        
        
        }

    public void suma(ArbolPerturbacion sumando) throws NotSemejanteException
    {
        if (!this.isSemejante(sumando))
            throw new NotSemejanteException("Imposible sumar, los arboles no son semejantes");
        else
        {
            this.getRaiz().suma(sumando.getRaiz());
        }
    }

    public void resta(ArbolPerturbacion sustraendo) throws NotSemejanteException
    {
        if (!this.isSemejante(sustraendo))
            throw new NotSemejanteException("Imposible restar, los arboles no son semejantes");
        else
        {
            this.getRaiz().resta(sustraendo.getRaiz());
        }
    }

    public void multiplica(double factor)
    {
        this.getRaiz().multiplica(factor);
    }

    public void dividir(double divisor) throws ArithmeticException
    {
        this.getRaiz().dividir(divisor);
    }

    public static ArbolPerturbacion promedio(ArrayList<ArbolPerturbacion> lista) throws ArithmeticException,
                                                                                          NotSemejanteException
    {
        ArbolPerturbacion resultado = null;
        if (lista != null && lista.size() != 0)
        {
            resultado = lista.get(0).clone();
            for (int i = 1; i < lista.size(); i++)
                resultado.suma(lista.get(i));
            resultado.dividir(lista.size());
        }
        return resultado;
    }

    public boolean isCorregido()
    {
        return (!this.getRaiz().isCero());
    }

    public double getPorcentajeCorreccion() throws RaizNulaException
    {
        NodoPerturbacionEvaluable nodoRaiz = (NodoPerturbacionEvaluable) this.getRaiz();
        if (this.raiz == null)
            throw new RaizNulaException("La raiz del arbol es nula");
        double resultado = 0;
        resultado = 1 - ((double) nodoRaiz.contarHojasInvalidas() / (double) nodoRaiz.contarNodosCorregibles());
        resultado = resultado * 100;
        return resultado;
    }

    public String detalle(NodoPerturbacion nodo)
    {
        String cadena = "";
        for (int i = 0; i < nodo.getLevel(); i++)
            cadena = cadena + "|";
        cadena = cadena + "-"+nodo.toString();
        if(!nodo.esHoja())
            for (int i = 0; i < nodo.getChildCount(); i++) 
                cadena=cadena+"\n"+this.detalle((NodoPerturbacion) nodo.getChildAt(i));
        
        return cadena;


    }
    public String detalleCompleto()
    {
        return detalle(this.getRaiz());
        }

}
