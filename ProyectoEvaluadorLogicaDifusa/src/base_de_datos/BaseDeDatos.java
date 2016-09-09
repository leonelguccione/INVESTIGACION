package base_de_datos;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Logger;

import javax.sql.rowset.serial.SerialException;

import modelo.Alumno;
import modelo.Arbol_Perturbacion;
import modelo.Asignatura;
import modelo.Cursada;
import modelo.Evaluacion;
import modelo.Examen;

public class BaseDeDatos
{
    Connection conexion;
    Statement sentencia;
    ResultSet resultado;

    public BaseDeDatos()
    {
        super();
        inic();
    }

    void inic()
    {
        System.out.println("Iniciando programa.");

        // Se carga el driver JDBC-ODBC
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (Exception e)
        {
            System.out.println("No se pudo cargar el puente JDBC-ODBC.");
            return;
        }

        try
        {
            // Se establece la conexión con la base de datos
            conexion =
                DriverManager.getConnection("jdbc:mysql://localhost:3306/modelo_del_estudiante", "root", "leonel");
        }
        catch (Exception e)
        {
            System.out.println(e);
            return;
        }
        System.out.println("Creación finalizada.");
    }


    // Arbol de Perturbación

    public void almacenar_arbol_perturbacion(Arbol_Perturbacion arbol)
    {
        String nombre = arbol.getNombre();
        byte[] arbol_serializado = arbol.serializar();

        Statement stmt;
        try
        {
            //borro arbol vacío, que se grabó para verlo en la lista de arboles
            this.borrar_arbol_perturbacion(arbol);

            Blob blob = new javax.sql.rowset.serial.SerialBlob(arbol_serializado);
            PreparedStatement agregar =
                conexion.prepareStatement("INSERT INTO ARBOL (nombre, arbolSerializado) VALUES (?,?)");
            agregar.setString(1, nombre);
            agregar.setBlob(2, blob);
            agregar.executeUpdate();
        }
        catch (SQLException e)
        {
            Logger.getLogger("Problemas en la inserción");
            System.out.println("Problemas en la inserción del árbol");
        }

    }


    public Iterator recuperar_arbol_perturbacion()
    {
        Statement sentencia;
        ArrayList<Arbol_Perturbacion> listado_arboles_perturbacion = new ArrayList<Arbol_Perturbacion>();
        try
        {
            sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery("SELECT * FROM ARBOL");
            while (resultado.next())
            {
                Blob unBlob = resultado.getBlob("arbolSerializado");
                int blobLength = (int) unBlob.length();
                byte[] arbolSerializado = unBlob.getBytes(1, blobLength);
                Arbol_Perturbacion arbol = Arbol_Perturbacion.deserializar(arbolSerializado);
                listado_arboles_perturbacion.add(arbol);
                unBlob.free();
            }
        }
        catch (SQLException e)
        {
            System.out.println("Problemas en la recuperación de los árboles");
        }
        return listado_arboles_perturbacion.iterator();
    }

    private Arbol_Perturbacion blobToArbol(Blob unBlob)
    {
        int blobLength;
        Arbol_Perturbacion arbol = null;
        try
        {
            blobLength = (int) unBlob.length();
            byte[] arbolSerializado = unBlob.getBytes(1, blobLength);
            arbol = Arbol_Perturbacion.deserializar(arbolSerializado);

        }
        catch (SQLException e)
        {
        }

        return arbol;
    }


    public void borrar_arbol_perturbacion(Arbol_Perturbacion arbol)
    {
        PreparedStatement borrar;
        try
        {
            borrar = conexion.prepareStatement("DELETE FROM ARBOL where nombre = ?");
            borrar.setString(1, arbol.getNombre());
            borrar.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.println("no se pudo eliminar arbol de la base de datos");
        }
    }

    // ASIGNATURA

    public void almacenar_asignatura_nueva(Asignatura asignatura)
    {
        String codigo_asignatura = asignatura.getCodigo();
        String nombre_asignatura = asignatura.getNombre();
        Arbol_Perturbacion arbol_dominio = asignatura.getArbol_dominio();
        Statement stmt;
        try
        {
            //borro arbol vacío, que se grabó para verlo en la lista de arboles
            this.borrar_asignatura(asignatura);
            Blob blob_arbol_dominio_serializado = null;
            PreparedStatement agregar =
                conexion.prepareStatement("insert into asignatura(codigo, nombre, arbolDominio)values(?,?,?)");
            agregar.setString(1, codigo_asignatura);
            agregar.setString(2, nombre_asignatura);
            agregar.setBlob(3, blob_arbol_dominio_serializado);
            agregar.executeUpdate();
        }
        catch (SQLException e)
        {
            Logger.getLogger("Problemas en la inserción");
            System.out.println("Problemas en la inserción del árbol");
        }
    }

