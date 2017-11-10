/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package luattlg.servlet.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import luattlg.other.General;
import tuanvxm.DAOs.UserDAO;
import tuanvxm.DTOs.UserDTO;

/**
 *
 * @author luattlgse62386
 */
@WebServlet(name = "SearchUserByName", urlPatterns = {"/SearchUserByName.action"})
public class SearchUserByName extends HttpServlet {
    private static final String FOWARD = "/tuanda/admin-view-account-page.jsp";
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
        
        int page_split = Integer.parseInt(getServletContext().getInitParameter("SIZEOFPAGE"));
        String search = request.getParameter("txtSearch");
        int page = 1;
        try {
            page = Integer.parseInt(request.getParameter("txtPage"));
        } catch (Exception ex) {
            System.out.println("This is the init");
        }
        page--;
        request.setAttribute("curPage", page);
        List<UserDTO> listOfUser = new UserDAO().findLikeName("%" + search + "%");
        System.out.println("Size "+listOfUser.size());
        request.setAttribute("MAXPAGE", General.getMaxPage(listOfUser.size(), page_split));
        request.setAttribute("SEARCHRESULT", listOfUser.subList(page*page_split, Math.min((page+1)*page_split, listOfUser.size())));
        request.getRequestDispatcher(FOWARD).forward(request, response);
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
