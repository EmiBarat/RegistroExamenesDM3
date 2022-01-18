/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import controller.AccesoBaseDatos;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Categoria;
import model.Usuario;

/**
 *
 * @author Emiliano Barat
 */
@WebServlet(name = "ListarAlumnosPeso", urlPatterns = {"/ListarAlumnosPeso"})
public class ListarAlumnosPeso extends HttpServlet {

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
            AccesoBaseDatos gestor = new AccesoBaseDatos();
            ArrayList lista = gestor.listarAlumnosTodos();
            request.setAttribute("listaAlumnos", lista);
            RequestDispatcher rd = request.getRequestDispatcher("listadoAlumnosPeso.jsp");
            rd.forward(request, response);

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
        
        HttpSession session = request.getSession();
        Usuario user = (Usuario) session.getAttribute("usuario");
        if (user != null && !user.getNombreUsuario().equals("")) {
            AccesoBaseDatos gestor = new AccesoBaseDatos();
            Categoria c = new Categoria();

            //Agregar los campos nuevos de filtro
            String txtNombreAlumno = request.getParameter("txtNombreAlumno");
            String txtApellidoAlumno = request.getParameter("txtApellidoAlumno");
            int idCategoriaSelected = Integer.parseInt(request.getParameter("cboCategorias"));

//        int idAlumno = Integer.parseInt(request.getParameter("hiddenIdAlumno"));
            int codEstadistico = 0;
            int dni = 0;
            if (!request.getParameter("txtCodEstadistico").equals("")) {
                codEstadistico = Integer.parseInt(request.getParameter("txtCodEstadistico"));
            }
            if (!request.getParameter("txtDni").equals("")) {
                dni = Integer.parseInt(request.getParameter("txtDni"));
            }
            
            
            
            int idGenero = Integer.parseInt(request.getParameter("cmbTipoGenero"));
            int grado = Integer.parseInt(request.getParameter("cmbTipoGrado"));

            String sqlWhereTxt = "where 1 = 1 ";

            //Agragar las condiciones de filtrado nuevas
            if (!txtNombreAlumno.equals("")) {
                sqlWhereTxt += "and a.nombres like '" + txtNombreAlumno + "%' ";
            }

            if (!txtApellidoAlumno.equals("")) {
                sqlWhereTxt += "and a.apellido like '" + txtApellidoAlumno + "%' ";
            }

            if (idCategoriaSelected != 0) {
                ArrayList<model.Categoria> lista = gestor.listarCategorias();
                for (Categoria categoria : lista) {
                    if (categoria.getIdCategoria() == idCategoriaSelected) {
                        c = categoria;
                    }
                }

                sqlWhereTxt += "and DATEDIFF(YEAR,fechaNac,GETDATE()) between " + c.getEdadMinima() + "and " + c.getEdadMaxima() + " ";
            }

            if (codEstadistico != 0) {
                sqlWhereTxt += "and a.codigoEstadistico = " + codEstadistico + " ";
            }
            if (dni != 0) {
                sqlWhereTxt += "and a.matriculaIndividual = " + dni + " ";
            }
            
            if (idGenero != 0) {
                sqlWhereTxt += "and a.idGenero = " + idGenero + " ";
            }
            if (grado != 0) {
                sqlWhereTxt += "and a.idGrado = " + grado + " ";
            }

            ArrayList listaFiltrada = gestor.listarAlumnosFiltrados(sqlWhereTxt);
            request.setAttribute("listaAlumnos", listaFiltrada);

            RequestDispatcher rd = request.getRequestDispatcher("listadoAlumnosPeso.jsp");
            rd.forward(request, response);

        } else {
            String msjNoUser = "No hay usuario logueado.\nIngrese sus credenciales.";
            request.setAttribute("msj", msjNoUser);
            RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
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
