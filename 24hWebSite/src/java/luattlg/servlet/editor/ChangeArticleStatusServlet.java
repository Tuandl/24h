/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package luattlg.servlet.editor;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tuanvxm.DAOs.ArticleDAO;
import tuanvxm.DTOs.ArticleDTO;
import tuanvxm.DTOs.UserDTO;

/**
 * This servlet is for editor to change article status.
 */
@WebServlet(name = "ChangeArticleStatusServlet", urlPatterns = {"/ChangeArticleStatus.action"})
public class ChangeArticleStatusServlet extends HttpServlet {
    private static final String SUCCESS = "tuanda/censor-page.jsp";
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
        
            int articleID = Integer.parseInt(request.getParameter("txtArticleID"));
            UserDTO user = (UserDTO)request.getSession().getAttribute("USER");
            String newArticleStatus = request.getParameter("txtArticleStatus");
//            System.out.println("Change article status: ");
//            System.out.println("article id = " + articleID);
//            System.out.println("new status = " + newArticleStatus);
//            System.out.println("User id "+user.getUserID());

            //Update
            ArticleDAO articleDAO = new ArticleDAO();
            ArticleDTO article = articleDAO.findByArticleID(articleID);
            article.setStatus(newArticleStatus);
            article.setLastModifiedTime(new Timestamp(new Date().getTime()));
            article.setLastStatusChangerID(user.getUserID());
            articleDAO.changeStatus(article);
            request.setAttribute("SUCCESS", "Chuyển trạng thái bài thành "+newArticleStatus+" thành công");
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
