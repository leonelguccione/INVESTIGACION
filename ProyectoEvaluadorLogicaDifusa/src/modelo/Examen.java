package modelo;


public class Examen {

    /**
     * @aggregation shared
     */
    private Alumno alumno;

    /**
     * @aggregation shared
     */
    private Arbol_Perturbacion arbol;
    private boolean modificado;

    public void setModificado(boolean modificado) {
        this.modificado = modificado;
    }

    public boolean isModificado() {
        return modificado;
    }

    public Examen(Alumno alumno, Arbol_Perturbacion arbol) {
        super();
        this.alumno = alumno;
        this.arbol = arbol;
        modificado=false;
    }


    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setArbol(Arbol_Perturbacion arbol) {
        this.arbol = arbol;
    }

    public Arbol_Perturbacion getArbol() {
        return arbol;
    }

    public Examen() {
        super();
    }
    @Override
    public String toString()
    {
      String aux=this.getAlumno().getNombre();
      return aux;
      
      }
}
