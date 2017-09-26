package modelo;

import base_de_datos.BaseDeDatos;

import java.sql.SQLException;

import java.util.Iterator;

public class Modelo_ABM_Cursada
{
   
    private BaseDeDatos db;

    public Modelo_ABM_Cursada(BaseDeDatos db)
    {
        super();
        this.db = db;
    }

   
    public void AgregarCursada(Asignatura asignatura,Cursada cur) throws SQLException
    {
        this.db.almacenar_cursada(asignatura,cur);
    }

    public Iterator recuperar_cursadas(Asignatura asignatura) throws SQLException
    {
        return db.recuperar_cursadas(asignatura);
    }

    public void eliminar_cursada(Cursada cur) throws SQLException
    {
        db.borrar_cursada(cur);
    }
}
