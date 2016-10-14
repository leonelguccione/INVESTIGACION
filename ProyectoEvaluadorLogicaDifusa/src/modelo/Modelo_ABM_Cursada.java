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
