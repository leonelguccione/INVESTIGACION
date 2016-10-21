package modelo;

import base_de_datos.BaseDeDatos;

import java.sql.SQLException;

import java.util.Iterator;


public class Modelo_ABM_Alumno 
{
   
    private BaseDeDatos db;

    public Modelo_ABM_Alumno(BaseDeDatos dbl)
    {
        super();
        this.db = dbl;
    }

    public void agregarAlumno(Alumno al) throws SQLException
    {
        this.db.almacenar_alumno(al);
    }

    public void borrarAlumno(Alumno alumno) throws SQLException
    {
        db.borrar_alumno(alumno);
    }

    public void modificarAlumno(Alumno al) throws SQLException
    {
        db.modificar_alumno(al);
    }


    public Iterator get_lista_alumnos() throws SQLException
    {
        return db.recuperar_alumnos();
    }

   

    

}
