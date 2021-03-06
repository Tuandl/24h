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
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tuanvxm.DAOs.ArticleDAO;
import tuanvxm.DAOs.CommentDAO;
import tuanvxm.DAOs.UserDAO;
import tuanvxm.DTOs.ArticleDTO;
import tuanvxm.DTOs.CommentDTO;
import tuanvxm.DTOs.UserDTO;
import tuanvxm.other.CategoryList;
import tuanvxm.other.RoleList;

/**
 * This servlet is for loading article and comment of that article from
 * database. Redirect : Article is not removed - article page. Article is
 * removed - HTTP 404 ERROR page.
 */
@WebServlet(name = "ReadArticleServlet", urlPatterns = {"/ReadArticle.action"})
public class ReadArticleServlet extends HttpServlet {

    private static final String ARTICLE = "tuanda/article.jsp";
    private static final int GETTOP = 15;
    private static final int STARTDAY = 365;

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
        UserDAO userDAO = new UserDAO();
        ArticleDTO article = new ArticleDAO().findByArticleID(articleID);
        article.setCreator(userDAO.findByUserID(article.getCreatorID()).getName());
        article.setViewCount(article.getViewCount()+1);
        
        if (article == null) {
            response.sendError(response.SC_NOT_FOUND, "Article not found or this article has been removed.");
            return;
        }

        //Get comment of the article
        List<CommentDTO> commentList = new CommentDAO().findByArticleID(articleID);
        article.increaseViewCount();

        UserDTO user = (UserDTO) request.getSession().getAttribute("USER");
        int userID = -1;
        if (user != null) {
            userID = user.getUserID();
        }

        //Hide comment
        List<CommentDTO> afterDeleteList = new ArrayList<CommentDTO>();
        for (CommentDTO comment : commentList) {
            if (comment.getStatus().equalsIgnoreCase(CommentDTO.STATUS_AVAILABLE) || comment.getLastStatusChangerID() == userID) {         
                comment.setCreator(userDAO.findByUserID(comment.getCreatorID()).getName());
                afterDeleteList.add(comment);
            }
        }

        //Get top trend
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, -STARTDAY);
        Timestamp time = new Timestamp(calendar.getTime().getTime());
        ArrayList<ArticleDTO> articles = (ArrayList) new ArticleDAO().findTopViewCountCreatedAfterTime(GETTOP, time);
        ArrayList<ArticleDTO> topTrendAfetDelete = new ArrayList<>();
        List<UserDTO> listOfUserDTOs = new UserDAO().findByRoleID(RoleList.getID("journalist"));
        HashMap<Integer, String> mapUser;
        mapUser = new HashMap<Integer, String>();
        for (UserDTO userOfList : listOfUserDTOs) {
            mapUser.put(new Integer(userOfList.getUserID()), userOfList.getName());
        }
        for (ArticleDTO trendArticle : articles) {
            if (trendArticle.getStatus().equalsIgnoreCase(ArticleDTO.STATUS_AVAILABLE)) {
                trendArticle.setCreator(mapUser.get(new Integer(trendArticle.getCreatorID())));
                topTrendAfetDelete.add(trendArticle);
            }
        }

        request.setAttribute("COMMENT-LIST", afterDeleteList);
        request.setAttribute("ARTICLE", article);
        request.setAttribute("CATEGORYNAME", CategoryList.getName(article.getCategoryID()));
        //System.out.println("Top trend: " + articles.size());
        request.setAttribute("TOP-TREND-LIST", topTrendAfetDelete);
        
        request.getRequestDispatcher(ARTICLE).forward(request, response);
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
