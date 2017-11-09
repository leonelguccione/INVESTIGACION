package modelo;

import base_de_datos.BaseDeDatos;

import java.sql.SQLException;

import java.util.Iterator;

public class Modelo_ABM_Parcial
{

    private BaseDeDatos db;
    public Modelo_ABM_Parcial(BaseDeDatos db)
    {
        super();
        this.db = db;
    }


   

    public void AgregarParcial(Cursada cur, Parcial parcial) throws SQLException
    {
        this.db.almacenar_Parcial(cur,parcial);
    }

    public Iterator recuperar_parciales(Cursada cursada) throws SQLException
    {
        return db.recuperar_parciales(cursada);
        
    }

    public void eliminar_parcial(Parcial parcial) throws SQLException
    {
       db.borrar_parcial(parcial);
    }
   
}
