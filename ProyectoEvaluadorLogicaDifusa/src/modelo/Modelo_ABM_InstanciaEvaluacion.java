package modelo;

import base_de_datos.BaseDeDatos;

import java.sql.SQLException;

import java.util.Iterator;


public class Modelo_ABM_InstanciaEvaluacion
{
    private BaseDeDatos db;

    public Modelo_ABM_InstanciaEvaluacion(BaseDeDatos db)
    {
        super();
        this.db = db;
    }

    public int recupera_proxima_evaluacion() throws SQLException
    {
        return this.db.recupera_proxima_evaluacion();
    }

    public void agregaInstanciaEvaluacion(Parcial parcial,Instancia_Evaluacion ev) throws SQLException
    {
        this.db.almacenar_evaluacion(parcial,ev);
    }

    public Iterator recuperarInstanciasevaluaciones(Parcial parcial) throws SQLException {
       return db.recuperar_evaluaciones(parcial);
        
    }
     
    public void borrarInstanciaEvaluacion(int id_evaluacion) throws SQLException
    {
        db.borrar_evaluacion(id_evaluacion);
    }
}
