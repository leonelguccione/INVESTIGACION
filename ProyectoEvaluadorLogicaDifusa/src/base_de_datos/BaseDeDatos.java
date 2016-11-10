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

import javax.sql.rowset.serial.SerialException;

import modelo.Alumno;
import modelo.Arbol_Perturbacion;
import modelo.Asignatura;
import modelo.Cursada;
import modelo.Examen;
import modelo.Instancia_Evaluacion;
import modelo.Parcial;

public class BaseDeDatos {
    Connection conexion;
    Statement sentencia;
    ResultSet resultado;

    public BaseDeDatos() {
        super();
        inic();
    }

    void inic() {
        System.out.println("Iniciando programa.");

        // Se carga el driver JDBC-ODBC
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e)
        {
            System.out.println("No se pudo cargar el puente JDBC-ODBC.");
            return;
        }

        try
        {
            // Se establece la conexión con la base de datos
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/modelo_del_estudiante", "root", "");
        } catch (SQLException e)
        {
            try
            {
                // Se establece la conexión con la base de datos
                conexion =
                    DriverManager.getConnection("jdbc:mysql://localhost:3306/modelo_del_estudiante", "root", "leonel");
            } catch (SQLException f)
            {
            }
            System.out.println(e);
            return;
        }
        System.out.println("Creación finalizada.");
    }


    private Arbol_Perturbacion blobToArbol(Blob unBlob) throws SQLException {
        int blobLength;
        Arbol_Perturbacion arbol = null;

        blobLength = (int) unBlob.length();
        byte[] arbolSerializado = unBlob.getBytes(1, blobLength);
        arbol = Arbol_Perturbacion.deserializar(arbolSerializado);
        return arbol;
    }


    public void borrar_arbol_perturbacion(Arbol_Perturbacion arbol) throws SQLException {
        PreparedStatement borrar;

        borrar = conexion.prepareStatement("DELETE FROM ARBOL where nombre = ?");
        borrar.setString(1, arbol.getNombre());
        borrar.executeUpdate();
    }


    public void almacenar_asignatura_nueva(Asignatura asignatura) throws SQLException {
        String codigo_asignatura = asignatura.getCodigo();
        String nombre_asignatura = asignatura.getNombre();
        Arbol_Perturbacion arbol_dominio = asignatura.getArbol_dominio();
        Blob blob_arbol_dominio_serializado;
        if (arbol_dominio != null)
        {
            byte[] arbol_dominio_serializado = arbol_dominio.serializar();
            blob_arbol_dominio_serializado = new javax.sql.rowset.serial.SerialBlob(arbol_dominio_serializado);
        } else
            blob_arbol_dominio_serializado = null;

        PreparedStatement agregar =
            conexion.prepareStatement("insert into asignaturas(codigo, nombre, arbol_dominio)values(?,?,?)");
        agregar.setString(1, codigo_asignatura);
        agregar.setString(2, nombre_asignatura);
        agregar.setBlob(3, blob_arbol_dominio_serializado);
        agregar.executeUpdate();
    }

    //TODO: mejorar esto. Podría traer problemas si cada asignatura tuviera un identificador automático
    public void actualizar_arbol_perturbacion(Asignatura asignatura_en_uso) throws SerialException, SQLException {
        String codigo_asignatura = asignatura_en_uso.getCodigo();
        String nombre_asignatura = asignatura_en_uso.getNombre();
        Arbol_Perturbacion arbol_dominio = asignatura_en_uso.getArbol_dominio();
        byte[] arbol_dominio_serializado = arbol_dominio.serializar();
        Statement stmt;
        //borro arbol vacío, que se grabó para verlo en la lista de arboles
        this.borrar_asignatura(asignatura_en_uso);
        Blob blob_arbol_dominio_serializado = new javax.sql.rowset.serial.SerialBlob(arbol_dominio_serializado);
        PreparedStatement agregar =
            conexion.prepareStatement("INSERT INTO asignaturas(codigo, nombre, arbol_dominio)VALUES(?,?,?)");
        agregar.setString(1, codigo_asignatura);
        agregar.setString(2, nombre_asignatura);
        agregar.setBlob(3, blob_arbol_dominio_serializado);
        agregar.executeUpdate();
    }

    public void borrar_asignatura(Asignatura asignatura) throws SQLException {
        PreparedStatement borrar;
        borrar = conexion.prepareStatement("DELETE FROM asignaturas WHERE codigo = ?");
        borrar.setString(1, asignatura.getCodigo());
        borrar.executeUpdate();

    }

    public Iterator recuperar_asignaturas() throws SQLException {
        Statement sentencia;
        ArrayList<Asignatura> listado_asignaturas = new ArrayList<Asignatura>();

        sentencia = conexion.createStatement();
        ResultSet resultado = sentencia.executeQuery("SELECT * FROM asignaturas");
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
            Blob arbolDominio_blob = resultado.getBlob("arbol_dominio");
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

            Asignatura unaAsignatura = new Asignatura(nombre, codigo, arbol_dominio);
            listado_asignaturas.add(unaAsignatura);
        }

        return listado_asignaturas.iterator();
    }

    // ALUMNOS

    public void almacenar_alumno(Alumno alumno) throws SQLException {
        String nombre = alumno.getNombre();
        String apellido = alumno.getApellido();
        long dni = alumno.getDni();
        long legajo = alumno.getLegajo();
        PreparedStatement agregar =
            conexion.prepareStatement("INSERT INTO alumnos (dni,legajo,apellido,nombre) VALUES (?,?,?,?)");
        agregar.setLong(1, dni);
        agregar.setLong(2, legajo);
        agregar.setString(3, apellido);
        agregar.setString(4, nombre);
        agregar.executeUpdate();
    }


    public void borrar_alumno(Alumno alumno) throws SQLException {

        PreparedStatement borrar = conexion.prepareStatement("DELETE FROM alumnos WHERE dni = ?");
        borrar.setLong(1, alumno.getDni());
        borrar.executeUpdate();
    }


    public void modificar_alumno(Alumno alumno) throws SQLException {
        String nombre = alumno.getNombre();
        String apellido = alumno.getApellido();
        long dni = alumno.getDni();
        long legajo = alumno.getLegajo();

        PreparedStatement modificar =
            conexion.prepareStatement("UPDATE alumnos SET legajo=? , apellido=? , nombre = ?  WHERE dni = ?");
        modificar.setLong(1, legajo);
        modificar.setString(2, apellido);
        modificar.setString(3, nombre);
        modificar.setLong(4, dni);
        modificar.executeUpdate();
    }


    public Iterator recuperar_alumnos() throws SQLException {
        Statement sentencia;
        ArrayList<Alumno> listado_alumnos = new ArrayList<Alumno>();
        long dni, legajo;
        String apellido, nombre;
        sentencia = conexion.createStatement();
        ResultSet resultado = sentencia.executeQuery("SELECT * FROM alumnos ORDER BY apellido");
        while (resultado.next())
        {
            dni = resultado.getLong("dni");
            legajo = resultado.getLong("legajo");
            apellido = resultado.getString("apellido");
            nombre = resultado.getString("nombre");
            listado_alumnos.add(new Alumno(legajo, apellido, nombre, dni));
        }

        return listado_alumnos.iterator();
    }


    // EVALUACION

    public void almacenar_evaluacion(Parcial parcial, Instancia_Evaluacion ev) throws SQLException {
        Date fecha = ev.getFecha();
        String descripcion = ev.getDescripcion();
        int id_parcial = parcial.getId();
        int id_autoincrement = this.recupera_proxima_evaluacion();

        PreparedStatement agregar =
            conexion.prepareStatement("INSERT INTO instancias_evaluaciones (id_parcial,descripcion,fecha) VALUES (?,?,?)");
        agregar.setInt(1, id_parcial);

        agregar.setString(2, descripcion);
        agregar.setDate(3, fecha);
        agregar.executeUpdate();


        ArrayList<Examen> examenes = ev.getExamenes();

        for (int i = 0; i < examenes.size(); i++)
        {
            byte[] arbol_particular_serializado = examenes.get(i).getArbol_podado_particular().serializar();
            Blob blob = new javax.sql.rowset.serial.SerialBlob(arbol_particular_serializado);
            agregar =
                conexion.prepareStatement("INSERT INTO examenes (dni_alumno,id_instancia_ev,arbol_particular) VALUES (?,?,?)");
            agregar.setLong(1, examenes.get(i).getAlumno().getDni());
            agregar.setInt(2, id_autoincrement);
            agregar.setBlob(3, blob);
            agregar.executeUpdate();
        }
    }


    public int recupera_proxima_evaluacion() throws SQLException {
        int id_autoincrement = 0;

        sentencia = conexion.createStatement();
        ResultSet resultado =
            sentencia.executeQuery("SELECT AUTO_INCREMENT FROM information_schema.TABLES WHERE TABLE_SCHEMA = 'modelo_del_estudiante' AND TABLE_NAME = 'instancias_evaluaciones'");
        resultado.next();
        id_autoincrement = resultado.getInt("auto_increment");

        return id_autoincrement;
    }


    // CURSADA

    public void almacenar_cursada(Asignatura asignatura, Cursada cur) throws SQLException {
        //   String asignatura = cur.getAsignatura();
        String codigo_asignatura = asignatura.getCodigo();
        int anio = cur.getAnio_fecha();
        int cuatrimestre = cur.getCuatrimestre();
        int id_autoincrement = 0;
        //Recupero el valor del proximo id autoincremental
        sentencia = conexion.createStatement();
        ResultSet resultado =
            sentencia.executeQuery("SELECT AUTO_INCREMENT FROM information_schema.TABLES WHERE TABLE_SCHEMA = 'modelo_del_estudiante' AND TABLE_NAME = 'cursadas'");
        resultado.next();
        id_autoincrement = resultado.getInt("auto_increment");

        // Inserta en la tabla cursadas
        if (id_autoincrement != 0)
        {

            PreparedStatement agregar =
                conexion.prepareStatement("INSERT INTO cursadas (codigo_asignatura,anio,cuatrimestre) VALUES (?,?,?)");
            agregar.setString(1, codigo_asignatura);
            agregar.setInt(2, anio);
            agregar.setInt(3, cuatrimestre);
            agregar.executeUpdate();


            // Inserta los alumnos en la tabla auxiliar


            for (int i = 0; i < cur.getAlumnos().size(); i++)
            {
                agregar =
                    conexion.prepareStatement("INSERT INTO aux_cursada_alumno (dni_alumno,id_cursada) VALUES (?,?)");

                agregar.setLong(1, cur.getAlumnos().get(i).getDni());
                agregar.setInt(2, id_autoincrement);
                agregar.executeUpdate();
            }


        }
    }


    public void actualizar_examen(Examen examen) throws SQLException {
        byte[] arbol_serializado = examen.getArbol_podado_particular().serializar();
        Blob blob;
        PreparedStatement agregar;
        blob = new javax.sql.rowset.serial.SerialBlob(arbol_serializado);
        agregar = conexion.prepareStatement("UPDATE examenes SET arbol_particular=? WHERE id_examen=? ");
        agregar.setBlob(1, blob);
        agregar.setInt(2, examen.getId());
        agregar.executeUpdate();
    }


    public Iterator recuperar_cursadas(Asignatura asignatura) throws SQLException {
        Statement sentencia;
        ArrayList<Cursada> listado_cursadas = new ArrayList<Cursada>();
        int id, anio, cuatrimestre;
        String codigo_asignatura = asignatura.getCodigo();
        Cursada cursadaactual;

        String sentencia_SQL = "SELECT * FROM cursadas WHERE codigo_asignatura='" + codigo_asignatura + "'";
        sentencia = conexion.createStatement();
        ResultSet resultado = sentencia.executeQuery(sentencia_SQL);
        while (resultado.next())
        {
            id = resultado.getInt("id");
            codigo_asignatura = resultado.getString("codigo_asignatura");
            anio = resultado.getInt("anio");
            cuatrimestre = resultado.getInt("cuatrimestre");
            cursadaactual = new Cursada(id, anio, cuatrimestre);
            //cursadaactual.setAlumnos(this.recupera_Alumnos_Cursada(id));
            listado_cursadas.add(cursadaactual);
        }

        return listado_cursadas.iterator();
    }


    public ArrayList<Long> recupera_DNI_Alumnos_Cursada(Cursada cursada) throws SQLException {
        ArrayList<Long> listado_dni = new ArrayList<Long>();
        int id = cursada.getId();
        long dni, legajo;
        String apellido, nombre, sentenciasql = "SELECT * FROM aux_cursada_alumno WHERE id_cursada =" + id;
        sentencia = conexion.createStatement();
        ResultSet resultado = sentencia.executeQuery(sentenciasql);
        while (resultado.next())
        {
            dni = resultado.getLong("dni_alumno");
            listado_dni.add(dni);
        }
        return listado_dni;
    }


    public void borrar_cursada(Cursada cur) throws SQLException {
        int id = cur.getId();

        PreparedStatement borrar = conexion.prepareStatement("DELETE FROM cursadas WHERE ID = ?");
        borrar.setInt(1, id);
        borrar.executeUpdate();

        borrar = conexion.prepareStatement("DELETE FROM aux_cursada_alumno WHERE ID_CURSADA = ?");
        borrar.setInt(1, id);
        borrar.executeUpdate();


    }

    public void borrar_evaluacion(int id_evaluacion) throws SQLException {
        PreparedStatement borrar;

        borrar = conexion.prepareStatement("DELETE FROM evaluaciones where id_evaluacion = ?");
        borrar.setInt(1, id_evaluacion);
        borrar.executeUpdate();

    }

    public Iterator recuperar_evaluaciones(Parcial parcial) throws SQLException {
        Statement sentencia;
        ArrayList<Instancia_Evaluacion> listado_evaluaciones = new ArrayList<Instancia_Evaluacion>();
        int id_evaluacion;
        int id_parcial = parcial.getId();
        String descripcion;
        Instancia_Evaluacion evaluacionactual;
        Date fecha;

        sentencia = conexion.createStatement();
        String consulta = "SELECT * FROM instancias_evaluaciones WHERE id_parcial = " + id_parcial;
        ResultSet resultado = sentencia.executeQuery(consulta);

        while (resultado.next())
        {

            id_evaluacion = resultado.getInt("id");
            fecha = resultado.getDate("fecha");
            descripcion = resultado.getString("descripcion");

            //recupero examenes
            ArrayList<Examen> examenes_tomados = this.recuperaExamenes(id_evaluacion);
            evaluacionactual = new Instancia_Evaluacion(examenes_tomados, fecha, descripcion, id_evaluacion);
            listado_evaluaciones.add(evaluacionactual);
        }
        return listado_evaluaciones.iterator();
    }


    private ArrayList<Examen> recuperaExamenes(int id) throws SQLException {
        ArrayList<Examen> listado_examenes = new ArrayList<Examen>();
        long dni;
        int id_examen;
        Blob unBlob;
        String sentenciasql = "SELECT * FROM examenes WHERE id_instancia_ev=" + id;


        sentencia = conexion.createStatement();
        ResultSet resultado = sentencia.executeQuery(sentenciasql);

        while (resultado.next())
        {
            dni = resultado.getLong("dni_alumno");
            id_examen = resultado.getInt("id_examen");

            unBlob = resultado.getBlob("arbol_particular");
            listado_examenes.add(new Examen(id_examen, new Alumno(0, "", "", dni), this.blobToArbol(unBlob), false));

        }

        return listado_examenes;
    }

    public void almacenar_Parcial(Cursada cur, Parcial parcial) throws SQLException {
        int id_cursada = cur.getId();

        String nombre_parcial = parcial.getNombre();
        Arbol_Perturbacion arbol_podado = parcial.getArbol_podado();
        Statement stmt;
        byte[] arbol_dominio_serializado = arbol_podado.serializar();

        //borro arbol vacío, que se grabó para verlo en la lista de arboles
        //this.borrar_asignatura(asignatura);
        Blob blob_arbol_dominio_serializado = new javax.sql.rowset.serial.SerialBlob(arbol_dominio_serializado);

        PreparedStatement agregar =
            conexion.prepareStatement("insert into parciales(id_cursada, nombre, arbol_podado)values(?,?,?)");
        agregar.setInt(1, id_cursada);
        agregar.setString(2, nombre_parcial);
        agregar.setBlob(3, blob_arbol_dominio_serializado);
        agregar.executeUpdate();

    }


    public Iterator recuperar_parciales(Cursada cursada) throws SQLException {
        int id_cursada = cursada.getId();
        Statement sentencia;
        Parcial parcial_actual;
        ArrayList<Parcial> listado_parciales = new ArrayList<Parcial>();
        int id;
        String nombre;

        String sentencia_SQL = "SELECT * FROM parciales WHERE id_cursada=" + id_cursada;
        sentencia = conexion.createStatement();
        ResultSet resultado = sentencia.executeQuery(sentencia_SQL);
        Arbol_Perturbacion arbol_podado = null;
        //se recupera el arbol_dominio de la asignatura

        while (resultado.next())
        {
            id = resultado.getInt("id");
            nombre = resultado.getString("nombre");
            Blob arbolPodado_blob = resultado.getBlob("arbol_podado");
            if (arbolPodado_blob != null)
            {
                int blobLength = (int) arbolPodado_blob.length();
                byte[] arbolPodado_byte = arbolPodado_blob.getBytes(1, blobLength);
                arbol_podado = Arbol_Perturbacion.deserializar(arbolPodado_byte);
                arbolPodado_blob.free();
            }
            parcial_actual = new Parcial(id, nombre, arbol_podado);

            listado_parciales.add(parcial_actual);
        }

        return listado_parciales.iterator();


    }


    public void borrar_parcial(Parcial parcial) throws SQLException {
        PreparedStatement borrar;

        borrar = conexion.prepareStatement("DELETE FROM parciales where id = ?");
        borrar.setInt(1, parcial.getId());
        borrar.executeUpdate();

    }
}

