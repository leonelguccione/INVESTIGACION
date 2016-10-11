package modelo;


public class Examen
{

    /**
     * @aggregation shared
     */
    private Alumno alumno;

    /**es un clone del arbol de dominio podado que corresponde al Parcial
     * al cual pertenece la instancia-->Examen
     * @aggregation shared
     */
    private Arbol_Perturbacion arbol_podado_particular;
    private boolean modificado;

    public void setModificado(boolean modificado)
    {
        this.modificado = modificado;
    }

    public boolean isModificado()
    {
        return modificado;
    }

    public Examen(Alumno alumno, Arbol_Perturbacion arbol)
    {
        super();
        this.alumno = alumno;
        this.arbol_podado_particular = arbol;
        modificado = false;
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
        String aux = this.getAlumno().getNombre();
        return aux;

    }
}
