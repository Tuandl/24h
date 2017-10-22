/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package luattlg.servlet.general;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tuanvxm.DAOs.CommentDAO;
import tuanvxm.DTOs.CommentDTO;
import tuanvxm.DTOs.UserDTO;

/**
 * Servlet is for add a comment into database.
 * Always foward to ReadArticle.action.
 */
@WebServlet(name = "CreateCommentServlet", urlPatterns = {"/CreateComment.action"})
public class CreateCommentServlet extends HttpServlet {

    private static final String SUCCESS = "ReadArticle.action";
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
        String commentContent = request.getParameter("txtComment");
        UserDTO user = (UserDTO)request.getSession().getAttribute("USER");
        Timestamp createdTime = new Timestamp(new Date().getTime());
        
        CommentDTO newComment = new CommentDTO(user.getUserID(), articleID, createdTime, commentContent, CommentDTO.STATUS_AVAILABLE);
        if(!new CommentDAO().createComment(newComment)){
            request.setAttribute("CREATE-COMMENT-ERROR", "There are some errors when you are trying to comment. Please try again");
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
