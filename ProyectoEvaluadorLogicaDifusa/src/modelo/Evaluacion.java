/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Date;

import java.util.ArrayList;

import util.Fecha;


/**
 *
 * @author leonel
 *
 */
public class Evaluacion
{
    /**
     * @aggregation shared
     */
    private Cursada cursada;

    /**
     * @aggregation shared
     */
    private Arbol_Perturbacion arbol_perturbacion;
    private Date fecha;
    private String descripcion;

    /**
     * @aggregation shared
     */
    private ArrayList<Examen> examenes;
    private int id_evaluacion;


    public ArrayList<Examen> getExamenes()
    {
        return examenes;
    }

    public void setCursada(Cursada cursada)
    {
        this.cursada = cursada;
    }

    public Cursada getCursada()
    {
        return cursada;
    }

    public void setArbol_perturbacion(Arbol_Perturbacion arbol_perturbacion)
    {
        this.arbol_perturbacion = arbol_perturbacion;
    }

    public Arbol_Perturbacion getArbol_perturbacion()
    {
        return arbol_perturbacion;
    }

    public void setFecha(Date fecha)
    {
        this.fecha = fecha;
    }

    public Date getFecha()
    {
        return fecha;
    }

    public Evaluacion(Cursada cursada, Arbol_Perturbacion arbol_perturbacion, Date fecha, String descripcion,
                      ArrayList<Alumno> alumnos_evaluados, int id_evaluacion)
    {
        super();
        this.examenes = new ArrayList<Examen>();
        Examen examen_auxiliar;
        this.cursada = cursada;
        this.arbol_perturbacion = arbol_perturbacion;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.id_evaluacion = id_evaluacion;

        for (int i = 0; i < alumnos_evaluados.size(); i++)
        {
            examen_auxiliar = new Examen(alumnos_evaluados.get(i), arbol_perturbacion);
            this.getExamenes().add(examen_auxiliar);
        }

    }

    public Evaluacion(ArrayList<Examen> examenes_tomados, Cursada cursada, Arbol_Perturbacion arbol_perturbacion,
                      Date fecha, String descripcion, int id_evaluacion)
    {
        this.examenes = examenes_tomados;
        this.cursada = cursada;
        this.arbol_perturbacion = arbol_perturbacion;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.id_evaluacion = id_evaluacion;
    }

    public void setDescripcion(String descripcion)
    {
        this.descripcion = descripcion;
    }

    public String getDescripcion()
    {
        return descripcion;
    }

    public void setAlumnos_evaluados(ArrayList<Alumno> alumnos_evaluados)
    {
        this.setExamenes(new ArrayList<Examen>());
        Examen examen_auxiliar;
        for (int i = 0; i < alumnos_evaluados.size(); i++)
        {
            examen_auxiliar = new Examen(alumnos_evaluados.get(i), arbol_perturbacion);
            this.getExamenes().add(examen_auxiliar);
        }

    }

    public ArrayList<Alumno> getAlumnos_evaluados()
    {
        ArrayList<Alumno> alumnos_evaluados = new ArrayList<Alumno>();
        for (int i = 0; i < this.getExamenes().size(); i++)
        {
            alumnos_evaluados.add(this.getExamenes().get(i).getAlumno());
        }
        return alumnos_evaluados;
    }

    public void setId_evaluacion(int id_evaluacion)
    {
        this.id_evaluacion = id_evaluacion;
    }

    public int getId_evaluacion()
    {
        return id_evaluacion;
    }

    @Override
    public String toString()
    {
        String retorno;
        retorno =
            this.getId_evaluacion() + " - " + this.getCursada().getAsignatura() + " - " +
            this.getDescripcion() + " - " + Fecha.date2Str(this.getFecha());
        return retorno;
    }

    public Evaluacion()
    {

    }

    public void setExamenes(ArrayList<Examen> examenes)
    {
        this.examenes = examenes;
    }
}
