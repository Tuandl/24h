/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package luattlg.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tuanvxm.DTOs.ArticleDTO;
import tuanvxm.other.Category;
import tuanvxm.other.CategoryList;
import tuanvxm.other.GenderList;
import tuanvxm.other.RoleList;

/**
* This servlet is for initializing the category list 
* the role list, the gender list then load them into the application scope.
* Get the list of articles that show in home page by map the category and the list of articles.
* On the other hand, set the role of the user to Guest.
* Redirect to home page
*/
@WebServlet(name = "InitServlet", urlPatterns = {"/Init.action"})
public class InitServlet extends HttpServlet {
    
    private static final String HOMEPAGE = "home.jsp";

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
        
        //If it's the first time, do the initialization
        if (isFirst == null){
            isFirst = "first";
            getServletContext().setAttribute("ISFIRST", isFirst);
            getServletContext().setAttribute("CATEGORY-LIST", CategoryList.CATEGORY_LIST);           
            getServletContext().setAttribute("GENDER-LIST", GenderList.GENDER_LIST);
            getServletContext().setAttribute("ROLE-LIST", RoleList.ROLE_LIST);
        }
        
        //Set the role to guest
        request.getSession().setAttribute("ROLE", "guest");
        response.sendRedirect(HOMEPAGE);
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
