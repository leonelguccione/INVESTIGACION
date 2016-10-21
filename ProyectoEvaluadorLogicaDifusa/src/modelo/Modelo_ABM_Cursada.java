package modelo;

import base_de_datos.BaseDeDatos;

import java.util.Iterator;

public class Modelo_ABM_Cursada
{
   
    private BaseDeDatos db;

    public Modelo_ABM_Cursada(BaseDeDatos db)
    {
        super();
        this.db = db;
    }

   
    public void AgregarCursada(Asignatura asignatura,Cursada cur)
    {
        this.db.almacenar_cursada(asignatura,cur);
    }

    public Iterator recuperar_cursadas(String codigo_asignatura)
    {
        return db.recuperar_cursadas(codigo_asignatura);
    }

    public void eliminar_cursada(Cursada cur)
    {
        db.borrar_cursada(cur);
    }
}
