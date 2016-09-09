package modelo;

import base_de_datos.BaseDeDatos;

import java.util.Iterator;
import java.util.SortedMap;
import java.util.TreeMap;

public class Modelo_ABM_Cursada
{
    private SortedMap<String, Alumno> alumnos_en_bd = new TreeMap<String, Alumno>();
    private SortedMap<String, Alumno> alumnos_en_cursada = new TreeMap<String, Alumno>();

    private BaseDeDatos db;

    public Modelo_ABM_Cursada(BaseDeDatos db)
    {
        super();
        this.db = db;
    }

    public Iterator get_lista_alumnos()
    {
        return db.recuperar_alumnos();
    }

    public void AgregarCursada(Cursada cur)
    {
        this.db.almacenar_cursada(cur);
    }

    public Iterator recuperar_cursadas()
    {
        return db.recuperar_cursadas();
    }

    public void eliminar_cursada(Cursada cur)
    {
        db.borrar_cursada(cur);
    }
}