    //TODO: mejorar esto. Podría traer problemas si cada asignatura tuviera un identificador automático
    public void actualizar_arbol_perturbacion(Asignatura asignatura_en_uso)
    {
        String codigo_asignatura = asignatura_en_uso.getCodigo();
        String nombre_asignatura = asignatura_en_uso.getNombre();
        Arbol_Perturbacion arbol_dominio = asignatura_en_uso.getArbol_dominio();

        byte[] arbol_dominio_serializado = arbol_dominio.serializar();

        Statement stmt;
        try
        {
            //borro arbol vacío, que se grabó para verlo en la lista de arboles
            this.borrar_asignatura(asignatura_en_uso);
            Blob blob_arbol_dominio_serializado = new javax.sql.rowset.serial.SerialBlob(arbol_dominio_serializado);
            PreparedStatement agregar =
                conexion.prepareStatement("insert into asignatura(codigo, nombre, arbolDominio)values(?,?,?)");
            agregar.setString(1, codigo_asignatura);
            agregar.setString(2, nombre_asignatura);
            agregar.setBlob(3, blob_arbol_dominio_serializado);
            agregar.executeUpdate();
        }
        catch (SQLException e)
        {
            Logger.getLogger("Problemas en la actualización");
            System.out.println("Problemas en la actualización del árbol");
        }
    }

    public void borrar_asignatura(Asignatura asignatura)
    {
        PreparedStatement borrar;
        try
        {
            borrar = conexion.prepareStatement("delete from asignatura where codigo = ?");
            borrar.setString(1, asignatura.getCodigo());
            borrar.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.println("no se pudo eliminar una asignatura de la base de datos");
        }
    }

    public Iterator recuperar_asignaturas()
    {
        Statement sentencia;
        ArrayList<Asignatura> listado_asignaturas = new ArrayList<Asignatura>();
        try
        {
            sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery("select * from asignatura");
            /*
             * cada asignatura está compuesta por:
             * codigo: String
             * nombre: String
             * arbolDominio: Blob
             * se construye cada instancia de Asignatura
             */
            while (resultado.next())
            {
                Arbol_Perturbacion arbol_dominio = null;
                //se recupera el arbol_dominio de la asignatura
                Blob arbolDominio_blob = resultado.getBlob("arbolDominio");
                if (arbolDominio_blob != null)
                {
                    int blobLength = (int) arbolDominio_blob.length();
                    byte[] arbolDominio_byte = arbolDominio_blob.getBytes(1, blobLength);
                    arbol_dominio = Arbol_Perturbacion.deserializar(arbolDominio_byte);
                    arbolDominio_blob.free();
                }
                //se recuperan codigo y nombre
                String codigo = resultado.getString("codigo");
                String nombre = resultado.getString("nombre");

                //se arma el objeto Asignatura

                Asignatura unaAsignatura = new Asignatura();
                unaAsignatura.setArbol_dominio(arbol_dominio);
                unaAsignatura.setCodigo(codigo);
                unaAsignatura.setNombre(nombre);
                listado_asignaturas.add(unaAsignatura);
            }
        }
        catch (SQLException e)
        {
            System.out.println("Problemas en la recuperación de las asignaturas");
        }
        return listado_asignaturas.iterator();
    }

    // ALUMNOS

    public void almacenar_alumno(Alumno alumno)
    {
        String nombre = alumno.getNombre();
        String apellido = alumno.getApellido();
        long dni = alumno.getDni();
        long legajo = alumno.getLegajo();

        try
        {
            PreparedStatement agregar =
                conexion.prepareStatement("INSERT INTO Alumnos (dni,legajo,apellido,nombre) VALUES (?,?,?,?)");
            agregar.setLong(1, dni);
            agregar.setLong(2, legajo);
            agregar.setString(3, apellido);
            agregar.setString(4, nombre);
            agregar.executeUpdate();
        }
        catch (SQLException e)
        {
            Logger.getLogger("Problemas en la inserción");
            System.out.println("Problemas en la inserción del alumno");
        }

    }


