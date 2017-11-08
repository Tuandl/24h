/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package luattlg.servlet.general;

import java.io.IOException;
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
import tuanvxm.other.Role;

/**
 * This servlet is for searching article. Redirect : With editor role -
 * manageArticle.jsp Other role - searchResult.jsp
 */
@WebServlet(name = "SearchArticleServlet", urlPatterns = {"/SearchArticle.action"})
public class SearchArticleServlet extends HttpServlet {

    private static String EDITOR = "tuanda/manage-article.jsp";
    private static String OTHER = "tuanda/search-result.jsp";
    private static final int GETTOP = 15;
    private static final int STARTDAY = 365;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int page_split = Integer.parseInt(request.getServletContext().getInitParameter("SIZEOFPAGE"));
        String searchType = request.getParameter("cbSearchType");
        String context = request.getParameter("txtSearch");
        int page = 1;
        try {
            page = Integer.parseInt(request.getParameter("txtPage"));
        } catch (Exception ex) {
            System.out.println("This is init");
        }
        page--;

        request.setAttribute("txtSearch", context);
        String role = (String) request.getSession().getAttribute("ROLE");
        UserDTO user = (UserDTO) request.getSession().getAttribute("USER");
        int userID = -1;
        if (user != null) {
            userID = user.getUserID();
        }
        List<ArticleDTO> articleList = null;
        if (searchType.equalsIgnoreCase("Writter")) {
            articleList = searchByWritter(context);
        } else {
            articleList = searchByName(context);
        }

        //Clear the hided article
        List<ArticleDTO> rawArticleList = articleList.subList(0, articleList.size());
        articleList = new ArrayList<ArticleDTO>();
        //System.out.println("raw article = "+rawArticleList.size());

        for (ArticleDTO article : rawArticleList) {
            if (article.getLastStatusChangerID() == userID || article.getStatus().equals(ArticleDTO.STATUS_AVAILABLE)) {
                articleList.add(article);
            }
            if (role.equalsIgnoreCase("Editor") && article.getStatus().equals(ArticleDTO.STATUS_NEW)) {
                articleList.add(article);
            }
        }
        //System.out.println("search result count = " + articleList.size());

        //Get top trend
        ArrayList<Role> listOfRole = (ArrayList<Role>) request.getServletContext().getAttribute("ROLE-LIST");
        List<UserDTO> listOfUserDTOs = new UserDAO().findByRoleID(getRoleID("journalist", listOfRole));
        HashMap<Integer, String> mapUser;
        mapUser = new HashMap<Integer, String>();
        for (UserDTO userr : listOfUserDTOs) {
            mapUser.put(new Integer(userr.getUserID()), userr.getName());
        }
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
        request.setAttribute("MAXPAGE", Math.min(1000, articleList.size()) / page_split + 1);
        request.setAttribute("SEARCH-RESULT-LIST", articleList.subList(page * page_split, Math.min((page + 1) * page_split, articleList.size())));
        if (role != null && role.equalsIgnoreCase("editor")) {
            request.getRequestDispatcher(EDITOR).forward(request, response);
        } else {
            request.getRequestDispatcher(OTHER).forward(request, response);
        }
    }

    //Method for searching article by writter
    private List<ArticleDTO> searchByWritter(String name) {
        List<ArticleDTO> listOfArticleDTOs = new ArrayList<>();

        //Get journalist role id;
        List<Role> listOfRole = (ArrayList<Role>) getServletContext().getAttribute("ROLE-LIST");
        int journalistRoleID = 0;
        for (Role role : listOfRole) {
            if (role.getName().equalsIgnoreCase("journalist")) {
                journalistRoleID = role.getRoleID();
                break;
            }
        }

        //Search all the journalist with that name and get all article of that journalist
        List<UserDTO> listOfUserDTOs = new UserDAO().findLikeName("%" + name + "%");
        for (UserDTO user : listOfUserDTOs) {
            if (user.getRoleID() == journalistRoleID) {
                //Get article
                List<ArticleDTO> articleOfJournalist = new ArticleDAO().findByCreatorID(user.getUserID());
                for (ArticleDTO article : articleOfJournalist) {
                    listOfArticleDTOs.add(article);
                }
            }
        }

        return listOfArticleDTOs;
    }

    //Method for searching article by article's name
    private List<ArticleDTO> searchByName(String name) {
        List<ArticleDTO> listOfArticleDTOs = new ArticleDAO().findByTitle("%" + name + "%");
        System.out.println("" + listOfArticleDTOs.size());
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
