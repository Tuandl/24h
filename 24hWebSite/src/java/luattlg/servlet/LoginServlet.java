/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package luattlg.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tuanvxm.DTOs.UserDTO;
import tuanvxm.other.CategoryList;

/**
 *
 * @author luattlgse62386
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

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
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("pwfPassword");
        
        UserDTO user = new UserDTO();
        user.setUsername(username);
        user.setPassword(password);
        
        if(user.login()){
            String role = CategoryList.getName(user.getRoleID());
            request.getSession().setAttribute("ROLE", role);
            request.getSession().setAttribute("USERINFO", user);
            
            if(role.equalsIgnoreCase("admin")){
                response.sendRedirect("admin.jsp");
            }
            if(role.equalsIgnoreCase("editor")){
                response.sendRedirect("editor.jsp");
            }
            if(role.equalsIgnoreCase("journalist")){
                response.sendRedirect("journalist.jsp");
            }
            if(role.equalsIgnoreCase("reader")){
                response.sendRedirect("reader.jsp");
            }
            
            return;
        }
        
        request.setAttribute("ERROR", "Username or password is incorrect.");
        request.getRequestDispatcher("home.jsp").forward(request, response);
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
