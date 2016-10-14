package modelo;

import base_de_datos.BaseDeDatos;

import java.util.Iterator;

public class Modelo_ABM_Asignatura
{
    BaseDeDatos db;

    public Modelo_ABM_Asignatura(BaseDeDatos db)
    {
        super();
        this.db = db;
    }

    public void almacenar_asignatura(Asignatura asignatura)
    {
        db.almacenar_asignatura_nueva(asignatura);
    }

    public void actualizar_arbol_perturbacion(Asignatura asignatura_en_uso)
    {
        db.actualizar_arbol_perturbacion(asignatura_en_uso);
    }

    public Iterator getIterator_listado_asignaturas()
    {
        return db.recuperar_asignaturas();
    }

    public void borrar_asignatura(Asignatura asignatura)
    {
        db.borrar_asignatura(asignatura);
    }

}