    public void borrar_alumno(long dni)
    {
        try
        {
            PreparedStatement borrar = conexion.prepareStatement("DELETE FROM Alumnos WHERE dni = ?");
            borrar.setLong(1, dni);
            borrar.executeUpdate();
        }
        catch (SQLException e)
        {
            Logger.getLogger("Problemas para borrar");
            System.out.println("Problemas para borrar");
        }
    }


    public void modificar_alumno(Alumno alumno)
    {
        String nombre = alumno.getNombre();
        String apellido = alumno.getApellido();
        long dni = alumno.getDni();
        long legajo = alumno.getLegajo();

        try
        {
            PreparedStatement modificar =
                conexion.prepareStatement("UPDATE Alumnos SET legajo=? , apellido=? , nombre = ?  WHERE dni = ?");
            modificar.setLong(1, legajo);
            modificar.setString(2, apellido);
            modificar.setString(3, nombre);
            modificar.setLong(4, dni);
            modificar.executeUpdate();

        }
        catch (SQLException e)
        {
            Logger.getLogger("Problemas para borrar");
            System.out.println("Problemas para borrar");
        }

    }


    public Iterator recuperar_alumnos()
    {
        Statement sentencia;
        ArrayList<Alumno> listado_alumnos = new ArrayList<Alumno>();
        long dni, legajo;
        String apellido, nombre;
        try
        {

            sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery("SELECT * FROM Alumnos ORDER BY apellido");
            while (resultado.next())
            {
                dni = resultado.getLong("dni");
                legajo = resultado.getLong("legajo");
                apellido = resultado.getString("apellido");
                nombre = resultado.getString("nombre");
                listado_alumnos.add(new Alumno(legajo, apellido, nombre, dni));
            }
        }
        catch (SQLException e)
        {
            System.out.println("Problemas en la recuperaci?n de los Alumnos");
        }
        return listado_alumnos.iterator();
    }


    // EVALUACION

    public void almacenar_evaluacion(Evaluacion ev)
    {
        int id_cursada = ev.getCursada().getId();
        Date fecha = ev.getFecha();
        String descripcion = ev.getDescripcion();
        byte[] arbol_serializado = ev.getArbol_perturbacion().serializar();

        int id_autoincrement = ev.getId_evaluacion();

        // Inserta en la tabla cursadas
        if (id_autoincrement != 0)
        {
            try
            {
                Blob blob = new javax.sql.rowset.serial.SerialBlob(arbol_serializado);
                PreparedStatement agregar =
                    conexion.prepareStatement("INSERT INTO evaluaciones (id_cursada,fecha,descripcion,arbol) VALUES (?,?,?,?)");
                agregar.setInt(1, id_cursada);
                agregar.setDate(2, fecha);
                agregar.setString(3, descripcion);
                agregar.setBlob(4, blob);
                agregar.executeUpdate();
            }
            catch (SQLException e)
            {
                Logger.getLogger("BaseDeDatos-->Problemas en la inserción");
                System.out.println(e.getMessage());
            }

            // Inserta los examenes en la tabla de examenes
            try
            {
                Blob blob = new javax.sql.rowset.serial.SerialBlob(arbol_serializado);
                PreparedStatement agregar =
                    conexion.prepareStatement("INSERT INTO examenes (dni_alumno,id_evaluacion,arbol) VALUES (?,?,?)");
                ;
                for (int i = 0; i < ev.getAlumnos_evaluados().size(); i++)
                {
                    //agregar = conexion.prepareStatement("INSERT INTO examenes (dni_alumno,id_evaluacion,arbol) VALUES (?,?,?)");
                    agregar.setLong(1, ev.getAlumnos_evaluados().get(i).getDni());
                    agregar.setInt(2, id_autoincrement);
                    agregar.setBlob(3, blob);
                    agregar.executeUpdate();
                }


            }
            catch (SQLException e)
            {
                Logger.getLogger("Problemas en la inserción");
                System.out.println("Problemas en la inserción de la evaluación");
            }
        }
    }


