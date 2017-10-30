/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package luattlg.servlet.journalist;

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
import tuanvxm.DAOs.ArticleDAO;
import tuanvxm.DTOs.ArticleDTO;
import tuanvxm.DTOs.UserDTO;
import tuanvxm.other.Category;

/**
 *
 * @author luattlgse62386
 */
@WebServlet(name = "UpdateArticleServlet", urlPatterns = {"/UpdateArticle.action"})
public class UpdateArticleServlet extends HttpServlet {
        
        private static final String JOURNALIST  = "/tuanda/journalist-manage-articles.jsp";

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
        String title = request.getParameter("txtTitle");
        String headine = request.getParameter("txtHeadline");
        String content = request.getParameter("txtContent");
        String thumbnail = request.getParameter("txtThumbnailURL");
        String category = request.getParameter("cbCategory");
        List<Category> categoryList = (ArrayList<Category>)getServletContext().getAttribute("CATEGORY-LIST");
        int categoryID = 0;
        for(Category cate : categoryList){
            if(cate.getName().equalsIgnoreCase(category)){
                categoryID = cate.getCategoryID();
            }
        }
        UserDTO user = (UserDTO)request.getSession().getAttribute("USER");
        Timestamp changeTime = new Timestamp(new Date().getTime());
        
        ArticleDTO articleDTO = new ArticleDAO().findByArticleID(articleID);
        articleDTO.setLastModifiedTime(changeTime);
        articleDTO.setLastModifierID(user.getUserID());
        articleDTO.setCategoryID(categoryID);
        articleDTO.setTitle(title);
        articleDTO.setHeadline(headine);
        articleDTO.setContent(content);
        articleDTO.setThumbnail(thumbnail);
        
        if(!new ArticleDAO().updateArticle(articleDTO)){
            request.setAttribute("ERROR", "Có lỗi xảy ra. Vui lòng thử lại");
        }else{
            request.setAttribute("SUCCESS", "Sửa bài thành công");
        }
        request.getRequestDispatcher(JOURNALIST).forward(request, response);
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
