package modelo;

import base_de_datos.BaseDeDatos;

import java.util.Iterator;


public class Modelo_ABM_Evaluacion
{
    private BaseDeDatos db;

    public Modelo_ABM_Evaluacion(BaseDeDatos db)
    {
        super();
        this.db = db;
    }

    public int recupera_proxima_evaluacion()
    {
        return this.db.recupera_proxima_evaluacion();
    }

    public void agrega_evaluacion(Evaluacion ev)
    {
        this.db.almacenar_evaluacion(ev);
    }

    public Iterator recuperar_evaluaciones()
    {
        return db.recuperar_evaluaciones();
    }

    public void actualizar_examen(Evaluacion evaluacion, Examen examen)
    {
        db.actualizar_examen(evaluacion, examen);
    }
    
    public void borrar_evaluacion(int id_evaluacion)
    {
        db.borrar_evaluacion(id_evaluacion);
    }
}
