package modelo;


import java.util.ArrayList;
import java.util.Iterator;

public class Cursada
{
    private int anio_fecha;//correponde con el año fecha, por ej 2016
    private int cuatrimestre;

    private int id;
    private ArrayList<Parcial> parciales = new ArrayList<Parcial>();

    /**
     * @aggregation shared
     */
    private ArrayList<Alumno> alumnos;

    public Cursada(int id, int anio, int cuatrimestre)
    {
        this();
        this.anio_fecha = anio;
        this.cuatrimestre = cuatrimestre;
        
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


    public void setAnio_fecha(int anio)
    {
        this.anio_fecha = anio;
    }

    public int getAnio_fecha()
    {
        return anio_fecha;
    }

    public void setCuatrimestre(int cuatrimestre)
    {
        this.cuatrimestre = cuatrimestre;
    }

    public int getCuatrimestre()
    {
        return cuatrimestre;
    }




    @Override
    public String toString()
    {
        String aux =
            this.getId() + ": " + "  Año: " + this.getAnio_fecha() + " Cuatrimestre: " +
            this.getCuatrimestre() + "\n";
        return aux;
    }

    public String detalle()
    {
        String aux =
            this.getId() + "  " + "  " + this.getAnio_fecha() + "  " + this.getCuatrimestre() + "\n";
        Iterator<Alumno> it = this.alumnos.iterator();
        while (it.hasNext())

            aux += it.next().toString() + "\n";
        return aux;
    }
    
    public String getNombreCursada()
    {
        String nombre_cursada = this.getAnio_fecha()+"-"+this.getCuatrimestre();
        return nombre_cursada;
    }


}
