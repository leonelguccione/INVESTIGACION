/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import base_de_datos.BaseDeDatos;


/**
 *
 * @author leonel
 */
public class Modelo
{
    private BaseDeDatos db;
    private Modelo_ABM_arbol_perturbacion modelo_abm_ap;
    private Modelo_ABM_Alumno modelo_abm_alumno;
    private Modelo_ABM_Cursada modelo_abm_cursada;
    private Modelo_ABM_Evaluacion modelo_abm_evaluacion;
    private Modelo_ABM_Asignatura modelo_abm_asignatura;

    public Modelo_ABM_Evaluacion getModelo_abm_evaluacion() {
        return modelo_abm_evaluacion;
    }

    public Modelo()
    {
        db = new BaseDeDatos();
        modelo_abm_ap = new Modelo_ABM_arbol_perturbacion(db);
        modelo_abm_alumno = new Modelo_ABM_Alumno(db);
        modelo_abm_cursada= new Modelo_ABM_Cursada(db);
        modelo_abm_evaluacion=new Modelo_ABM_Evaluacion(db);
        modelo_abm_asignatura = new Modelo_ABM_Asignatura(db);        
    }

    public Modelo_ABM_Cursada getModelo_abm_cursada() {
        return modelo_abm_cursada;
    }

    public Modelo_ABM_arbol_perturbacion getModelo_abm_ap()
    {
        return modelo_abm_ap;
    }
    
    public Modelo_ABM_Alumno getModelo_abm_alumno()
    {
        return modelo_abm_alumno;
    }

    public Modelo_ABM_Asignatura getModelo_abm_asignatura()
    {
        return modelo_abm_asignatura;
    }
}
