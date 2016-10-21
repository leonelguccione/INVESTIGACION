package modelo;

import base_de_datos.BaseDeDatos;

import java.util.Iterator;


public class Modelo_ABM_InstanciaEvaluacion
{
    private BaseDeDatos db;

    public Modelo_ABM_InstanciaEvaluacion(BaseDeDatos db)
    {
        super();
        this.db = db;
    }

    public int recupera_proxima_evaluacion()
    {
        return this.db.recupera_proxima_evaluacion();
    }

    public void agregaInstanciaEvaluacion(Instancia_Evaluacion ev)
    {
        this.db.almacenar_evaluacion(ev);
    }

    public Iterator recuperarInstanciasevaluaciones()
    {
       // return db.recuperar_evaluaciones();
        return null;
    }
     
    public void borrarInstanciaEvaluacion(int id_evaluacion)
    {
        db.borrar_evaluacion(id_evaluacion);
    }
}
