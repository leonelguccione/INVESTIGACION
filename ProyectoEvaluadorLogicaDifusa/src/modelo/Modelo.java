/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import base_de_datos.BaseDeDatos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;


/**
 *
 * @author leonel
 */
public class Modelo {
    private BaseDeDatos db;
    private Modelo_ABM_arbol_perturbacion modelo_abm_ap;
    private Modelo_ABM_Alumno modelo_abm_alumno;
    private Modelo_ABM_Cursada modelo_abm_cursada;
    private Modelo_ABM_Evaluacion modelo_abm_evaluacion;
    private Modelo_ABM_Asignatura modelo_abm_asignatura;
    private Modelo_ABM_Parcial modelo_abm_parcial;

    private HashMap<Long, Alumno> alumnos = new HashMap<Long, Alumno>();
    private HashMap<String, Asignatura> asignaturas = new HashMap<String, Asignatura>();


    public HashMap<Long, Alumno> getAlumnos() {
        return alumnos;
    }

    public HashMap<String, Asignatura> getAsignaturas() {
        return asignaturas;
    }

    public Modelo_ABM_Evaluacion getModelo_abm_evaluacion() {
        return modelo_abm_evaluacion;
    }

    public Modelo() {
        db = new BaseDeDatos();
        modelo_abm_ap = new Modelo_ABM_arbol_perturbacion(db);
        modelo_abm_alumno = new Modelo_ABM_Alumno(db);
        modelo_abm_cursada = new Modelo_ABM_Cursada(db);
        modelo_abm_evaluacion = new Modelo_ABM_Evaluacion(db);
        modelo_abm_asignatura = new Modelo_ABM_Asignatura(db);
        modelo_abm_parcial = new Modelo_ABM_Parcial(db);
        this.recupera_sistema();
    }

    public Modelo_ABM_Parcial getModelo_abm_parcial() {
        return modelo_abm_parcial;
    }

    public Modelo_ABM_Cursada getModelo_abm_cursada() {
        return modelo_abm_cursada;
    }

    public Modelo_ABM_arbol_perturbacion getModelo_abm_ap() {
        return modelo_abm_ap;
    }

    public Modelo_ABM_Alumno getModelo_abm_alumno() {
        return modelo_abm_alumno;
    }

    public Modelo_ABM_Asignatura getModelo_abm_asignatura() {
        return modelo_abm_asignatura;
    }

    public void agrega_asignatura(Asignatura asignatura) {
        this.asignaturas.put(asignatura.getCodigo(), asignatura);
        this.modelo_abm_asignatura.almacenar_asignatura(asignatura);
    }

    public void agrega_alumno(Alumno alumno) {
        this.alumnos.put(alumno.getDni(), alumno);
        this.modelo_abm_alumno.agregarAlumno(alumno);
    }

    public void borra_asignatura(Asignatura asignatura) {
        this.asignaturas.remove(asignatura);
        this.modelo_abm_asignatura.borrar_asignatura(asignatura);
    }

    public void borra_alumno(Alumno alumno) {
        this.alumnos.remove(alumno);
        this.modelo_abm_alumno.borrarAlumno(alumno);
    }


    //Metodos de recuperacion de sistema a partir de las BD
    //Ver mapeadores
    public void recupera_sistema() {
        //Recupera los alumnos
        Iterator iterator_alumnos = this.getModelo_abm_alumno().get_lista_alumnos();
        while (iterator_alumnos.hasNext())
        {
            Alumno alumno_aux = (Alumno) iterator_alumnos.next();
            this.alumnos.put(alumno_aux.getDni(), alumno_aux);
        }
        Iterator iterator_asignaturas = modelo_abm_asignatura.getIterator_listado_asignaturas();
        //Recupera las asignaturas
        while (iterator_asignaturas.hasNext())
        {
            Asignatura asignatura_aux = (Asignatura) iterator_asignaturas.next();
            this.asignaturas.put(asignatura_aux.getCodigo(), asignatura_aux);
            this.recuperarCursadas(asignatura_aux);
        }
        //Recupera las cursadas


    }

    public void recuperarCursadas(Asignatura asignatura) {
        Iterator iterator_cursadas = this.getModelo_abm_cursada().recuperar_cursadas(asignatura.getCodigo());
        //Recorrer el contenido del Iterator
        ArrayList<Cursada> cursadas_recuperadas = new ArrayList<Cursada>();
        while (iterator_cursadas.hasNext())
        {
            Cursada cur = (Cursada) iterator_cursadas.next();
            cursadas_recuperadas.add(cur);
            this.recuperarParciales(cur);
        }
        asignatura.setCursadas(cursadas_recuperadas);
    }

    public void recuperarParciales(Cursada cursada) {
        Iterator iterator_parciales = this.getModelo_abm_parcial().recuperar_parciales(cursada.getId());
        //Recorrer el contenido del Iterator
        ArrayList<Parcial> parciales_recuperados = new ArrayList<Parcial>();
        while (iterator_parciales.hasNext())
        {
            Parcial parcial = (Parcial) iterator_parciales.next();
            parciales_recuperados.add(parcial);
        }
        cursada.setParciales(parciales_recuperados);
    }


}
