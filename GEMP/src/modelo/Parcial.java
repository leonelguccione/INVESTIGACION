package modelo;

import java.util.ArrayList;


/**Representa Parcial1, Parcial2, Totalizador...
 */
public class Parcial
{
    private int id;
    private String nombre;
    private ArbolPerturbacion arbol_podado = null;
    private ArrayList<Instancia_Evaluacion> instancias_evaluaciones = new ArrayList<Instancia_Evaluacion>();

     public Parcial(int id, String nombre, ArbolPerturbacion arbol_podado)
    {
        this.id = id;
        this.nombre = nombre;
        this.arbol_podado = arbol_podado;
    }


    public void setId(int id)
    {
        this.id = id;
    }

    public int getId()
    {
        return id;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setArbol_podado(ArbolPerturbacion arbol_podado)
    {
        this.arbol_podado = arbol_podado;
    }

    public ArbolPerturbacion getArbol_podado()
    {
        return arbol_podado;
    }

    public void setInstancias_evaluaciones(ArrayList<Instancia_Evaluacion> instancias_evaluaciones)
    {
        this.instancias_evaluaciones = instancias_evaluaciones;
    }

    public ArrayList<Instancia_Evaluacion> getInstancias_evaluaciones()
    {
        return instancias_evaluaciones;
    }

    @Override
    public String toString() {
        // TODO Implement this method
        return this.getNombre();
    }

}
