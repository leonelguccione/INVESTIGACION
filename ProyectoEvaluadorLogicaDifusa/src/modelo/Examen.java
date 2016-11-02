package modelo;


public class Examen
{

    /**
     * @aggregation shared
     */
    private int id;
    private Alumno alumno;

    /**es un clone del arbol de dominio podado que corresponde al Parcial
     * al cual pertenece la instancia-->Examen
     * @aggregation shared
     */
    private Arbol_Perturbacion arbol_podado_particular;
    private boolean modificado;
  

    public Examen(Alumno alumno, Arbol_Perturbacion arbol)
    {
        super();
        this.alumno = alumno;
        this.arbol_podado_particular =Arbol_Perturbacion.deserializar(arbol.serializar()) ;
        modificado = false;
    }

    public Examen(int id, Alumno alumno, Arbol_Perturbacion arbol_podado_particular, boolean modificado)
    {
        this.id = id;
        this.alumno = alumno;
        this.arbol_podado_particular = arbol_podado_particular;
        this.modificado = modificado;
    }

    public void setModificado(boolean modificado)
    {
        this.modificado = modificado;
    }

    public boolean isModificado()
    {
        return modificado;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getId()
    {
        return id;
    }

    


    public void setAlumno(Alumno alumno)
    {
        this.alumno = alumno;
    }

    public Alumno getAlumno()
    {
        return alumno;
    }

    public void setArbol_podado_particular(Arbol_Perturbacion arbol)
    {
        this.arbol_podado_particular = arbol;
    }

    public Arbol_Perturbacion getArbol_podado_particular()
    {
        return arbol_podado_particular;
    }

    public Examen()
    {
        super();
    }

    @Override
    public String toString()
    {
        String aux = this.getAlumno().getApellido()+" "+ this.getAlumno().getNombre();
        return aux;

    }
}
