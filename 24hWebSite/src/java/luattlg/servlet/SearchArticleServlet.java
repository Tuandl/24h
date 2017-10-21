/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package luattlg.servlet;

import java.io.IOException;
import java.util.ArrayList;
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
 * This servlet is for searching article. 
 * Redirect :
 * With editor role - manageArticle.jsp
 * Other role - searchResult.jsp
 */
@WebServlet(name = "SearchArticleServlet", urlPatterns = {"/SearchArticle.action"})
public class SearchArticleServlet extends HttpServlet {

    
    private static String EDITOR = "manageArticle.jsp";
    private static String OTHER = "searchResult.jsp";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String searchType = request.getParameter("cbSearchType");
        String context = request.getParameter("txtSearch");
        String role = (String)request.getSession().getAttribute("ROLE");
        UserDTO user = (UserDTO)request.getSession().getAttribute("USER");
        
        List <ArticleDTO> articleList = null;
        if(searchType.equalsIgnoreCase("Writter")){
            articleList = searchByWritter(context);
        }else{
            articleList = searchByName(context);
        }
        
        //Clear the hided article
        List<ArticleDTO> rawArticleList = articleList;
        articleList = new ArrayList<ArticleDTO>();
        for(ArticleDTO article : rawArticleList){
            if(article.getLastStatusChangerID() == user.getUserID() || article.getStatus().equals(ArticleDTO.STATUS_AVAILABLE)){
                articleList.add(article);
            }
            if(role.equalsIgnoreCase("Editor") && article.getStatus().equals(ArticleDTO.STATUS_NEW)){
                articleList.add(article);
            }
        }
        
        request.setAttribute("SEARCH-RESULT-LIST", articleList);
        if(role.equalsIgnoreCase("editor")){
            request.getRequestDispatcher(EDITOR).forward(request, response);
        }else{
            request.getRequestDispatcher(OTHER).forward(request, response);
        }
    }

    //Method for searching article by writter
    private List<ArticleDTO> searchByWritter(String name){
        List<ArticleDTO> listOfArticleDTOs = new ArrayList<>();
        
        //Get journalist role id;
        List<Role> listOfRole = (ArrayList<Role>)getServletContext().getAttribute("ROLE-LIST");
        int journalistRoleID = 0;
        for(Role role : listOfRole){
            if(role.getName().equalsIgnoreCase("journalist")){
                journalistRoleID = role.getRoleID();
                break;
            }
        }

        //Search all the journalist with that name and get all article of that journalist
        List<UserDTO> listOfUserDTOs = new UserDAO().findLikeName(name);
        for(UserDTO user : listOfUserDTOs){
            if(user.getRoleID() == journalistRoleID){
                //Get article
                List<ArticleDTO> articleOfJournalist = new ArticleDAO().findByCreatorID(user.getUserID());
                for(ArticleDTO article : articleOfJournalist){
                    listOfArticleDTOs.add(article);
                }
            }
        }
        
        return listOfArticleDTOs;
    }
    
    //Method for searching article by article's name
    private List<ArticleDTO> searchByName(String name){
        List<ArticleDTO> listOfArticleDTOs = new ArticleDAO().findByTitle(name);
        return listOfArticleDTOs;
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
