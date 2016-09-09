package modelo;


public class Asignatura
{
    String nombre_asignatura;
    String codigo;
    Arbol_Perturbacion arbol_dominio;//serializable
    
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
