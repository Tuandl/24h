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
import tuanvxm.DAOs.UserDAO;
import tuanvxm.DTOs.ArticleDTO;
import tuanvxm.DTOs.UserDTO;
import tuanvxm.other.Category;
import tuanvxm.other.CategoryList;
import tuanvxm.other.Role;

/**
 *
 * @author luattlgse62386
 */
@WebServlet(name = "LoadCategoryArticlesServlet", urlPatterns = {"/LoadCategoryArticles.action"})
public class LoadCategoryArticlesServlet extends HttpServlet {

    private static final String CATEGORY = "tuanda/category.jsp";
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
        int categoryID = 0;

        categoryID = Integer.parseInt(request.getParameter("categoryID"));
        request.setAttribute("categoryID", categoryID);
        int page = Integer.parseInt(request.getParameter("txtPage"));
                
        ArrayList<Role> listOfRole = (ArrayList<Role>) request.getServletContext().getAttribute("ROLE-LIST");
        List<UserDTO> listOfUserDTOs = new UserDAO().findByRoleID(getRoleID("journalist", listOfRole));
        HashMap<Integer, String> mapUser;
        mapUser = new HashMap<Integer, String>();
        for (UserDTO user : listOfUserDTOs) {
            mapUser.put(new Integer(user.getUserID()), user.getName());
        }
        
        List<ArticleDTO> articles = searchByCategory(categoryID);
        for(ArticleDTO article : articles){
            article.setCreator(mapUser.get(new Integer(article.getCreatorID())));
            System.out.println(""+mapUser.get(new Integer(article.getCreatorID())));
        }
        
        request.setAttribute("categoryID", categoryID);
        request.setAttribute("CATEGORY-NAME", CategoryList.getName(categoryID));
        request.setAttribute("CATEGORY-ARTICLE", articles.subList(page*20, Math.min((page+1)*20,articles.size())));

        System.out.println("number of article in category: " + articles.size());

        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, -STARTDAY);
        Timestamp time = new Timestamp(calendar.getTime().getTime());
        ArrayList<ArticleDTO> articlesTopTrend = (ArrayList) new ArticleDAO().findTopViewCountCreatedAfterTime(GETTOP, time);
        ArrayList<ArticleDTO> topTrendAfetDelete = new ArrayList<>();
        for (ArticleDTO trendArticle : articlesTopTrend) {
            if (trendArticle.getStatus().equalsIgnoreCase(ArticleDTO.STATUS_AVAILABLE)) {
                trendArticle.setCreator(mapUser.get(new Integer(trendArticle.getCreatorID())));
                topTrendAfetDelete.add(trendArticle);
            }
        }
        request.setAttribute("TOP-TREND-LIST", topTrendAfetDelete);
        request.getRequestDispatcher(CATEGORY).forward(request, response);
    }

    private List<ArticleDTO> searchByCategory(int categoryID) {
        List<ArticleDTO> listOfArticleDTOs = new ArrayList<>();

        //Get journalist role id;
        listOfArticleDTOs = new ArticleDTO().findByCategoryIDAndStatus(categoryID, ArticleDTO.STATUS_AVAILABLE);
        return listOfArticleDTOs;
    }
    
    private int getRoleID(String rolename, ArrayList<Role> listOfRole) {
        //System.out.println("Get Role ID home page filter here "+listOfRole.size());
        for (Role role : listOfRole) {
            if (role.getName().equalsIgnoreCase(rolename)) {
                return role.getRoleID();
            }
        }
        return 0;
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
