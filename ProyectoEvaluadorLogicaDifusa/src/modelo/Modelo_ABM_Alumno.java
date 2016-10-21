package modelo;

import base_de_datos.BaseDeDatos;

import java.util.Iterator;


public class Modelo_ABM_Alumno 
{
   
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

    public void borrarAlumno(Alumno alumno)
    {
        db.borrar_alumno(alumno);
    }

    public void modificarAlumno(Alumno al)
    {
        db.modificar_alumno(al);
    }


    public Iterator get_lista_alumnos()
    {
        return db.recuperar_alumnos();
    }

   

    

}
