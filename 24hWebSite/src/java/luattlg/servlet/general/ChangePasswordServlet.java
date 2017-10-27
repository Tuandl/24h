/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package luattlg.servlet.general;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tuanvxm.DAOs.UserDAO;
import tuanvxm.DTOs.UserDTO;

/**
 * This servlet is for changing the password. Foward :
 * SUCCESS - success.jsp
 * FAIL - fail.jsp
 */
@WebServlet(name = "ChangePasswordServlet", urlPatterns = {"/ChangePassword.action"})
public class ChangePasswordServlet extends HttpServlet {

    private static final String SUCCESS = "tuanda/profile.jsp";
    private static final String FAIL = "tuanda/profile.jsp";
    
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
        String oldPassword = request.getParameter("pwfOldPassword");
        String newPassword = request.getParameter("pwfNewPassword");
        UserDTO user = (UserDTO)request.getSession().getAttribute("USER");
        
        if(!new UserDAO().changePassword(user.getUserID(), oldPassword, newPassword)){
            request.setAttribute("ERROR", "The old password is incorrect. Please enter new one");
            request.getRequestDispatcher(FAIL).forward(request, response);
        } else {
            request.setAttribute("SUCCESS", "Change password successful!!");
        }
        
        request.getRequestDispatcher(SUCCESS).forward(request, response);
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
