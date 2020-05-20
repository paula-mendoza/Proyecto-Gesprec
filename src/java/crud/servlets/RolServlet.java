/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.SQLException;
import java.util.List;

import crud.dao.RolDAO;
import crud.model.Rol;
import java.io.Console;
import javax.servlet.RequestDispatcher;

/**
 *
 * @author Dell_1
 */
@WebServlet(name = "RolServlet", urlPatterns = {"/RolServlet"})
public class RolServlet extends HttpServlet {

    RolDAO rolDAO;

    public void init() {

        try {
            rolDAO = new RolDAO();
        } catch (Exception e) {
        }
    }

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
        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet RolServlet</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet RolServlet at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
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
        processRequest(request, response);
        System.out.println("I'm in doGet !!");
        String action = request.getParameter("action");
        System.out.println(action);

        try {
            switch (action) {
                case "register":
                    System.out.println("entro");
                    register(request, response);
                    break;

                case "show":
                    show(request, response);
                    break;

                case "delete":
                    System.out.println("Go to delete");
                    delete(request, response);
                    break;
                    
                case "update":
                    System.out.println("Go to update");
                    update(request,response);
                    break;

                default:
                    break;

            }
        } catch (SQLException e) {
            e.getStackTrace();
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
        processRequest(request, response);

    }

    private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {

        Rol rol = new Rol(Integer.parseInt(request.getParameter("id")), request.getParameter("nombre"));
        boolean valueinse = rolDAO.insert(rol);
        System.out.println(valueinse);
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);

    }

    private void show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("show.jsp");
        List<Rol> listRol = rolDAO.listRol();
        System.out.println(listRol);
        request.setAttribute("list", listRol);
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        Rol rol = rolDAO.getRolId(Integer.parseInt(request.getParameter("id")));
        rolDAO.delete(rol);
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }
    
    private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        {
          Rol rol = new Rol (Integer.parseInt(request.getParameter("id")), request.getParameter("nombre"));
          rolDAO.update(rol);
          
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

