/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package luattlg.servlet.general;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tuanvxm.DAOs.CommentDAO;
import tuanvxm.DTOs.CommentDTO;
import tuanvxm.DTOs.UserDTO;
import tuanvxm.other.Role;

/**
 * This servlet is for changing the comment's status. Need ArticleID, CommentID,
 * new status. Response to ReadArticle.Action.
 */
@WebServlet(name = "ChangeCommentStatusServlet", urlPatterns = {"/ChangeCommentStatus.action"})
public class ChangeCommentStatusServlet extends HttpServlet {

    private static final String SUCCESS = "ReaArticle.action";

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
        int commentID = Integer.parseInt(request.getParameter("commentID"));
        String newStatus = request.getParameter("txtStatus");
        Timestamp createTime = new Timestamp(new Date().getTime());
        UserDTO user = (UserDTO) request.getSession().getAttribute("USER");
        List<Role> listOfRole = (ArrayList<Role>) getServletContext().getAttribute("ROLE-LIST");
        String roleName = "";
        for (Role role : listOfRole) {
            if (role.getRoleID() == user.getRoleID()) {
                roleName = role.getName();
            }
        }

        //Format new status 
        if (newStatus.equalsIgnoreCase("Hide")) {
            if (roleName.equalsIgnoreCase("editor")) {
                newStatus = CommentDTO.STATUS_HIDE_BY_EDITOR;
            } else {
                newStatus = CommentDTO.STATUS_HIDE_BY_READER;
            }
        } else {
            newStatus = CommentDTO.STATUS_AVAILABLE;
        }
        
        CommentDTO comment = new CommentDTO(commentID, newStatus, articleID, createTime);
        if(!new CommentDAO().changeStatus(comment)){
            request.setAttribute("ERROR-CHANGE-COMMENT-STATUS", "Some errors occurs. Please try again");
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
