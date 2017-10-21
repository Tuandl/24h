/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package luattlg.servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tuanvxm.DAOs.CategoryDAO;
import tuanvxm.DAOs.RoleDAO;
import tuanvxm.other.Category;
import tuanvxm.other.Role;

/**
* This servlet is for initializing the category list 
* and the role list, then load them into the application scope.
* After that, redirect to home page
*/
@WebServlet(name = "InitServlet", urlPatterns = {"/Init.action"})
public class InitServlet extends HttpServlet {

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
        
        //This instance is for check if this is the first time the website load or not.
        String isFirst = (String) getServletContext().getAttribute("ISFIRST");
        
        //If not then do the initialization
        if (isFirst == null){
            isFirst = "first";
            
            CategoryDAO categoryDAO = new CategoryDAO();
            List<Category> categories = categoryDAO.loadCategoryList();
            RoleDAO roleDAO = new RoleDAO();
            List<Role> roles = roleDAO.loadRoleList();
            
            getServletContext().setAttribute("ISFIRST", isFirst);
            getServletContext().setAttribute("CATEGORY-LIST", categories);
            getServletContext().setAttribute("ROLE-LIST", roles);
        }
        
        response.sendRedirect("home.jsp");
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
