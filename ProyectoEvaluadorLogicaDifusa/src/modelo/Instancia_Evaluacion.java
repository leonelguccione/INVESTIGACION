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
public class Instancia_Evaluacion
{

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

    public void setFecha(Date fecha)
    {
        this.fecha = fecha;
    }

    public Date getFecha()
    {
        return fecha;
    }

    public Instancia_Evaluacion(Arbol_Perturbacion arbol_perturbacion, Date fecha, String descripcion,
                      ArrayList<Alumno> alumnos_evaluados)
    {
        super();
        this.examenes = new ArrayList<Examen>();
        Examen examen_auxiliar;
        this.fecha = fecha;
        this.descripcion = descripcion;
       
        for (int i = 0; i < alumnos_evaluados.size(); i++)
        {
            examen_auxiliar = new Examen(alumnos_evaluados.get(i), arbol_perturbacion);
            this.getExamenes().add(examen_auxiliar);
        }

    }

    /**se utiliza cuando los levanto de la base de datos
     * @param examenes_tomados
     * @param cursada
     * @param arbol_perturbacion
     * @param fecha
     * @param descripcion
     * @param id_evaluacion
     */
    public Instancia_Evaluacion(ArrayList<Examen> examenes_tomados, 
                      Date fecha, String descripcion, int id_evaluacion)
    {
        this.examenes = examenes_tomados;
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
            this.getId_evaluacion() + " - " + " - " +
            this.getDescripcion() + " - " + Fecha.date2Str(this.getFecha());
        return retorno;
    }

    public void setExamenes(ArrayList<Examen> examenes)
    {
        this.examenes = examenes;
    }
}
