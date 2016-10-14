package modelo;

import base_de_datos.BaseDeDatos;

import java.util.Iterator;

public class Modelo_ABM_Parcial
{

    private BaseDeDatos db;
    public Modelo_ABM_Parcial(BaseDeDatos db)
    {
        super();
        this.db = db;
    }


   

    public void AgregarParcial(Cursada cur, Parcial parcial)
    {
        this.db.almacenar_Parcial(cur,parcial);
    }

    public Iterator recuperar_parciales(int id_cursada)
    {
        return db.recuperar_parciales(id_cursada);
        
    }

    public void eliminar_parcial(Parcial parcial)
    {
       db.borrar_parcial(parcial);
    }
   
}
