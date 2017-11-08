package modelo;

import base_de_datos.BaseDeDatos;

import java.sql.SQLException;

import java.util.Iterator;

import javax.sql.rowset.serial.SerialException;

public class Modelo_ABM_Asignatura
{
    BaseDeDatos db;

    public Modelo_ABM_Asignatura(BaseDeDatos db)
    {
        super();
        this.db = db;
    }

    public void almacenar_asignatura(Asignatura asignatura) throws SQLException
    {
        db.almacenar_asignatura_nueva(asignatura);
    }

    public void actualizar_Asignatura(Asignatura asignatura_en_uso) throws SerialException, SQLException
    {
        db.actualizar_Asignatura(asignatura_en_uso);
    }

    public Iterator getIterator_listado_asignaturas() throws SQLException
    {
        return db.recuperar_asignaturas();
    }

    public void borrar_asignatura(Asignatura asignatura) throws SQLException
    {
        db.borrar_asignatura(asignatura);
    }

}
