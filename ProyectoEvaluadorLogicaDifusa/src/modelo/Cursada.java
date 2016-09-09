package modelo;


import java.util.ArrayList;
import java.util.Iterator;

public class Cursada
{
    private int anio;
    private int cuatrimestre;
    private String Asignatura;//TODO: cambiar por clase Asignatura
    private int id;

    /**
     * @aggregation shared
     */
    private ArrayList<Alumno> alumnos;

    public Cursada(int id, String Asignatura, int anio, int cuatrimestre)
    {
        this();
        this.anio = anio;
        this.cuatrimestre = cuatrimestre;
        this.Asignatura = Asignatura;
        this.id = id;
    }
    

    public void setAlumnos(ArrayList<Alumno> alumnos)
    {
        this.alumnos = alumnos;
    }

    public ArrayList<Alumno> getAlumnos()
    {
        return alumnos;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getId()
    {
        return id;
    }

    public Cursada()
    {
        super();
        alumnos = new ArrayList<Alumno>();
    }


    public void setAnio(int anio)
    {
        this.anio = anio;
    }

    public int getAnio()
    {
        return anio;
    }

    public void setCuatrimestre(int cuatrimestre)
    {
        this.cuatrimestre = cuatrimestre;
    }

    public int getCuatrimestre()
    {
        return cuatrimestre;
    }

    public void setAsignatura(String Asignatura)
    {
        this.Asignatura = Asignatura;
    }

    public String getAsignatura()
    {
        return Asignatura;
    }


    @Override
    public String toString()
    {
        String aux =
            this.getId() + ": " + this.getAsignatura() + "  Año: " + this.getAnio() + " Cuatrimestre: " +
            this.getCuatrimestre() + "\n";
        return aux;
    }

    public String detalle()
    {
        String aux =
            this.getId() + "  " + this.getAsignatura() + "  " + this.getAnio() + "  " + this.getCuatrimestre() + "\n";
        Iterator<Alumno> it = this.alumnos.iterator();
        while (it.hasNext())

            aux += it.next().toString() + "\n";
        return aux;
    }
    
    public String getNombreCursada()
    {
        String nombre_cursada = this.getAsignatura()+"-"+this.getAnio()+"-"+this.getCuatrimestre();
        return nombre_cursada;
    }


}
