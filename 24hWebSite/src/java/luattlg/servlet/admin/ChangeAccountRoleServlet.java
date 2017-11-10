/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package luattlg.servlet.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tuanvxm.DAOs.UserDAO;
import tuanvxm.other.Role;

/**
 * This servlet is for changing role. Foward to admin page.
 */
@WebServlet(name = "ChangeAccountRoleServlet", urlPatterns = {"/ChangeAccountRole.action"})
public class ChangeAccountRoleServlet extends HttpServlet {

    private static final String FOWARD = "/tuanda/admin-home-page.jsp";
    
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
        
        int userID = Integer.parseInt(request.getParameter("txtUserID"));
        String newRole = request.getParameter("txtRole");
        String pressCard = request.getParameter("txtPressCard");
        List<Role> roleList = (ArrayList<Role>) getServletContext().getAttribute("ROLE-LIST");
        int newRoleID = 0;
        int journalistID = 0;
        for (Role role : roleList) {
            if (role.getName().equalsIgnoreCase(newRole)) {
                newRoleID = role.getRoleID();
            }
            if(role.getName().equalsIgnoreCase("journalist")){
                journalistID = role.getRoleID();
            }
        }
        if(newRoleID == journalistID){
            if(pressCard == null || pressCard.length() == 0){
                request.setAttribute("WARNING", "Để chuyển vai trò thành nhà báo xin hãy nhập mã số nhà báo.");
                request.getRequestDispatcher(FOWARD).forward(request, response);
                return;
            }
        }
        if(!new UserDAO().changeRoleID(userID, newRoleID, pressCard)){
            request.setAttribute("ERROR-CHANGE-ROLE", "Some errors occur. Please try again.");
        }else{
            request.setAttribute("SUCCESS", "Chuyển vai trò thành công");
        }
        request.getRequestDispatcher(FOWARD).forward(request, response);
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
