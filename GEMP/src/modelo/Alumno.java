/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;


/**
 *
 * @author leonel
 */
public class Alumno implements Comparable
{

    private long legajo;
    private String apellido;
    private String nombre;
    private long dni;

    public Alumno(long legajo, String apellido, String nombre, long dni)
    {
        this.legajo = legajo;
        this.apellido = apellido;
        this.nombre = nombre;
        this.dni = dni;
    }

    /**
     * @return the legajo
     */
    public long getLegajo()
    {
        return legajo;
    }

    /**
     * @param legajo the legajo to set
     */
    public void setLegajo(long legajo)
    {
        this.legajo = legajo;
    }

    /**
     * @return the apellido
     */
    public String getApellido()
    {
        return apellido;
    }

    /**
     * @param apellido the apellido to set
     */
    public void setApellido(String apellido)
    {
        this.apellido = apellido;
    }

    /**
     * @return the nombre
     */
    public String getNombre()
    {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    /**
     * @return the dni
     */
    public long getDni()
    {
        return dni;
    }

    /**
     * @param dni the dni to set
     */
    public void setDni(long dni)
    {
        this.dni = dni;
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 73 * hash + (int) (this.dni ^ (this.dni >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final Alumno other = (Alumno) obj;
        if (this.dni != other.dni)
        {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(Object o)
    {
        Alumno otro = (Alumno) o;
        return getApellido().compareTo(otro.getApellido());
    }

    @Override
    public String toString()
    {
        return apellido +"   "+ nombre+ "   "+dni;
    }
    
    

}
