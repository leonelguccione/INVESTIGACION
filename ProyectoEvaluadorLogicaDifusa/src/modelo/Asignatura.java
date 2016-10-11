package modelo;

import java.util.ArrayList;


public class Asignatura
{
    private String nombre_asignatura;
    private String codigo;
    private Arbol_Perturbacion arbol_dominio;//serializable
    private ArrayList<Cursada> cursadas = new ArrayList<Cursada>();
    
    public Asignatura()
    {
        super();
    }

    public void setNombre(String nombre)
    {
        this.nombre_asignatura = nombre;
    }

    public String getNombre()
    {
        return nombre_asignatura;
    }

    public void setCodigo(String codigo)
    {
        this.codigo = codigo;
    }

    public String getCodigo()
    {
        return codigo;
    }

    public void setArbol_dominio(Arbol_Perturbacion arbol_dominio)
    {
        this.arbol_dominio = arbol_dominio;
    }

    public Arbol_Perturbacion getArbol_dominio()
    {
        return arbol_dominio;
    }


    @Override
    public String toString()
    {
        return (this.getCodigo()+": "+this.getNombre());
    }
}
