package modelo;

import base_de_datos.BaseDeDatos;

import java.util.Iterator;
import java.util.Observable;


public class Modelo_ABM_Alumno extends Observable
{
    /**Por ahora la clave es el apellido, luego debe mejorarse
     */
    //TODO: cambiar la clave
    private BaseDeDatos db;

    public Modelo_ABM_Alumno(BaseDeDatos dbl)
    {
        super();
        this.db = dbl;
    }

    public void agregarAlumno(Alumno al)
    {
        this.db.almacenar_alumno(al);
    }

    public void borrarAlumno(long dni)
    {
        db.borrar_alumno(dni);
    }

    public void modificarAlumno(Alumno al)
    {
        db.modificar_alumno(al);
    }


    public Iterator get_lista_alumnos()
    {
        return db.recuperar_alumnos();
    }

    public void generar_lista_alumnos()
    {
        setChanged();
        notifyObservers("cargar_alumnos");
    }

    public Iterator getIterator_listado_alumnos()
    {
        return db.recuperar_alumnos();
    }


}
