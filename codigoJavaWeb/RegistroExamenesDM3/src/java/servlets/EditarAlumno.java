/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import controller.AccesoBaseDatos;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Integer.parseInt;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static javax.xml.bind.DatatypeConverter.parseDouble;
import model.Alumno;
import model.Categoria;
import model.Usuario;

/**
 *
 * @author Emiliano Barat
 */
@WebServlet(name = "EditarAlumno", urlPatterns = {"/EditarAlumno"})
public class EditarAlumno extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Usuario user = (Usuario) session.getAttribute("usuario");
        if (user != null && !user.getNombreUsuario().equals("")) {
            //SI EL REQUEST VIENE SIN PARAMETROS LO REDIRIJO AL LISTADO DE ALUMNOS
            try {
                //Tomo el idAlumno que paso por la URL
                int idAlumno = Integer.parseInt(request.getParameter("idAlumno"));
                AccesoBaseDatos gestor = new AccesoBaseDatos();
                //Busco al alumno con ese ID
                Alumno a = gestor.buscarAlumnoModel(idAlumno);
                //Uso el metodo de la clase Alumno para calcular la categoria segun la fecha de nacimiento
                int idCategoria = a.calcularCategConSql(a.getFechaNacimiento());
                //Listo todas las categorias
                ArrayList<Categoria> listaCategorias = gestor.listarCategorias();
                Categoria c = new Categoria();
                //Verifico a que categoria es segun el IdCategoria
                for (Categoria listaCategoria : listaCategorias) {
                    if (listaCategoria.getIdCategoria() == idCategoria) {
                        c = listaCategoria;
                    }
                }
                request.setAttribute("alumno", a);
                request.setAttribute("categoria", c);
                RequestDispatcher rd = request.getRequestDispatcher("editarAlumnos.jsp");
                rd.forward(request, response);
            } catch (Exception e) {
                RequestDispatcher rd = request.getRequestDispatcher("ListarAlumnos");
                rd.forward(request, response);
            }

//            if (!request.getAttributeNames().hasMoreElements()) {
//                
//            } else {
//                //Tomo el idAlumno que paso por la URL
//                int idAlumno = Integer.parseInt(request.getParameter("idAlumno"));
//                AccesoBaseDatos gestor = new AccesoBaseDatos();
//                //Busco al alumno con ese ID
//                Alumno a = gestor.buscarAlumnoModel(idAlumno);
//                //Uso el metodo de la clase Alumno para calcular la categoria segun la fecha de nacimiento
//                int idCategoria = a.calcularCategConSql(a.getFechaNacimiento());
//                //Listo todas las categorias
//                ArrayList<Categoria> listaCategorias = gestor.listarCategorias();
//                Categoria c = new Categoria();
//                //Verifico a que categoria es segun el IdCategoria
//                for (Categoria listaCategoria : listaCategorias) {
//                    if (listaCategoria.getIdCategoria() == idCategoria) {
//                        c = listaCategoria;
//                    }
//                }
//                request.setAttribute("alumno", a);
//                request.setAttribute("categoria", c);
//                RequestDispatcher rd = request.getRequestDispatcher("editarAlumnos.jsp");
//                rd.forward(request, response);
//            }
        } else {
            String msjNoUser = "No hay usuario logueado.\nIngrese sus credenciales.";
            request.setAttribute("msj", msjNoUser);
            RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
            rd.forward(request, response);
        }

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Date nacimimento = new Date();
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        Alumno alumno = new Alumno();

        try {
            int idAlumno = Integer.parseInt(request.getParameter("txtIdAlumno"));

            String nombre = request.getParameter("txtNombre");
            String apellido = request.getParameter("txtApellido");
            int grado = Integer.parseInt(request.getParameter("cmbGrados"));
            int genero = Integer.parseInt(request.getParameter("cmbGeneros"));
            int grupoSang = Integer.parseInt(request.getParameter("cmbGrSang"));
            int facSang = Integer.parseInt(request.getParameter("cmbFacSang"));
            int codEstadistico = Integer.parseInt(request.getParameter("numCodEstadistico"));
            int dni = Integer.parseInt(request.getParameter("numDni"));
            int iosfa = Integer.parseInt(request.getParameter("numIosfa"));

            String fechaNatalicio = request.getParameter("dateFechaNac");

            String infoSan = request.getParameter("infoSanitaria");
            String infoGreneral = request.getParameter("infoGeneral");
            double altura = Double.parseDouble(request.getParameter("txtAltura"));

            Date fechaActual = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String fechaAlta = dateFormat.format(fechaActual);

            int idCategoria = alumno.calcularCategConSql(fechaNatalicio);

            Alumno a = new Alumno(idAlumno, grado, genero, nombre, apellido, fechaNatalicio, codEstadistico, dni, iosfa, grupoSang, facSang, infoSan, infoGreneral, altura, fechaAlta, null);

            AccesoBaseDatos gestor = new AccesoBaseDatos();

            gestor.editarAlumno(a);

            request.setAttribute("a", a);

            String msj = "Editó ";
            request.setAttribute("msj", msj);

            String path = request.getContextPath();
            response.sendRedirect(path + "/exitoCarga.jsp");
//            RequestDispatcher rd = request.getRequestDispatcher("exitoCargaAlumno.jsp");
//            rd.forward(request, response);

        } catch (Exception e) {
            RequestDispatcher rd = request.getRequestDispatcher("errorCarga.jsp");
            rd.forward(request, response);
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
