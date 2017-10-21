/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package luattlg.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tuanvxm.DAOs.ArticleDAO;
import tuanvxm.DAOs.CommentDAO;
import tuanvxm.DTOs.ArticleDTO;
import tuanvxm.DTOs.CommentDTO;

/**
 * This servlet call for loading article from database.
 * Redirect :
 * Article is not removed - article.jsp
 * Article is removed - HTTP 404 ERROR page
 */
@WebServlet(name = "ReadArticleServlet", urlPatterns = {"/ReadArticle.action"})
public class ReadArticleServlet extends HttpServlet {

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
        
        int articleID = Integer.parseInt(request.getParameter("articleID"));
        ArticleDTO article = new ArticleDAO().findByCategoryIDAndStatus(articleID, ArticleDTO.STATUS_AVAILABLE);
        
        if(article == null){
            response.sendError(response.SC_NOT_FOUND, "Article not found or this article has been removed.");
            return;
        }
        
        //Get comment of the article
        List<CommentDTO> commentList = new CommentDAO().findByArticleID(articleID);
        article.increaseViewCount();
        request.setAttribute("ARTICLE", article);
        request.setAttribute("COMMENTLIST", commentList);
        
        request.getRequestDispatcher("article.jsp").forward(request, response);
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
