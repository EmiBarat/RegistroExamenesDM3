/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dtoModel.ExamenDto;
import dtoModel.ListadoAlumnosDTO;
import dtoModel.PruebasPromedioDTO;
import dtoModel.PruebasXIdExamenDTO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Alumno;
import model.Categoria;
import model.DetalleExamen;
import model.Examen;
import model.FactorSanguineo;
import model.Genero;
import model.Grado;
import model.GrupoSanguineo;
import model.Pesaje;
import model.Profesor;
import model.TipoDeEstadoPeso;
import model.TiposExamen;
import model.TipoPrueba;
import model.TipoUsuario;
import model.Usuario;

/**
 *
 * @author Emiliano Barat
 */
public class AccesoBaseDatos {

    public AccesoBaseDatos() {

        try {
            //paso obligatotio
            //Esta en driverClass en las propiedades de la conexion
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AccesoBaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String CONN = "jdbc:sqlserver://LAPTOP-AORGGV7T\\SQLEXPRESS:1433;databaseName=DM3_Examenes_EduFis";
    private String USER = "sa";
    private String PASS = "administrador";

    //----------LISTADOS----------
    //LISTAR PESAJES FILTRADOS
    public ArrayList<Pesaje> listarPesajesFiltrados(String filtro) {

        ArrayList<Pesaje> lista = new ArrayList<>();
        boolean seguimiento = false;
        try {
            Connection conn = DriverManager.getConnection(CONN, USER, PASS);

            String sql = "select *\n"
                    + "from pesajes p\n"
                    + "inner join alumnos a on a.idAlumno = p.idAlumno\n"
                    + "inner join tiposEstadosPeso tp on tp.idTipoEstadosPeso = p.idEstadoPeso " + filtro;

            PreparedStatement st = conn.prepareStatement(sql);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int idPesaje = rs.getInt(1);
                int idAlumno = rs.getInt(2);
                Alumno a = buscarAlumnoModel(idAlumno);
                int idEstadoPeso = rs.getInt(3);
                TipoDeEstadoPeso tipoEst = buscarEstadoPesoPorId(idEstadoPeso);
                String fechaPesaje = rs.getString(4);
                double peso = rs.getDouble(5);

                String obs = rs.getString(6);
                int bitSeguimiento = rs.getInt(7);
                if (bitSeguimiento != 0) {
                    seguimiento = true;
                }
                double imc = rs.getDouble(8);

                Pesaje p = new Pesaje(idPesaje, a, tipoEst, fechaPesaje, peso, obs, seguimiento, imc);
                lista.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    //LISTADOS DE SITUACIONES DE PESO
    public ArrayList<TipoDeEstadoPeso> listarEstadoPeso() {

        ArrayList<TipoDeEstadoPeso> lista = new ArrayList<>();

        try {

            Connection conn = DriverManager.getConnection(CONN, USER, PASS);

            String sql = "select *\n"
                    + "	from tiposEstadosPeso";
            PreparedStatement st = conn.prepareStatement(sql);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int idEstadoPeso = rs.getInt(1);
                String descripcion = rs.getString(2);
                String obs = rs.getString(3);
                double imcMin = rs.getDouble(4);
                double imcMax = rs.getDouble(5);

                TipoDeEstadoPeso estPeso = new TipoDeEstadoPeso(idEstadoPeso, descripcion, obs, imcMin, imcMax);
                lista.add(estPeso);
            }

            rs.close();
            st.close();
            conn.close();

        } catch (Exception e) {
        }

        return lista;

    }

    //LISTADO DE TODOS LOS PESAJES
    public ArrayList<Pesaje> listarPesoTodos() {

        ArrayList<Pesaje> lista = new ArrayList<>();
        boolean seguimiento = false;
        try {
            Connection conn = DriverManager.getConnection(CONN, USER, PASS);

            String sql = "select * from pesajes";

            PreparedStatement st = conn.prepareStatement(sql);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int idPesaje = rs.getInt(1);
                int idAlumno = rs.getInt(2);
                Alumno a = buscarAlumnoModel(idAlumno);
                int idEstadoPeso = rs.getInt(3);
                TipoDeEstadoPeso tipoEst = buscarEstadoPesoPorId(idEstadoPeso);
                String fechaPesaje = rs.getString(4);
                double peso = rs.getDouble(5);

                String obs = rs.getString(6);
                int bitSeguimiento = rs.getInt(7);
                if (bitSeguimiento != 0) {
                    seguimiento = true;
                }
                double imc = rs.getDouble(8);

                Pesaje p = new Pesaje(idPesaje, a, tipoEst, fechaPesaje, peso, obs, seguimiento, imc);
                lista.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    //listado de usuarios para administracion
    public ArrayList<Usuario> listarUsuarios() {
        ArrayList<Usuario> lista = new ArrayList<>();
        boolean activo = false;
        try {
            Connection conn = DriverManager.getConnection(CONN, USER, PASS);

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from usuarios u inner join tipoUsuario tu on tu.idtipousuario = u.idtipousuario");

            while (rs.next()) {

                int idUsuario = rs.getInt(1);
                int idTipoUsuario = rs.getInt(2);
                String nombreUsuario = rs.getString(3);
                String password = rs.getString(4);
                String pregSecreta = rs.getString(5);
                String respSecreta = rs.getString(6);
                int estado = rs.getInt(7);
                if (estado == 1) {
                    activo = true;
                }
                String fechaBaja = rs.getString(8);
                String fechaAlta = rs.getString(9);
                String tipoUsuario = rs.getString(11);
                String observaciones = rs.getString(12);

                TipoUsuario tu = new TipoUsuario(idTipoUsuario, tipoUsuario, observaciones);

                Usuario u = new Usuario(idUsuario, tu, nombreUsuario, password, pregSecreta, respSecreta, activo, fechaBaja, fechaAlta);
                lista.add(u);

            }

            rs.close();
            st.close();
            conn.close();
        } catch (Exception ex) {
            Logger.getLogger(AccesoBaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

    //listado de los tipos de usuario para el altaUsuario
    public ArrayList<TipoUsuario> listarTiposUsuarios() {
        ArrayList<TipoUsuario> lista = new ArrayList<>();

        try {
            Connection conn = DriverManager.getConnection(CONN, USER, PASS);

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from tipoUsuario");

            while (rs.next()) {

                int idTipoUsuario = rs.getInt(1);
                String tipoUsuario = rs.getString(2);
                String observaciones = rs.getString(3);

                TipoUsuario tu = new TipoUsuario(idTipoUsuario, tipoUsuario, observaciones);
                lista.add(tu);

            }

            rs.close();
            st.close();
            conn.close();
        } catch (Exception ex) {
            Logger.getLogger(AccesoBaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

    //Este metodo retorna una ArrayList con todos los DNI de los alumnos en la DB para
    //poder validar que el nuevo alumno a registrar no exixsta.
    public ArrayList<Integer> listarDni() {
        ArrayList<Integer> lista = new ArrayList<>();

        try {
            Connection conn = DriverManager.getConnection(CONN, USER, PASS);

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select matriculaIndividual from alumnos");

            while (rs.next()) {

                int dni = rs.getInt("matriculaIndividual");

                lista.add(dni);
            }

            rs.close();
            st.close();
            conn.close();
        } catch (Exception ex) {
            Logger.getLogger(AccesoBaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

    public ArrayList<Genero> listarGeneros() {
        ArrayList<Genero> lista = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(CONN, USER, PASS);

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select idGenero, descripcion from generos");

            while (rs.next()) {

                int idGenero = rs.getInt("idGenero");
                String descripcion = rs.getString("descripcion");

                Genero g = new Genero(idGenero, descripcion);
                lista.add(g);
            }

            rs.close();
            st.close();
            conn.close();
        } catch (Exception ex) {
            Logger.getLogger(AccesoBaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

    public ArrayList<TipoPrueba> listarTiposDePrueba() {
        ArrayList<TipoPrueba> lista = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(CONN, USER, PASS);

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select idPrueba, descripcion from tiposPruebas");

            while (rs.next()) {

                int idPrueba = rs.getInt("idPrueba");
                String descripcion = rs.getString("descripcion");

                TipoPrueba tp = new TipoPrueba(idPrueba, descripcion);
                lista.add(tp);
            }

            rs.close();
            st.close();
            conn.close();
        } catch (Exception ex) {
            Logger.getLogger(AccesoBaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

    public ArrayList<Grado> listarGrados() {
        ArrayList<Grado> lista = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(CONN, USER, PASS);

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select idGrado, descripcion from grados");

            while (rs.next()) {

                int idGrado = rs.getInt("idGrado");
                String descripcion = rs.getString("descripcion");

                Grado g = new Grado(idGrado, descripcion);
                lista.add(g);
            }

            rs.close();
            st.close();
            conn.close();
        } catch (Exception ex) {
            Logger.getLogger(AccesoBaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

    public ArrayList<GrupoSanguineo> listarGruposSanguineos() {
        ArrayList<GrupoSanguineo> lista = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(CONN, USER, PASS);

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select idGrupoSang, descripcion from gruposSanguineos");

            while (rs.next()) {

                int idGrupo = rs.getInt("idGrupoSang");
                String descripcion = rs.getString("descripcion");

                GrupoSanguineo g = new GrupoSanguineo(idGrupo, descripcion);
                lista.add(g);
            }

            rs.close();
            st.close();
            conn.close();
        } catch (Exception ex) {
            Logger.getLogger(AccesoBaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

    public ArrayList<FactorSanguineo> listarFactorSanguineos() {
        ArrayList<FactorSanguineo> lista = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(CONN, USER, PASS);

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select idFactor, factor from factoresSanguineos");

            while (rs.next()) {

                int idFactorSanguineo = rs.getInt("idFactor");
                String factor = rs.getString("factor");

                FactorSanguineo f = new FactorSanguineo(idFactorSanguineo, factor);
                lista.add(f);
            }

            rs.close();
            st.close();
            conn.close();
        } catch (Exception ex) {
            Logger.getLogger(AccesoBaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

    //LISTAR SIEMPRE SE USA EN EL LISTADO DE PROFESORES EN EL MENU DE ADMINISTRACION,
    //PARA PODER VERLOS INCLUSO CUANDO ESTAN DE BAJA... SINO COMO SE LOS PUEDE DAL NUEVAMENTE DE ALTA??
    public ArrayList<Profesor> listarProfesoresSiempre() {
        ArrayList<Profesor> lista = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(CONN, USER, PASS);

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select idProfesor, idGrado, nombre, apellido, codigoEstadistico, matriculaIndividual, fechaBaja from profesores");

            while (rs.next()) {

                int idProfesor = rs.getInt("idProfesor");
                int idGrado = rs.getInt("idGrado");
                int codigoEstadistico = rs.getInt("codigoEstadistico");
                int matriculaIndividual = rs.getInt("matriculaIndividual");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                Date fechaBaja = rs.getDate("fechaBaja");

                Profesor p = new Profesor(idProfesor, idGrado, nombre, apellido, codigoEstadistico, matriculaIndividual, fechaBaja);
                lista.add(p);
            }

            rs.close();
            st.close();
            conn.close();
        } catch (Exception ex) {
            Logger.getLogger(AccesoBaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

    public ArrayList<Profesor> listarProfesores() {
        ArrayList<Profesor> lista = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(CONN, USER, PASS);

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select idProfesor, idGrado, nombre, apellido, codigoEstadistico, matriculaIndividual, fechaBaja from profesores where fechaBaja is null");

            while (rs.next()) {

                int idProfesor = rs.getInt("idProfesor");
                int idGrado = rs.getInt("idGrado");
                int codigoEstadistico = rs.getInt("codigoEstadistico");
                int matriculaIndividual = rs.getInt("matriculaIndividual");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                Date fechaBaja = rs.getDate("fechaBaja");

                Profesor p = new Profesor(idProfesor, idGrado, nombre, apellido, codigoEstadistico, matriculaIndividual, fechaBaja);
                lista.add(p);
            }

            rs.close();
            st.close();
            conn.close();
        } catch (Exception ex) {
            Logger.getLogger(AccesoBaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

    public ArrayList<TiposExamen> listarTiposExamen() {
        ArrayList<TiposExamen> lista = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(CONN, USER, PASS);

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select idTipoExamen, descripcion, observaciones from tiposExamen");

            while (rs.next()) {

                int idTipoExamen = rs.getInt("idTipoExamen");
                String descripcion = rs.getString("descripcion");
                String observaciones = rs.getString("observaciones");

                TiposExamen t = new TiposExamen(idTipoExamen, descripcion, observaciones);
                lista.add(t);

            }

            rs.close();
            st.close();
            conn.close();
        } catch (Exception ex) {
            Logger.getLogger(AccesoBaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

    public ArrayList<Categoria> listarCategorias() {
        ArrayList<Categoria> lista = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(CONN, USER, PASS);

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select idCategoria, descripcion, edadMinima, edadMaxima from categorias");

            while (rs.next()) {

                int idCategoria = rs.getInt("idCategoria");
                int edadMinima = rs.getInt("edadMinima");
                int edadMaxima = rs.getInt("edadMaxima");
                String descripcion = rs.getString("descripcion");

                Categoria c = new Categoria(idCategoria, descripcion, edadMinima, edadMaxima);
                lista.add(c);

            }

            rs.close();
            st.close();
            conn.close();
        } catch (Exception ex) {
            Logger.getLogger(AccesoBaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

    //LISTAR TODOS LOS EXAMENES
    public ArrayList<ExamenDto> listarTodosExamenes() {
        ArrayList<ExamenDto> lista = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(CONN, USER, PASS);

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select e.idExamen, te.descripcion as tipoPrueba, e.fechaExamen, p.apellido as apellidoProfe, p.nombre as nombreProfe, a.apellido, a.nombres,\n"
                    + "                    		 e.notaFinal, e.observaciones, c.descripcion as descripCateg\n"
                    + "                    	from examenes e\n"
                    + "                    	inner join tiposExamen te on e.idTipoExamen = te.idTipoExamen\n"
                    + "                    	inner join profesores p on e.encargadoExamen = p.idProfesor\n"
                    + "                    	inner join alumnos a on e.idAlumno = a.idAlumno\n"
                    + "                    	inner join categorias c on e.idCategoria = c.idCategoria\n");

            while (rs.next()) {

                int idExamen = rs.getInt("idExamen");
                String tipoExamen = rs.getString("tipoPrueba");
                String fechaExamen = rs.getString("fechaExamen");
                String nombreProfe = rs.getString("nombreProfe");
                String apellidoProfe = rs.getString("apellidoProfe");
                String nombreAlumno = rs.getString("nombres");
                String apellidoAlumno = rs.getString("apellido");
                double nota = rs.getDouble("notafinal");
                String observaciones = rs.getString("observaciones");
                String categ = rs.getString("descripCateg");

                ExamenDto e = new ExamenDto(idExamen, tipoExamen, fechaExamen, nombreProfe, apellidoProfe, nombreAlumno, apellidoAlumno, nota, observaciones, categ);
                lista.add(e);
            }

            rs.close();
            st.close();
            conn.close();
        } catch (Exception ex) {
            Logger.getLogger(AccesoBaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

    //LISTAR EXAMENES FILTRADOS
    public ArrayList<ExamenDto> listarExamenesFiltrados(String sqlWhereTxt) {
        ArrayList<ExamenDto> lista = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(CONN, USER, PASS);

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select e.idExamen, te.descripcion as tipoPrueba, e.fechaExamen, p.apellido as apellidoProfe, p.nombre as nombreProfe, a.apellido, a.nombres,\n"
                    + "                    		 e.notaFinal, e.observaciones, c.descripcion as descripCateg\n"
                    + "                    	from examenes e\n"
                    + "                    	inner join tiposExamen te on e.idTipoExamen = te.idTipoExamen\n"
                    + "                    	inner join profesores p on e.encargadoExamen = p.idProfesor\n"
                    + "                    	inner join alumnos a on e.idAlumno = a.idAlumno\n"
                    + "                    	inner join categorias c on e.idCategoria = c.idCategoria\n" + sqlWhereTxt);

            while (rs.next()) {

                int idExamen = rs.getInt("idExamen");
                String tipoExamen = rs.getString("tipoPrueba");
                String fechaExamen = rs.getString("fechaExamen");
                String nombreProfe = rs.getString("nombreProfe");
                String apellidoProfe = rs.getString("apellidoProfe");
                String nombreAlumno = rs.getString("nombres");
                String apellidoAlumno = rs.getString("apellido");
                double nota = rs.getDouble("notafinal");
                String observaciones = rs.getString("observaciones");
                String categ = rs.getString("descripCateg");

                ExamenDto e = new ExamenDto(idExamen, tipoExamen, fechaExamen, nombreProfe, apellidoProfe, nombreAlumno, apellidoAlumno, nota, observaciones, categ);
                lista.add(e);
            }

            rs.close();
            st.close();
            conn.close();
        } catch (Exception ex) {
            Logger.getLogger(AccesoBaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

    //LISTAR ALUMNOS FILTRADOS
    public ArrayList<ListadoAlumnosDTO> listarAlumnosFiltrados(String sqlWhereTxt) {
        ArrayList<ListadoAlumnosDTO> lista = new ArrayList<>();
        Alumno a = new Alumno();
        Categoria c = new Categoria();
        try {
            Connection conn = DriverManager.getConnection(CONN, USER, PASS);

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select a.idAlumno, gr.descripcion, a.apellido, a.nombres, a.fechaNac, a.matriculaIndividual, a.codigoEstadistico, g.idGenero, g.descripcion genero\n"
                    + "	from alumnos a\n"
                    + "	inner join grados gr on gr.idGrado = a.idGrado\n"
                    + "	inner join generos g on g.idGenero = a.idGenero " + sqlWhereTxt);

            while (rs.next()) {

                int idAlumno = rs.getInt("idAlumno");
                String grado = rs.getString("descripcion");
                String apellido = rs.getString("apellido");
                String nombre = rs.getString("nombres");

                //tomar una Date y devolver un String
                java.util.Date fechaNac = new java.util.Date();
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

                fechaNac = rs.getDate("fechaNac");
                String fechaNacim = dateFormat.format(fechaNac);

                int dni = rs.getInt("matriculaIndividual");
                int codigoEstadistico = rs.getInt("codigoEstadistico");
                int idGenero = rs.getInt("idGenero");
                String genero = rs.getNString("genero");

                int idcateg = a.calcularCategConSql(fechaNacim);

                //Como categoria no se relaciona con el Alumno en la DB, no puedo traer la categoria de cada uno.
                //Con este mamarracho, logro traer la categoria usando la fecha de nacimiento y un metodo de clase Alumno 
                //que me dice que id de categoria es.
                String sql = "select idCategoria, descripcion descrCategoria, edadMinima, edadMaxima\n"
                        + "                        from categorias\n"
                        + "                        where idCategoria = ?";
                PreparedStatement st2 = conn.prepareStatement(sql);

                st2.setString(1, String.valueOf(idcateg));
                ResultSet rs2 = st2.executeQuery();

                int cat, edadMin, edadMax;

                if (rs2.next()) {
                    cat = rs2.getInt("idCategoria");
                    String nombreCat = rs2.getString("descrCategoria");
                    edadMin = rs2.getInt("edadMinima");
                    edadMax = rs2.getInt("edadMaxima");

                    c = new Categoria(cat, nombreCat, edadMin, edadMax);
                }

                ListadoAlumnosDTO dto = new ListadoAlumnosDTO(idAlumno, grado, apellido, nombre, fechaNacim, dni, codigoEstadistico, idGenero, genero, c);
                lista.add(dto);

            }

            rs.close();
            st.close();
            conn.close();
        } catch (Exception ex) {
            Logger.getLogger(AccesoBaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

    //Listar los pesajes de un alumno
    public ArrayList<Pesaje> buscarPesoPorAlumno(int idAlumno) {

        ArrayList<Pesaje> lista = new ArrayList<>();
        boolean seguimiento = false;
        try {
            Connection conn = DriverManager.getConnection(CONN, USER, PASS);

            String sql = "select * from pesajes where idAlumno = ?";

            PreparedStatement st = conn.prepareStatement(sql);

            st.setString(1, String.valueOf(idAlumno));
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int idPesaje = rs.getInt(1);
//                int idAlumno = rs.getInt(2);
                Alumno a = buscarAlumnoModel(idAlumno);
                int idEstadoPeso = rs.getInt(3);
                TipoDeEstadoPeso tipoEst = buscarEstadoPesoPorId(idEstadoPeso);
                String fechaPesaje = rs.getString(4);
                double peso = rs.getDouble(5);

                String obs = rs.getString(6);
                int bitSeguimiento = rs.getInt(7);
                if (bitSeguimiento != 0) {
                    seguimiento = true;
                }
                double imc = rs.getDouble(8);

                Pesaje p = new Pesaje(idPesaje, a, tipoEst, fechaPesaje, peso, obs, seguimiento, imc);
                lista.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    //Listar los examenes de un alumno
    public ArrayList<ExamenDto> buscarExamenPorAlumno(int idAlumno, String sqlWhere) {

        ArrayList<ExamenDto> lista = new ArrayList<>();

        try {
            Connection conn = DriverManager.getConnection(CONN, USER, PASS);

            String sql = "select e.idExamen, te.descripcion as tipoPrueba, e.fechaExamen, p.apellido as apellidoProfe, p.nombre as nombreProfe, a.apellido, a.nombres,\n"
                    + "                    		 e.notaFinal, e.observaciones, c.descripcion as descripCateg\n"
                    + "                    	from examenes e\n"
                    + "                    	inner join tiposExamen te on e.idTipoExamen = te.idTipoExamen\n"
                    + "                    	inner join profesores p on e.encargadoExamen = p.idProfesor\n"
                    + "                    	inner join alumnos a on e.idAlumno = a.idAlumno\n"
                    + "                    	inner join categorias c on e.idCategoria = c.idCategoria\n"
                    + "                    	where a.idAlumno = ? " + sqlWhere;

            PreparedStatement st = conn.prepareStatement(sql);

            st.setString(1, String.valueOf(idAlumno));
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int idExam = rs.getInt("idExamen");
                String tipoPrueba = rs.getString("tipoPrueba");
                String fechaExam = rs.getString("fechaExamen");
                String nombreProfe = rs.getString("nombreProfe");
                String apellidoProfe = rs.getString("apellidoProfe");
                String apellidoAlum = rs.getString("apellido");
                String nombreAlum = rs.getString("nombres");
                double nota = rs.getDouble("notaFinal");
                String obs = rs.getString("observaciones");
                String categoria = rs.getString("descripCateg");

                ExamenDto ex = new ExamenDto(idExam, tipoPrueba, fechaExam, nombreProfe, apellidoProfe, nombreAlum, apellidoAlum, nota, obs, categoria);
                lista.add(ex);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

//-------------INSERTS---------------------
    //INSERT DE NUEVO PROFESOR
    public void registrarProfesor(Profesor p) {

        try {
            Connection conn = DriverManager.getConnection(CONN, USER, PASS);

            String sql = "insert into profesores (idGrado, nombre, apellido, codigoEstadistico, matriculaIndividual)\n"
                    + "values (?, ?, ?, ?, ?)";

            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, p.getIdGrado());
            st.setString(2, p.getNombre());
            st.setString(3, p.getApellido());
            st.setInt(4, p.getCodigoEstadistico());
            st.setInt(5, p.getMatriculaIndividual());

            st.executeUpdate();

            st.close();
            conn.close();

        } catch (Exception ex) {
        }
    }

    //INSERT DE NUEVO USUARIO
    public void registrarPeso(Pesaje p) {
        int seguimiento = 0;
        try {
            Connection conn = DriverManager.getConnection(CONN, USER, PASS);

            String sql = "insert into pesajes (idAlumno, idEstadoPeso, fechaPesaje, pesoEnKilos, observaciones, bajoSeguimiento, IMC)\n"
                    + "			values (?,?,?,?,?,?,?)";

            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, p.getAlumno().getIdAlumno());
            st.setInt(2, p.getEstadoPeso().getIdTipoEstadoPeso());
            st.setString(3, p.getFechaPesaje());
            st.setDouble(4, p.getPesoEnKilos());
            st.setString(5, p.getObservaciones());
            if (p.isBajoSeguimiento()) {
                seguimiento = 1;
            }
            st.setInt(6, seguimiento);
            st.setDouble(7, p.getIndiceMasaCorporal());

            st.executeUpdate();

            st.close();
            conn.close();

        } catch (Exception ex) {
        }
    }

    public void registrarUsuario(Usuario u) {
        int activo = 0;
        try {
            Connection conn = DriverManager.getConnection(CONN, USER, PASS);

            String sql = "insert into usuarios (idTipoUsuario, nombreUsuario, password, preguntaSecreta, respuestaSecreta, activo, fechaAlta)\n"
                    + "values (?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, u.getTipoUsuario().getIdTipoUsuario());
            st.setString(2, u.getNombreUsuario());
            st.setString(3, u.getPassword());
            st.setString(4, u.getPreguntaSecreta());
            st.setString(5, u.getRespuestaSecreta());
            if (u.isActivo()) {
                activo = 1;
            }
            st.setInt(6, activo);
            st.setString(7, u.getFechaAlta());

            st.executeUpdate();

            st.close();
            conn.close();

        } catch (Exception ex) {
        }
    }

    //INSERT EN DETALLE_EXAMEN
    public void registrarDetalleExamen(DetalleExamen de) {
        try {
            Connection conn = DriverManager.getConnection(CONN, USER, PASS);

            String sql = "insert into detalleExamenes (idExamen, idPrueba, fechaPrueba, resultado, puntajeObtenido, idProfesor, observaciones)\n"
                    + "values(?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, de.getIdExamen());
            st.setInt(2, de.getPrueba());
            st.setString(3, de.getFechaPrueba());
            st.setDouble(4, de.getResultado());
            st.setInt(5, de.getPuntaje());
            st.setInt(6, de.getProdesor());
            st.setString(7, de.getObservaciones());

            st.executeUpdate();

            st.close();
            conn.close();

        } catch (Exception ex) {
        }
    }

    //metodo de carga de alumnos nuevos
    public void nuevoAlumno(Alumno a) {

        try {
            Connection conn = DriverManager.getConnection(CONN, USER, PASS);

            String sql = "INSERT INTO alumnos (idGrado, nombres, apellido, fechaNac, codigoEstadistico, matriculaIndividual, nroIOSFA, grupoSanguineo, factorSanguineo, obsSanitaria, obsGenerales, tallaEnMetros, idGenero, fechaAlta)\n"
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, a.getGrado());
            st.setString(2, a.getNombres());
            st.setString(3, a.getApellidos());
            st.setString(4, a.getFechaNacimiento());
            st.setInt(5, a.getCodigoEstadistico());
            st.setInt(6, a.getMatriculaIndividual());
            st.setInt(7, a.getNroIOSFA());
            st.setInt(8, a.getGrupoSanguineo());
            st.setInt(9, a.getFactorSanguineo());
            st.setString(10, a.getObsSanitarias());
            st.setString(11, a.getObsGenerales());
            st.setDouble(12, a.getTallaEnMetros());
            st.setInt(13, a.getGenero());
            st.setString(14, a.getFechaAlta());

            st.executeUpdate();

            st.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //cargar de nuevo examen sin la nota... estoy intentando pasarle tambien la nota!!
    public void registrarExamen(Examen e) {
//        try {
//            Connection conn = DriverManager.getConnection(CONN, USER, PASS);
//
//            String sql = "insert into examenes (idTipoExamen, fechaExamen, encargadoExamen, idAlumno, observaciones, idCategoria)\n"
//                    + "			values (?, ?, ?, ?, ?, ?)";
//
//            PreparedStatement st = conn.prepareStatement(sql);
//            st.setInt(1, e.getTipoExamen());
//            st.setString(2, e.getFechaExamen());
//            st.setInt(3, e.getIdProfesor());
//            st.setInt(4, e.getIdAlumno());
//            st.setString(5, e.getObservaciones());
//            st.setInt(6, e.getIdCategoria());
//
//            st.executeUpdate();
//
//            st.close();
//            conn.close();
//
//        } catch (Exception ex) {
//        }

        try (Connection conn = DriverManager.getConnection(CONN, USER, PASS)) {

            conn.setAutoCommit(false);
            int idExamen = 0;

            String sql = "insert into examenes (idTipoExamen, fechaExamen, encargadoExamen, idAlumno, observaciones, idCategoria, notaFinal)\n"
                    + "			values (?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            st.setInt(1, e.getTipoExamen());
            st.setString(2, e.getFechaExamen());
            st.setInt(3, e.getIdProfesor());
            st.setInt(4, e.getIdAlumno());
            st.setString(5, e.getObservaciones());
            st.setInt(6, e.getIdCategoria());
            st.setInt(7, e.calcularNotaFinal());

            st.execute();
            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                idExamen = rs.getInt(1);
            }

            for (DetalleExamen de : e.getListaDetalleExamen()) {
                String sqlDE = "insert into detalleExamenes (idExamen, idPrueba, fechaPrueba, resultado, puntajeObtenido, idProfesor, observaciones)\n"
                        + "values(?, ?, ?, ?, ?, ?, ?)";

                PreparedStatement stDE = conn.prepareStatement(sqlDE);
                stDE.setInt(1, idExamen);
                stDE.setInt(2, de.getPrueba());
                stDE.setString(3, de.getFechaPrueba());
                stDE.setDouble(4, de.getResultado());
                stDE.setInt(5, de.getPuntaje());
                stDE.setInt(6, de.getProdesor());
                stDE.setString(7, de.getObservaciones());
                stDE.execute();
            }

            conn.commit();

        } catch (Exception ex) {

        }
    }

    //-------------------Buscadores-------------------------------------
    public TipoUsuario buscarTipoUsuario(int idUsuario) {

        TipoUsuario tu = new TipoUsuario();

        try {
            Connection conn = DriverManager.getConnection(CONN, USER, PASS);

            String sql = "select * from tipoUsuario where idTipoUsuario = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, idUsuario);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                String tipoUser = rs.getString(2);
                String obs = rs.getString(3);
                tu = new TipoUsuario(idUsuario, tipoUser, obs);

            }

        } catch (Exception e) {
        }

        return tu;
    }

    public int buscarGeneroDeAlumno(int idAlumno) {

        int idGenero = 0;

        try {

            Connection conn = DriverManager.getConnection(CONN, USER, PASS);

            String sql = "select idGenero\n"
                    + "	from alumnos\n"
                    + "	where idAlumno = ?";
            PreparedStatement st = conn.prepareStatement(sql);

            st.setInt(1, idAlumno);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                idGenero = rs.getInt("idGenero");

            }

            rs.close();
            st.close();
            conn.close();

        } catch (Exception e) {
        }

        return idGenero;

    }

    public TipoDeEstadoPeso buscarEstadoPeso(double imc) {

        TipoDeEstadoPeso estPeso = null;

        try {

            Connection conn = DriverManager.getConnection(CONN, USER, PASS);

            String sql = "select *\n"
                    + "	from tiposEstadosPeso\n"
                    + "	where ? between imcmin and imcmax";
            PreparedStatement st = conn.prepareStatement(sql);

            st.setDouble(1, imc);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                int idEstadoPeso = rs.getInt(1);
                String descripcion = rs.getString(2);
                String obs = rs.getString(3);
                double imcMin = rs.getDouble(4);
                double imcMax = rs.getDouble(5);

                estPeso = new TipoDeEstadoPeso(idEstadoPeso, descripcion, obs, imcMin, imcMax);
            }

            rs.close();
            st.close();
            conn.close();

        } catch (Exception e) {
        }

        return estPeso;

    }

    public TipoDeEstadoPeso buscarEstadoPesoPorId(int idEstado) {

        TipoDeEstadoPeso estPeso = null;

        try {

            Connection conn = DriverManager.getConnection(CONN, USER, PASS);

            String sql = "select *\n"
                    + "	from tiposEstadosPeso\n"
                    + "	where idTipoEstadosPeso = ?";
            PreparedStatement st = conn.prepareStatement(sql);

            st.setInt(1, idEstado);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                int idEstadoPeso = rs.getInt(1);
                String descripcion = rs.getString(2);
                String obs = rs.getString(3);
                double imcMin = rs.getDouble(4);
                double imcMax = rs.getDouble(5);

                estPeso = new TipoDeEstadoPeso(idEstadoPeso, descripcion, obs, imcMin, imcMax);
            }

            rs.close();
            st.close();
            conn.close();

        } catch (Exception e) {
        }

        return estPeso;

    }

    public int buscarResultadoPrueba(int idExamen, int idPrueba) {
        int result = 0;

        try {

            Connection conn = DriverManager.getConnection(CONN, USER, PASS);

            String sql = "select de.resultado\n"
                    + "from detalleExamenes de\n"
                    + "where idExamen = ? and idPrueba = ?";
            PreparedStatement st = conn.prepareStatement(sql);

            st.setInt(1, idExamen);
            st.setInt(2, idPrueba);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                result = rs.getInt("resultado");

            }

            rs.close();
            st.close();
            conn.close();

        } catch (Exception e) {
        }

        return result;
    }

    public ExamenDto buscarExamen(int idExamen) {

        ExamenDto examDto = new ExamenDto();

        try {
            Connection conn = DriverManager.getConnection(CONN, USER, PASS);

            String sql = "select e.idExamen, te.descripcion as tipoPrueba, e.fechaExamen, p.apellido as apellidoProfe, p.nombre as nombreProfe, a.apellido, a.nombres,\n"
                    + "                    		 e.notaFinal, e.observaciones, c.descripcion as descripCateg\n"
                    + "                    	from examenes e\n"
                    + "                    	inner join tiposExamen te on e.idTipoExamen = te.idTipoExamen\n"
                    + "                    	inner join profesores p on e.encargadoExamen = p.idProfesor\n"
                    + "                    	inner join alumnos a on e.idAlumno = a.idAlumno\n"
                    + "                    	inner join categorias c on e.idCategoria = c.idCategoria\n"
                    + "                    	where idExamen = ?";

            PreparedStatement st = conn.prepareStatement(sql);

            st.setString(1, String.valueOf(idExamen));
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int idExam = rs.getInt("idExamen");
                String tipoPrueba = rs.getString("tipoPrueba");
                String fechaExam = rs.getString("fechaExamen");
                String nombreProfe = rs.getString("nombreProfe");
                String apellidoProfe = rs.getString("apellidoProfe");
                String apellidoAlum = rs.getString("apellido");
                String nombreAlum = rs.getString("nombres");
                double nota = rs.getDouble("notaFinal");
                String obs = rs.getString("observaciones");
                String categoria = rs.getString("descripCateg");

                examDto = new ExamenDto(idExam, tipoPrueba, fechaExam, nombreProfe, apellidoProfe, nombreAlum, apellidoAlum, nota, obs, categoria);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return examDto;
    }

    public Examen buscarExamenModel(int idExamen) {
        Examen examen = new Examen();
        try {
            Connection conn = DriverManager.getConnection(CONN, USER, PASS);

            String sql = "select	e.idExamen, e.idTipoExamen, e.fechaExamen, e.encargadoExamen, \n"
                    + "                 e.idAlumno, e.notaFinal, e.observaciones, e.idCategoria\n"
                    + "         from examenes e\n"
                    + "	where idExamen = ?";

            PreparedStatement st = conn.prepareStatement(sql);

            st.setString(1, String.valueOf(idExamen));
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int idExam = rs.getInt("idExamen");
                int idTipoExam = rs.getInt("idTipoExamen");
                int idProfe = rs.getInt("encargadoExamen");
                int idAlumno = rs.getInt("idAlumno");
                int idCateg = rs.getInt("idCategoria");
                String fechaExam = rs.getString("fechaExamen");

                double nota = rs.getDouble("notaFinal");
                String obs = rs.getString("observaciones");

                examen = new Examen(idTipoExam, fechaExam, idProfe, idAlumno, obs, idCateg);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return examen;

    }

    //buscar un pesaje segun idPesaje
    public Pesaje buscarPesaje(int idPesaje) {
        Pesaje p = new Pesaje();
        boolean seguimiento = false;
        try {
            Connection conn = DriverManager.getConnection(CONN, USER, PASS);
            String sql = "SELECT * FROM pesajes WHERE idPesaje = ?";

            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, idPesaje);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                int id = rs.getInt(1);
                Alumno a = buscarAlumnoModel(rs.getInt(2));
                int idEstadoPeso = rs.getInt(3);
                TipoDeEstadoPeso tipoEst = buscarEstadoPesoPorId(idEstadoPeso);
                String fechaPesaje = rs.getString(4);
                double peso = rs.getDouble(5);

                String obs = rs.getString(6);
                int bitSeguimiento = rs.getInt(7);
                if (bitSeguimiento != 0) {
                    seguimiento = true;
                }
                double imc = rs.getDouble(8);

                p = new Pesaje(id, a, tipoEst, fechaPesaje, peso, obs, seguimiento, imc);

            }

        } catch (Exception e) {
        }
        return p;
    }

    //buscar el idExamen del ultimo registro ingresado
    public int buscarIdUltimoExamen() {

        int ultimoId = 0;
        try {
            Connection conn = DriverManager.getConnection(CONN, USER, PASS);

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select top 1 idExamen\n"
                    + "	from examenes\n"
                    + "	order by idExamen desc");

            if (rs.next()) {
                ultimoId = rs.getInt(1);
            }

        } catch (Exception e) {
        }
        return ultimoId;
    }

    //calcular la edad usando SQL Server
    public int buscarEdad(String fechaNac) {
        int edad = 0;

        try {

            Connection conn = DriverManager.getConnection(CONN, USER, PASS);

            String sql = "select datediff(year, ? , getdate()) as edad";
            PreparedStatement st = conn.prepareStatement(sql);

            st.setString(1, fechaNac);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                edad = rs.getInt("edad");

            }

            rs.close();
            st.close();
            conn.close();

        } catch (Exception e) {
        }

        return edad;
    }

    //buscar la categoria
    public int buscarCategoria(int edad) {
        int cat = 0;

        try {

            Connection conn = DriverManager.getConnection(CONN, USER, PASS);

            String sql = "select idCategoria\n"
                    + "	from categorias c\n"
                    + "	where ? between c.edadMinima and c.edadMaxima";
            PreparedStatement st = conn.prepareStatement(sql);

            st.setString(1, String.valueOf(edad));
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                cat = rs.getInt("idCategoria");

            }

            rs.close();
            st.close();
            conn.close();

        } catch (Exception e) {
        }

        return cat;
    }

    //BUSCAR EL GENERO SEGUN ID
    public Genero buscarGenero(int idGenero) {
        Genero g = new Genero();

        try {

            Connection conn = DriverManager.getConnection(CONN, USER, PASS);

            String sql = "select descripcion from generos where idGenero = ?";
            PreparedStatement st = conn.prepareStatement(sql);

            st.setString(1, String.valueOf(idGenero));
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                String genero = rs.getString("descripcion");
                g = new Genero(idGenero, genero);
            }

            rs.close();
            st.close();
            conn.close();

        } catch (Exception e) {
        }

        return g;
    }

    //buscar grado por id
    public Grado buscarGrado(int idGr) {
        Grado gr = new Grado();

        try {

            Connection conn = DriverManager.getConnection(CONN, USER, PASS);

            String sql = "select descripcion from grados where idGrado = ?";
            PreparedStatement st = conn.prepareStatement(sql);

            st.setString(1, String.valueOf(idGr));
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                String grado = rs.getString("descripcion");
                gr = new Grado(idGr, grado);
            }

            rs.close();
            st.close();
            conn.close();

        } catch (Exception e) {
        }

        return gr;
    }

    //buscar grupo sanguineo por idGrupoSanguineo
    public GrupoSanguineo buscarGrupoSanguineo(int idGrupoSang) {
        GrupoSanguineo gru = new GrupoSanguineo();

        try {

            Connection conn = DriverManager.getConnection(CONN, USER, PASS);

            String sql = "select descripcion from gruposSanguineos where idGrupoSang = ?";
            PreparedStatement st = conn.prepareStatement(sql);

            st.setString(1, String.valueOf(idGrupoSang));
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                String grupo = rs.getString("descripcion");
                gru = new GrupoSanguineo(idGrupoSang, grupo);
            }

            rs.close();
            st.close();
            conn.close();

        } catch (Exception e) {
        }

        return gru;
    }

    //buscar grupo sanguineo por idGrupoSanguineo
    public FactorSanguineo buscarFactorSanguineo(int idFactorSang) {
        FactorSanguineo f = new FactorSanguineo();

        try {

            Connection conn = DriverManager.getConnection(CONN, USER, PASS);

            String sql = "select factor from factoresSanguineos where idFactor = ?";
            PreparedStatement st = conn.prepareStatement(sql);

            st.setString(1, String.valueOf(idFactorSang));
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                String factor = rs.getString("factor");
                f = new FactorSanguineo(idFactorSang, factor);
            }

            rs.close();
            st.close();
            conn.close();

        } catch (Exception e) {
        }

        return f;
    }

    public Categoria buscarObjCategoria(int id) {
        Categoria cat = new Categoria();

        try {

            Connection conn = DriverManager.getConnection(CONN, USER, PASS);

            String sql = "select idCategoria, descripcion descrCategoria, edadMinima, edadMaxima\n"
                    + "                        from categorias\n"
                    + "                        where idCategoria = ?";
            PreparedStatement st2 = conn.prepareStatement(sql);

            st2.setString(1, String.valueOf(id));
            ResultSet rs2 = st2.executeQuery();

            if (rs2.next()) {
                int idCateg = rs2.getInt("idCategoria");
                String nombreCat = rs2.getString("descrCategoria");
                int edadMin = rs2.getInt("edadMinima");
                int edadMax = rs2.getInt("edadMaxima");

                cat = new Categoria(idCateg, nombreCat, edadMin, edadMax);
            }

        } catch (Exception e) {
        }

        return cat;
    }

    public ListadoAlumnosDTO buscarAlumno(int idAlumno) {

        ListadoAlumnosDTO alumnoDto = new ListadoAlumnosDTO();
        Alumno a = new Alumno();

        Categoria c = new Categoria();

        try {

            Connection conn = DriverManager.getConnection(CONN, USER, PASS);
            String sqlAlumno = "select gr.descripcion, a.apellido, a.nombres, a.fechaNac, a.matriculaIndividual, a.codigoEstadistico, g.idGenero, g.descripcion genero\n"
                    + "	from alumnos a\n"
                    + "	inner join grados gr on gr.idGrado = a.idGrado\n"
                    + "	inner join generos g on g.idGenero = a.idGenero\n"
                    + " where a.idAlumno = ? ";

            PreparedStatement stAlumno = conn.prepareStatement(sqlAlumno);

            stAlumno.setString(1, String.valueOf(idAlumno));
            ResultSet rsAlumno = stAlumno.executeQuery();

            if (rsAlumno.next()) {

//                int idAlumno = rsAlumno.getInt("idAlumno");
                String grado = rsAlumno.getString("descripcion");
                String apellido = rsAlumno.getString("apellido");
                String nombre = rsAlumno.getString("nombres");

                java.util.Date fechaNac = new java.util.Date();
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

                fechaNac = rsAlumno.getDate("fechaNac");
                String fechaNacim = dateFormat.format(fechaNac);

                int dni = rsAlumno.getInt("matriculaIndividual");
                int codigoEstadistico = rsAlumno.getInt("codigoEstadistico");
                int idGenero = rsAlumno.getInt("idGenero");
                String genero = rsAlumno.getNString("genero");

                int idcateg = a.calcularCategConSql(fechaNacim);

                //Como categoria no se relaciona con el Alumno en la DB, no puedo traer la categoria de cada uno.
                //Con este mamarracho, logro traer la categoria usando la fecha de nacimiento y un metodo de clase Alumno 
                //que me dice que id de categoria es.
                String sql = "select idCategoria, descripcion descrCategoria, edadMinima, edadMaxima\n"
                        + "                        from categorias\n"
                        + "                        where idCategoria = ?";
                PreparedStatement st2 = conn.prepareStatement(sql);

                st2.setString(1, String.valueOf(idcateg));
                ResultSet rs2 = st2.executeQuery();

                int cat, edadMin, edadMax;

                if (rs2.next()) {
                    cat = rs2.getInt("idCategoria");
                    String nombreCat = rs2.getString("descrCategoria");
                    edadMin = rs2.getInt("edadMinima");
                    edadMax = rs2.getInt("edadMaxima");

                    c = new Categoria(cat, nombreCat, edadMin, edadMax);
                }

                alumnoDto = new ListadoAlumnosDTO(idAlumno, grado, apellido, nombre, fechaNacim, dni, codigoEstadistico, idGenero, genero, c);

            }

            rsAlumno.close();
            stAlumno.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return alumnoDto;
    }

    //----------------LISTAR ALUMNOS------------------------
    public ArrayList<ListadoAlumnosDTO> listarAlumnosTodos() {

        ArrayList<ListadoAlumnosDTO> lista = new ArrayList<>();
        Alumno a = new Alumno();
        Categoria c = new Categoria();

        try {

            Connection conn = DriverManager.getConnection(CONN, USER, PASS);

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select a.idAlumno, gr.descripcion, a.apellido, a.nombres, a.fechaNac, a.matriculaIndividual, a.codigoEstadistico, g.idGenero, g.descripcion genero\n"
                    + "	from alumnos a\n"
                    + "	inner join grados gr on gr.idGrado = a.idGrado\n"
                    + "	inner join generos g on g.idGenero = a.idGenero");

            while (rs.next()) {

                int idAlumno = rs.getInt("idAlumno");
                String grado = rs.getString("descripcion");
                String apellido = rs.getString("apellido");
                String nombre = rs.getString("nombres");

                //tomar una Date y devolver un String
                java.util.Date fechaNac = new java.util.Date();
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

                fechaNac = rs.getDate("fechaNac");
                String fechaNacim = dateFormat.format(fechaNac);

                int dni = rs.getInt("matriculaIndividual");
                int codigoEstadistico = rs.getInt("codigoEstadistico");
                int idGenero = rs.getInt("idGenero");
                String genero = rs.getNString("genero");

                int idcateg = a.calcularCategConSql(fechaNacim);

                //Como categoria no se relaciona con el Alumno en la DB, no puedo traer la categoria de cada uno.
                //Con este mamarracho, logro traer la categoria usando la fecha de nacimiento y un metodo de clase Alumno 
                //que me dice que id de categoria es.
                String sql = "select idCategoria, descripcion descrCategoria, edadMinima, edadMaxima\n"
                        + "                        from categorias\n"
                        + "                        where idCategoria = ?";
                PreparedStatement st2 = conn.prepareStatement(sql);

                st2.setString(1, String.valueOf(idcateg));
                ResultSet rs2 = st2.executeQuery();

                int cat, edadMin, edadMax;

                if (rs2.next()) {
                    cat = rs2.getInt("idCategoria");
                    String nombreCat = rs2.getString("descrCategoria");
                    edadMin = rs2.getInt("edadMinima");
                    edadMax = rs2.getInt("edadMaxima");

                    c = new Categoria(cat, nombreCat, edadMin, edadMax);
                }

                ListadoAlumnosDTO dto = new ListadoAlumnosDTO(idAlumno, grado, apellido, nombre, fechaNacim, dni, codigoEstadistico, idGenero, genero, c);
                lista.add(dto);

            }

            rs.close();
            st.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    //obtener el puntaje correspondiente a un resultado de prueba segun el genero, categoria.
    public int obtenerResultado(int idCateg, int idPrueba, int idGenero, double resultado) {
        int r = 0;

        try {

            Connection conn = DriverManager.getConnection(CONN, USER, PASS);

            String sql = "select puntosObtenidos\n"
                    + "    from matrizPuntajes\n"
                    + "    where idGenero = ?\n"
                    + "    and idPrueba = ?\n"
                    + "    and idCategoria = ?\n"
                    + "    and ? between exigenciaMin and exigenciaMax";
            PreparedStatement st = conn.prepareStatement(sql);

            st.setInt(1, idGenero);
            st.setInt(2, idPrueba);
            st.setInt(3, idCateg);
            st.setDouble(4, resultado);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                r = rs.getInt("puntosObtenidos");

            }

            rs.close();
            st.close();
            conn.close();

        } catch (Exception e) {
        }

        return r;
    }

    //listar resultado de las pruebas y sus resultados segun idExamen
    public ArrayList<PruebasXIdExamenDTO> listarPruebasRendidasXIdExamen(int idExamen) {

        ArrayList<PruebasXIdExamenDTO> lista = new ArrayList<>();

        try {

            Connection conn = DriverManager.getConnection(CONN, USER, PASS);

            String sql = "select de.idExamen, de.idPrueba, tp.descripcion as nombrePrueba, de.resultado, de.puntajeObtenido\n"
                    + "	from detalleExamenes de\n"
                    + "	inner join tiposPruebas tp on de.idPrueba = tp.idPrueba\n"
                    + "	where idExamen = ?";
            PreparedStatement st = conn.prepareStatement(sql);

            st.setString(1, String.valueOf(idExamen));
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int idExam = rs.getInt("idExamen");
                int idPrueba = rs.getInt("idPrueba");
                String nombrePrueba = rs.getString("nombrePrueba");
                double resultado = rs.getDouble("resultado");
                int puntaje = rs.getInt("puntajeObtenido");

                PruebasXIdExamenDTO p = new PruebasXIdExamenDTO(idExamen, idPrueba, nombrePrueba, resultado, puntaje);
                lista.add(p);
            }

            rs.close();
            st.close();
            conn.close();

        } catch (Exception e) {
        }

        return lista;

    }

    //------------------UPDATES----------------------
    //UPDATE DE PESO
    public void editarPesaje(Pesaje p) {
        try {
            Connection conn = DriverManager.getConnection(CONN, USER, PASS);

            String sql = "update pesajes\n"
                    + "set fechaPesaje = ?,\n"
                    + "	pesoEnKilos= ?,\n"
                    + "	idEstadoPeso = ?,\n"
                    + "	bajoSeguimiento = ?,\n"
                    + "	observaciones = ?, IMC = ?\n"
                    + "	where idPesaje= ?";

            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, p.getFechaPesaje());
            st.setDouble(2, p.getPesoEnKilos());
            st.setInt(3, p.getEstadoPeso().getIdTipoEstadoPeso());
            int bitSeguimineto = 0;
            if (p.isBajoSeguimiento()) {
                bitSeguimineto = 1;
            }
            st.setInt(4, bitSeguimineto);
            st.setString(5, p.getObservaciones());
            st.setInt(7, p.getIdPesaje());
            st.setDouble(6, p.getIndiceMasaCorporal());

            st.executeUpdate();

            st.close();
            conn.close();

        } catch (Exception ex) {
        }
    }

    //Update para cancelar la baja de un usuario.
    public void updateCancelarBaja(int idUsuario) {

        try {
            Connection conn = DriverManager.getConnection(CONN, USER, PASS);

            String sql = "UPDATE usuarios\n"
                    + "   SET fechaBaja = null\n"
                    + "   where idUsuario = ?";

            PreparedStatement st = conn.prepareStatement(sql);

            st.setInt(1, idUsuario);

            st.executeUpdate();

            st.close();
            conn.close();

        } catch (Exception ex) {
        }

    }

    //Update para cancelar la baja de un Profesor.
    public void updateCancelarBajaProfesor(int idProfesor) {

        try {
            Connection conn = DriverManager.getConnection(CONN, USER, PASS);

            String sql = "UPDATE profesores\n"
                    + "   SET fechaBaja = null\n"
                    + "   where idProfesor = ?";

            PreparedStatement st = conn.prepareStatement(sql);

            st.setInt(1, idProfesor);

            st.executeUpdate();

            st.close();
            conn.close();

        } catch (Exception ex) {
        }

    }

    //update de fechaBaja usuarios
    public void updateBajaUsuario(String fechaBaja, int idUsuario) {

        try {
            Connection conn = DriverManager.getConnection(CONN, USER, PASS);

            String sql = "UPDATE usuarios\n"
                    + "   SET fechaBaja = ?\n"
                    + "   where idUsuario = ?";

            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, fechaBaja);
            st.setInt(2, idUsuario);

            st.executeUpdate();

            st.close();
            conn.close();

        } catch (Exception ex) {
        }

    }

    //update de fechaBaja Profesor
    public void updateBajaProfesor(String fechaBaja, int idProfe) {

        try {
            Connection conn = DriverManager.getConnection(CONN, USER, PASS);

            String sql = "UPDATE profesores\n"
                    + "   SET fechaBaja = ?\n"
                    + "   where idProfesor = ?";

            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, fechaBaja);
            st.setInt(2, idProfe);

            st.executeUpdate();

            st.close();
            conn.close();

        } catch (Exception ex) {
        }

    }

    //update detalle examen
    public void editarDetalleExamen(DetalleExamen de) {
        try {
            Connection conn = DriverManager.getConnection(CONN, USER, PASS);

            String sql = "update detalleExamenes\n"
                    + "set fechaPrueba = ?,\n"
                    + "	resultado = ?,\n"
                    + "	puntajeObtenido = ?,\n"
                    + "	idProfesor = ?,\n"
                    + "	observaciones = ?\n"
                    + "	where idExamen = ? and idPrueba = ?";

            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, de.getFechaPrueba());
            st.setDouble(2, de.getResultado());
            st.setInt(3, de.getPuntaje());
            st.setInt(4, de.getProdesor());
            st.setString(5, de.getObservaciones());
            st.setInt(6, de.getIdExamen());
            st.setInt(7, de.getPrueba());

            st.executeUpdate();

            st.close();
            conn.close();

        } catch (Exception ex) {
        }
    }

    //update nota de examen
    public void updateNotaExam(double nota, int idExam) {

        try {
            Connection conn = DriverManager.getConnection(CONN, USER, PASS);

            String sql = "UPDATE examenes\n"
                    + "   SET notaFinal = ?\n"
                    + "   where idExamen = ?";

            PreparedStatement st = conn.prepareStatement(sql);
            st.setDouble(1, nota);
            st.setInt(2, idExam);

            st.executeUpdate();

            st.close();
            conn.close();

        } catch (Exception ex) {
        }

    }

    public void editarAlumno(Alumno a) {

        try {
            Connection conn = DriverManager.getConnection(CONN, USER, PASS);

            String sql = "update alumnos\n"
                    + "set nombres = ?,\n"
                    + "apellido = ?,\n"
                    + "fechaNac = ?,\n"
                    + "codigoEstadistico = ?,\n"
                    + "matriculaIndividual = ?,\n"
                    + "nroIOSFA = ?,\n"
                    + "grupoSanguineo = ?,\n"
                    + "factorSanguineo = ?,\n"
                    + "idGrado = ?,\n"
                    + "obsSanitaria = ?,\n"
                    + "obsGenerales = ?,\n"
                    + "tallaEnMetros = ?,\n"
                    + "idGenero = ?\n"
                    + "where idAlumno = ?";

            PreparedStatement st = conn.prepareStatement(sql);

            st.setString(1, a.getNombres());
            st.setString(2, a.getApellidos());
            st.setString(3, a.getFechaNacimiento());
            st.setInt(4, a.getCodigoEstadistico());
            st.setInt(5, a.getMatriculaIndividual());
            st.setInt(6, a.getNroIOSFA());
            st.setInt(7, a.getGrupoSanguineo());
            st.setInt(8, a.getFactorSanguineo());
            st.setInt(9, a.getGrado());
            st.setString(10, a.getObsSanitarias());
            st.setString(11, a.getObsGenerales());
            st.setDouble(12, a.getTallaEnMetros());
            st.setInt(13, a.getGenero());
            st.setInt(14, a.getIdAlumno());

            st.executeUpdate();
            st.close();
            conn.close();

        } catch (Exception e) {

        }
    }

    //update de datos de un examen
    public void editarDatosExamen(int idExamen, int tipoxamen, int idProfesor, String fechaExamen, String obs) {

        try {
            Connection conn = DriverManager.getConnection(CONN, USER, PASS);

            String sql = "update examenes\n"
                    + "set idTipoExamen = ?,\n"
                    + "	encargadoExamen = ?,\n"
                    + "	fechaExamen = ?,\n"
                    + "	observaciones = ?\n"
                    + "where idExamen = ?";

            PreparedStatement st = conn.prepareStatement(sql);

            st.setInt(1, tipoxamen);
            st.setInt(2, idProfesor);
            st.setString(3, fechaExamen);
            st.setString(4, obs);
            st.setInt(5, idExamen);

            st.executeUpdate();
            st.close();
            conn.close();

        } catch (Exception e) {

        }
    }

    public Usuario buscarUsuario(String usuario) {
        Usuario user = null;

        try {
            Connection conn = DriverManager.getConnection(CONN, USER, PASS);

            String sql = "select idUsuario, u.idTipoUsuario, tu.tipoUsuario, tu.observaciones, nombreUsuario, u.password, preguntaSecreta, respuestaSecreta, activo, fechaBaja, fechaAlta\n"
                    + "	from usuarios u\n"
                    + "	inner join tipoUsuario tu on u.idTipoUsuario = tu.idTipoUsuario\n"
                    + "	where nombreUsuario = ?";

            PreparedStatement st = conn.prepareStatement(sql);

            st.setString(1, String.valueOf(usuario));
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int idUsuario = rs.getInt("idUsuario");
                int idTipoUsuario = rs.getInt("idTipoUsuario");
                String tipoUsuario = rs.getString("tipoUsuario");
                String observaciones = rs.getString("observaciones");
                String nombreUsuario = rs.getString("nombreUsuario");
                String password = rs.getString("password");
                String preguntaSecreta = rs.getString("preguntaSecreta");
                String respuestaSecreta = rs.getString("respuestaSecreta");
                boolean activo = rs.getBoolean("activo");
                String fechaBaja = rs.getString("fechaBaja");
                String fechaAlta = rs.getString("fechaAlta");

                TipoUsuario tu = new TipoUsuario(idTipoUsuario, tipoUsuario, observaciones);

                user = new Usuario(idUsuario, tu, nombreUsuario, password, preguntaSecreta, respuestaSecreta, activo, fechaBaja, fechaAlta);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public Alumno buscarAlumnoModel(int idAlum) {
        Alumno alumno = null;

        try {
            Connection conn = DriverManager.getConnection(CONN, USER, PASS);

            String sql = "select idAlumno, idGrado, nombres, apellido, fechaNac, \n"
                    + "		codigoEstadistico, matriculaIndividual, nroIOSFA, \n"
                    + "		grupoSanguineo, factorSanguineo, obsSanitaria, \n"
                    + "		obsGenerales, tallaEnMetros, idGenero, fechaAlta, fechaBaja\n"
                    + "from alumnos\n"
                    + "where idAlumno = ?";

            PreparedStatement st = conn.prepareStatement(sql);

            st.setString(1, String.valueOf(idAlum));
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int idAlumno = rs.getInt("idAlumno");
                int idGrado = rs.getInt("idGrado");
                String nombres = rs.getString("nombres");
                String apellido = rs.getString("apellido");
                String fechaNac = rs.getString("fechaNac");
                int codigoEstadistico = rs.getInt("codigoEstadistico");
                int matriculaIndividual = rs.getInt("matriculaIndividual");
                int nroIOSFA = rs.getInt("nroIOSFA");
                int grupoSanguineo = rs.getInt("grupoSanguineo");
                int factorSanguineo = rs.getInt("factorSanguineo");
                String obsSanitaria = rs.getString("obsSanitaria");
                String obsGenerales = rs.getString("obsGenerales");
                double tallaEnMetros = rs.getDouble("tallaEnMetros");
                int idGenero = rs.getInt("idGenero");
                String fechaAlta = rs.getString("fechaAlta");
                String fechaBaja = rs.getString("fechaBaja");

                alumno = new Alumno(idAlumno, idGrado, idGenero, nombres,
                        apellido, fechaNac, codigoEstadistico, matriculaIndividual,
                        nroIOSFA, grupoSanguineo, factorSanguineo, obsSanitaria,
                        obsGenerales, tallaEnMetros, fechaAlta, fechaBaja);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return alumno;
    }

    //CONSULTAS PARA GRAFICOS
    //Metodo para tarer el promedio de las notas por cada prueba
    public ArrayList<PruebasPromedioDTO> contarPruebaPromedio() {

        ArrayList<PruebasPromedioDTO> lista = new ArrayList<>();

        try {
            Connection conn = DriverManager.getConnection(CONN, USER, PASS);
            String sql = "select tp.descripcion Prueba, AVG(de.puntajeObtenido) Promedio\n"
                    + "     from detalleExamenes de\n"
                    + "         inner join tiposPruebas tp on tp.idPrueba = de.idPrueba\n"
                    + "         inner join examenes e on de.idExamen = e.idExamen\n"
                    + "     where puntajeObtenido != 0\n"
                    + "     group by tp.descripcion";
            PreparedStatement st = conn.prepareStatement(sql);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                String prueba = rs.getString("prueba");
                double promedio = rs.getDouble("promedio");
                PruebasPromedioDTO pp = new PruebasPromedioDTO(prueba, promedio);
                lista.add(pp);
            }
            rs.close();
            st.close();
            conn.close();

        } catch (Exception e) {
        }
        return lista;
    }

    //El parametro se lo pasa el usuario con el cbm de jsp de visualizarGraficos
    //para que sea "parametrizable".
    public ArrayList<PruebasPromedioDTO> contarPruebaPromedio(int categ) {

        ArrayList<PruebasPromedioDTO> lista = new ArrayList<>();

        try {
            Connection conn = DriverManager.getConnection(CONN, USER, PASS);
            String sql = "select tp.descripcion Prueba, AVG(de.puntajeObtenido) Promedio\n"
                    + "     from detalleExamenes de\n"
                    + "         inner join tiposPruebas tp on tp.idPrueba = de.idPrueba\n"
                    + "         inner join examenes e on de.idExamen = e.idExamen\n"
                    + "     where puntajeObtenido != 0\n"
                    + "         and e.idCategoria = ?\n"
                    + "     group by tp.descripcion";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, categ);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                String prueba = rs.getString("prueba");
                double promedio = rs.getDouble("promedio");
                PruebasPromedioDTO pp = new PruebasPromedioDTO(prueba, promedio);
                lista.add(pp);
            }
            rs.close();
            st.close();
            conn.close();

        } catch (Exception e) {
        }
        return lista;
    }

    //Metodo para los datos del grafico filtrado
    public ArrayList<PruebasPromedioDTO> contarPruebaPromedioFiltrado(String queryWhere) {

        ArrayList<PruebasPromedioDTO> lista = new ArrayList<>();

        try {
            Connection conn = DriverManager.getConnection(CONN, USER, PASS);
            String sql = "select tp.descripcion Prueba, AVG(de.puntajeObtenido) Promedio\n"
                    + "                         from detalleExamenes de\n"
                    + "                             inner join tiposPruebas tp on tp.idPrueba = de.idPrueba\n"
                    + "                             inner join examenes e on de.idExamen = e.idExamen\n"
                    + "                         where puntajeObtenido != 0\n"
                    + queryWhere
                    + "                         group by tp.descripcion";
            PreparedStatement st = conn.prepareStatement(sql);
//            st.setInt(1, categ);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                String prueba = rs.getString("prueba").trim();
                double promedio = rs.getDouble("promedio");
                PruebasPromedioDTO pp = new PruebasPromedioDTO(prueba, promedio);
                lista.add(pp);
            }
            rs.close();
            st.close();
            conn.close();

        } catch (Exception e) {
        }
        return lista;
    }

    //cuanto los alumnos segun su situacion de estado de peso para hacer un grafico de torta
    public ArrayList<Integer> contarEstadosPeso() {

        ArrayList<Integer> lista = new ArrayList<>();
        int bajo = 0;
        int normo = 0;
        int sobre = 0;
        int obe1 = 0;
        int obe2 = 0;
        int obe3 = 0;
        try {
            Connection conn = DriverManager.getConnection(CONN, USER, PASS);

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select idEstadoPeso from pesajes");

            while (rs.next()) {
                int idGenero = rs.getInt("idEstadoPeso");

                if (idGenero == 1) {
                    bajo++;
                }
                if (idGenero == 2) {
                    normo++;
                }
                if (idGenero == 3) {
                    sobre++;
                }
                if (idGenero == 4) {
                    obe1++;
                }
                if (idGenero == 5) {
                    obe2++;
                }
                if (idGenero == 6) {
                    obe3++;
                }
            }

            lista.add(bajo);
            lista.add(normo);
            lista.add(sobre);
            lista.add(obe1);
            lista.add(obe2);
            lista.add(obe3);

            rs.close();
            st.close();
            conn.close();

        } catch (Exception e) {
        }
        return lista;
    }

    public ArrayList<Integer> contarGeneros() {

        ArrayList<Integer> lista = new ArrayList<>();
        int fem = 0;
        int masc = 0;
        try {
            Connection conn = DriverManager.getConnection(CONN, USER, PASS);

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select idGenero from alumnos");

            while (rs.next()) {
                int idGenero = rs.getInt("idGenero");

                if (idGenero == 1) {
                    fem++;
                } else {
                    masc++;
                }
            }

            lista.add(fem);
            lista.add(masc);

            rs.close();
            st.close();
            conn.close();

        } catch (Exception e) {
        }
        return lista;
    }

    //---------------ELIMINAR--------------------
    //ELIMINAR PESAJE
    public void eliminarPesaje(int idPesaje) {

        try {
            Connection conn = DriverManager.getConnection(CONN, USER, PASS);

            String sql = "delete from pesajes where idPesaje = ?";

            PreparedStatement st = conn.prepareStatement(sql);

            st.setInt(1, idPesaje);

            st.executeUpdate();

            st.close();
            conn.close();

        } catch (Exception ex) {
        }

    }

    //Eliminar usuarios
    public void eliminarUsuario(int idUsuario) {

        try {
            Connection conn = DriverManager.getConnection(CONN, USER, PASS);

            String sql = "delete from usuarios where idUsuario = ?";

            PreparedStatement st = conn.prepareStatement(sql);

            st.setInt(1, idUsuario);

            st.executeUpdate();

            st.close();
            conn.close();

        } catch (Exception ex) {
        }

    }

    public void eliminarProfe(int idProfe) {

        try {
            Connection conn = DriverManager.getConnection(CONN, USER, PASS);

            String sql = "delete from profesores where idProfesor = ?";

            PreparedStatement st = conn.prepareStatement(sql);

            st.setInt(1, idProfe);

            st.executeUpdate();

            st.close();
            conn.close();

        } catch (Exception ex) {
        }

    }

}