    public int recupera_proxima_evaluacion()
    {
        int id_autoincrement = 0;
        try
        {
            sentencia = conexion.createStatement();
            ResultSet resultado =
                sentencia.executeQuery("SELECT AUTO_INCREMENT FROM information_schema.TABLES WHERE TABLE_SCHEMA = 'modelo_del_estudiante' AND TABLE_NAME = 'evaluaciones'");
            resultado.next();
            id_autoincrement = resultado.getInt("auto_increment");


        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());

        }
        return id_autoincrement;
    }


    // CURSADA

    public void almacenar_cursada(Cursada cur)
    {
        String asignatura = cur.getAsignatura();
        int anio = cur.getAnio();
        int cuatrimestre = cur.getCuatrimestre();
        int id_autoincrement = 0;
        //Recupero el valor del proximo id autoincremental
        try
        {
            sentencia = conexion.createStatement();
            ResultSet resultado =
                sentencia.executeQuery("SELECT AUTO_INCREMENT FROM information_schema.TABLES WHERE TABLE_SCHEMA = 'modelo_del_estudiante' AND TABLE_NAME = 'cursadas'");
            resultado.next();
            id_autoincrement = resultado.getInt("auto_increment");
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        // Inserta en la tabla cursadas
        if (id_autoincrement != 0)
        {
            try
            {
                PreparedStatement agregar =
                    conexion.prepareStatement("INSERT INTO cursadas (asignatura,anio,cuatrimestre) VALUES (?,?,?)");
                agregar.setString(1, asignatura);
                agregar.setInt(2, anio);
                agregar.setInt(3, cuatrimestre);
                agregar.executeUpdate();
            }
            catch (SQLException e)
            {
                Logger.getLogger("Problemas en la inserción");
                System.out.println("Problemas en la inserción de la cursada");
            }

            // Inserta los alumnos en la tabla auxiliar
            try
            {
                PreparedStatement agregar;

                for (int i = 0; i < cur.getAlumnos().size(); i++)
                {
                    agregar =
                        conexion.prepareStatement("INSERT INTO aux_cursada_alumno (dni_alumno,id_cursada) VALUES (?,?)");

                    agregar.setLong(1, cur.getAlumnos().get(i).getDni());
                    agregar.setInt(2, id_autoincrement);
                    agregar.executeUpdate();
                }


            }
            catch (SQLException e)
            {
                Logger.getLogger("Problemas en la inserción");
                System.out.println("Problemas en la inserción de la cursada");
            }
        }
    }


    public void actualizar_examen(Evaluacion evaluacion, Examen examen)
    {
        byte[] arbol_serializado = examen.getArbol().serializar();

        Blob blob;
        PreparedStatement agregar;
        try
        {
            blob = new javax.sql.rowset.serial.SerialBlob(arbol_serializado);

            agregar = conexion.prepareStatement("UPDATE examenes SET arbol=? WHERE dni_alumno=? AND id_evaluacion=?");
            agregar.setBlob(1, blob);
            agregar.setLong(2, examen.getAlumno().getDni());
            agregar.setInt(3, evaluacion.getId_evaluacion());
            agregar.executeUpdate();


        }
        catch (SerialException e)
        {
        }
        catch (SQLException e)
        {
            Logger.getLogger("Problemas en la actualizacion del examen");
            System.out.println("Problemas en la actualizacion del examen");
        }
    }


    public Iterator recuperar_cursadas()
    {
        Statement sentencia;
        ArrayList<Cursada> listado_cursadas = new ArrayList<Cursada>();
        int id, anio, cuatrimestre;
        String asignatura;
        Cursada cursadaactual;
        try
        {
            sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery("SELECT * FROM cursadas");
            while (resultado.next())
            {
                id = resultado.getInt("id");
                asignatura = resultado.getString("asignatura");
                anio = resultado.getInt("anio");
                cuatrimestre = resultado.getInt("cuatrimestre");
                cursadaactual = new Cursada(id, asignatura, anio, cuatrimestre);
                cursadaactual.setAlumnos(this.recupera_Alumnos_Cursada(id));
                listado_cursadas.add(cursadaactual);
            }
        }
        catch (SQLException e)
        {
            System.out.println("Problemas en la recuperación de las cursadas");
        }
        return listado_cursadas.iterator();
    }

    private ArrayList<Alumno> recupera_Alumnos_Cursada(int id)
    {
        ArrayList<Alumno> listado_alumnos = new ArrayList<Alumno>();
        long dni, legajo;
        String apellido, nombre, sentenciasql =
            "SELECT * FROM Alumnos, aux_cursada_alumno WHERE id_cursada =" + id +
            " AND Alumnos.dni = aux_cursada_alumno.dni_alumno ORDER BY Alumnos.apellido";

        try
        {
            sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery(sentenciasql);
            while (resultado.next())
            {
                dni = resultado.getLong("dni");
                legajo = resultado.getLong("legajo");
                apellido = resultado.getString("apellido");
                nombre = resultado.getString("nombre");
                listado_alumnos.add(new Alumno(legajo, apellido, nombre, dni));

            }
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return listado_alumnos;
    }


    public void borrar_cursada(Cursada cur)
    {
        int id = cur.getId();
        try
        {
            PreparedStatement borrar = conexion.prepareStatement("DELETE FROM cursadas WHERE ID = ?");
            borrar.setInt(1, id);
            borrar.executeUpdate();
        }
        catch (SQLException e)
        {
            Logger.getLogger("Problemas para borrar");
            System.out.println("Problemas para borrar");
        }
        try
        {
            PreparedStatement borrar = conexion.prepareStatement("DELETE FROM aux_cursada_alumno WHERE ID_CURSADA = ?");
            borrar.setInt(1, id);
            borrar.executeUpdate();
        }
        catch (SQLException e)
        {
            Logger.getLogger("Problemas para borrar");
            System.out.println("Problemas para borrar");
        }

    }

    public void borrar_evaluacion(int id_evaluacion)
    {
        PreparedStatement borrar;
        try
        {
            borrar = conexion.prepareStatement("DELETE FROM evaluaciones where id_evaluacion = ?");
            borrar.setInt(1, id_evaluacion);
            borrar.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.println("exception en BaseDeDatos-> borrar_evaluacion(...)");
        }
    }

    public Iterator recuperar_evaluaciones()
    {
        Statement sentencia;
        ArrayList<Evaluacion> listado_evaluaciones = new ArrayList<Evaluacion>();
        int id_evaluacion, id_cursada;
        String descripcion;
        Evaluacion evaluacionactual;
        Date fecha;
        try
        {
            sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery("SELECT * FROM evaluaciones");
            while (resultado.next())
            {
                id_evaluacion = resultado.getInt("id_evaluacion");
                id_cursada = resultado.getInt("id_cursada");
                fecha = resultado.getDate("fecha");
                descripcion = resultado.getString("descripcion");

                //recupero el arbol de pertubacion blob
                Blob unBlob = resultado.getBlob("arbol");
                Arbol_Perturbacion arbol = this.blobToArbol(unBlob);
                unBlob.free();
                //recupero la cursada
                Cursada cursadaactual = null;
                cursadaactual = this.recuperar_cursada_por_Id(id_cursada);
                //recupero examenes
                ArrayList<Examen> examenes_tomados = this.recuperaExamenes(id_evaluacion);
                evaluacionactual =
                    new Evaluacion(examenes_tomados, cursadaactual, arbol, fecha, descripcion, id_evaluacion);
                listado_evaluaciones.add(evaluacionactual);
            }
        }
        catch (SQLException e)
        {
            System.out.println("Problemas en la recuperación de las cursadas");
        }
        return listado_evaluaciones.iterator();
    }


    public Cursada recuperar_cursada_por_Id(int id_buscado)
    {


        Statement sentencia;
        int id, anio, cuatrimestre;
        String asignatura;
        Cursada cursadaactual = null;
        String sentencia_sql = "SELECT * FROM cursadas WHERE ID=" + id_buscado;
        try
        {
            sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery(sentencia_sql);
            while (resultado.next())
            {
                id = resultado.getInt("id");
                asignatura = resultado.getString("asignatura");
                anio = resultado.getInt("anio");
                cuatrimestre = resultado.getInt("cuatrimestre");
                cursadaactual = new Cursada(id, asignatura, anio, cuatrimestre);
                cursadaactual.setAlumnos(this.recupera_Alumnos_Cursada(id));

            }
        }
        catch (SQLException e)
        {
            System.out.println("Problemas en la recuperación de las cursadas");
        }
        return cursadaactual;


    }

    private ArrayList<Examen> recuperaExamenes(int id)
    {
        ArrayList<Examen> listado_examenes = new ArrayList<Examen>();
        long dni, legajo;
        Blob unBlob;
        String apellido, nombre, sentenciasql =
            "SELECT * FROM Alumnos, examenes WHERE id_evaluacion =" + id +
            " AND Alumnos.dni = examenes.dni_alumno ORDER BY Alumnos.apellido";

        try
        {
            sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery(sentenciasql);

            while (resultado.next())
            {
                dni = resultado.getLong("dni");
                legajo = resultado.getLong("legajo");
                apellido = resultado.getString("apellido");
                nombre = resultado.getString("nombre");
                unBlob = resultado.getBlob("arbol");
                listado_examenes.add(new Examen(new Alumno(legajo, apellido, nombre, dni), this.blobToArbol(unBlob)));

            }
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return listado_examenes;
    }


}
