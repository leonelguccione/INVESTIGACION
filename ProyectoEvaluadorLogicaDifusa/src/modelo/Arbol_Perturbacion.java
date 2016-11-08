/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import java.util.ArrayList;

import javax.swing.tree.DefaultTreeModel;

/**
 * Clase que implementa el Arbol de Perturbación.
 * El arbol de perturbacion se utiliza para evaluar.
 *
 **/
public class Arbol_Perturbacion implements Serializable, Cloneable {
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
    private Nodo_Perturbacion raiz;

    /**Model de la raíz del árbol de perturbación
     */
    DefaultTreeModel treeModel = null;


    public Arbol_Perturbacion(String idArbol, String descripcion) {
        this.setNombre(idArbol);
        this.setDescripcion(descripcion);
    }


    /**
     * Agrega un nuevo nodo al arbol como hijo del nodo denominado padre. Si el árbol está vacío
     * lo agrega como raiz.
     * @param padre nodo sobre el cual se agregará el nuevo nodo
     * @param nuevoHijo nodo que se agregará al arbol
     */
    public void agregarNodo(Nodo_Perturbacion padre, Nodo_Perturbacion nuevoHijo) {
        if (isVacio()) {
            this.raiz = padre;
            treeModel = new DefaultTreeModel(getRaiz());
        } else {
            //TODO: revisar esto !!
            treeModel.insertNodeInto(nuevoHijo, padre, padre.getChildCount());
            //padre.add(nuevoHijo);
        }
    }

    public void agregarNodo(Nodo_Perturbacion nodo_seleccionado_padre, String id_nuevo_nodo) {
        Nodo_Perturbacion nuevo_nodo = new Nodo_Perturbacion(id_nuevo_nodo);
        agregarNodo(nodo_seleccionado_padre, nuevo_nodo);

    }

    public Nodo_Perturbacion buscarNodo(Nodo_Perturbacion nodo) {
        // TODO Implement this method
        return null;
    }

    public Nodo_Perturbacion buscarNodo(String idDato) {
        // TODO Implement this method
        return null;
    }

    public Nodo_Perturbacion extraerNodo(Nodo_Perturbacion nodo) {
        // TODO Implement this method
        return null;
    }

    public boolean isVacio() {
        return (getRaiz() == null);
    }

    public void procesar() throws Exception {
        if (getRaiz().contarHojasInvalidas() != 0)
            throw new Exception("Hay hojas no validas o vacías");
        else
            getRaiz().procesar_Nodo();
    }

    public boolean isCompleto() {
        return (getRaiz().contarHojasInvalidas() == 0);
    }


    public Nodo_Perturbacion getRaiz() {
        return raiz;
    }


    /**Establece la raiz del árbol
     * Establece el Model con dicha raíz
     * @param raiz
     */
    public void setRaiz(Nodo_Perturbacion raiz) {
        this.raiz = raiz;
        treeModel = new DefaultTreeModel(getRaiz());
    }

    /**establece la raíz de un nuevo árbol de perturbación
     * @param idNodo identificador del nodo, es el idDato del DatoBean
     */
    public void setRaiz(String idNodo) {
        Nodo_Perturbacion np = new Nodo_Perturbacion(idNodo);
        this.setRaiz(np);
    }

    @Override
    public String toString() {
        return getNombre();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String idArbol) {
        this.nombre = idArbol;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public DefaultTreeModel getTreeModel() {
        return treeModel;
    }


    /**
     * @return
     */
    public byte[] serializar() {
        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        ObjectOutputStream os;
        try {
            os = new ObjectOutputStream(bs);
            os.writeObject(this);
            os.close();
        } catch (IOException e) {
        } finally {
            byte[] bytes = bs.toByteArray(); // devuelve byte[]
            return bytes;
        }
    }

    public static Arbol_Perturbacion deserializar(byte[] bytes) {
        ByteArrayInputStream bs = new ByteArrayInputStream(bytes);
        Arbol_Perturbacion arbol_recuperado = null;
        ObjectInputStream is;
        try {
            is = new ObjectInputStream(bs);
            arbol_recuperado = (Arbol_Perturbacion) is.readObject();
            //is.readObject();
            is.close();
        } catch (IOException e) {
        } catch (ClassNotFoundException e) {
        }
        return arbol_recuperado;
    }

    /**Determina si el árbol tiene la misma estructura que otroArbol.
     * Compara las estructuras (el mismo formato de árbol), que cada nodo ubicado en
     * el lugar semejante tenga el mismo identificador del dato.
     * @param otroArbol
     * @return true en caso de ser semejante, false en caso contrario
     */
    public boolean isSemejante(Arbol_Perturbacion otroArbol) {
        boolean nombreIgual = this.getNombre().equals(otroArbol.getNombre());
        boolean descripcionIgual = this.getDescripcion().equals(otroArbol.getDescripcion());
        boolean rtaParcial = nombreIgual && descripcionIgual;
        if (rtaParcial == true) {
            rtaParcial = this.getRaiz().isSemejante(otroArbol.getRaiz());
        }
        return rtaParcial;
    }

    /**
     * Devuelve un clon del Arbol de Pertubación
     * @return
     */
    @Override
    public Arbol_Perturbacion clone() {
        // TODO Implement this method
        return Arbol_Perturbacion.deserializar(this.serializar());
    }

    public void suma(Arbol_Perturbacion sumando) throws Exception {
        if (!this.isSemejante(sumando))
            throw new Exception("Imposible sumar, los arboles no son semejantes");
        else {
            this.getRaiz().suma(sumando.getRaiz());
        }
    }

    public void resta(Arbol_Perturbacion sustraendo) throws Exception {
        if (!this.isSemejante(sustraendo))
            throw new Exception("Imposible restar, los arboles no son semejantes");
        else {
            this.getRaiz().resta(sustraendo.getRaiz());
        }
    }

    public void multiplica(double factor) {
        this.getRaiz().multiplica(factor);
    }

    public void dividir(double divisor) throws Exception {
        if (divisor == 0)
            throw new Exception("No se puede dividir por cero");
        else {
            this.getRaiz().dividir(divisor);
        }
    }

    public static Arbol_Perturbacion promedio(ArrayList<Arbol_Perturbacion> lista) throws Exception {
        Arbol_Perturbacion resultado = null;
        if (lista != null && lista.size() != 0) {
            resultado = lista.get(0).clone();
            for (int i = 1; i < lista.size(); i++)
                resultado.suma(lista.get(i));
            resultado.dividir(lista.size());
        }
        return resultado;
    }

    public boolean isCorregido() {
        return (!this.getRaiz()
                     .getEtiqueta()
                     .isCero());

    }
}
