package modelo;

import java.util.ArrayList;


public class Asignatura
{
    private String nombre_asignatura;
    private String codigo;
    private ArbolPerturbacion arbol_dominio; //serializable

    /**
     * @aggregation composite
     */
    private ArrayList<Cursada> cursadas = new ArrayList<Cursada>();

    public Asignatura()
    {
        super();
    }


    public Asignatura(String nombre_asignatura, String codigo, ArbolPerturbacion arbol_dominio)
    {
        this.nombre_asignatura = nombre_asignatura;
        this.codigo = codigo;
        this.arbol_dominio = arbol_dominio;
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

    public void setArbol_dominio(ArbolPerturbacion arbol_dominio)
    {
        this.arbol_dominio = arbol_dominio;
    }

    public ArbolPerturbacion getArbol_dominio()
    {
        return arbol_dominio;
    }


    @Override
    public String toString()
    {
        return (this.getCodigo() + ": " + this.getNombre());
    }

    public void setCursadas(ArrayList<Cursada> cursadas)
    {
        this.cursadas = cursadas;
    }

    public ArrayList<Cursada> getCursadas()
    {
        return cursadas;
    }

    public void agregaCursada(Cursada cursada)
    {
        this.cursadas.add(cursada);
    }
}
