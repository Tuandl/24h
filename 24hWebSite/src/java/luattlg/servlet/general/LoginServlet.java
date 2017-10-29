/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package luattlg.servlet.general;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tuanvxm.DAOs.UserDAO;
import tuanvxm.DTOs.UserDTO;
import tuanvxm.other.CategoryList;
import tuanvxm.other.RoleList;

/**
* This servlet is for login action.
* This servlet will redirect base on user's role:
* Admin : admin.jsp
* Editor : editor.jsp
* Journalist : journalist.jsp
* Reader : reader.jsp
* Guest (Login fail) : home.jsp
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/Login.action"})
public class LoginServlet extends HttpServlet {
    
    private static final String ADMIN = "tuanda/admin-home-page.jsp";
    private static final String EDITOR = "tuanda/censor-page.jsp";
    private static final String JOURNALIST = "tuanda/journalist-manage-articles.jsp";
    private static final String READER = "tuanda/index.jsp";
    private static final String GUEST = "tuanda/index.jsp";

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
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("pwfPassword");
        
        UserDTO user = new UserDTO();
        user.setUsername(username);
        user.setPassword(password);
        
        
        if(user.login()){
            String role = RoleList.getName(user.getRoleID());
//            System.out.println("role = " + role);
            user = new UserDAO().findByUserName(username);
            request.getSession().setAttribute("ROLE", role);
            request.getSession().setAttribute("USER", user);
            request.setAttribute("SUCCESS", "Xin chào " + user.getName());
            
            if(role.equalsIgnoreCase("Administrator")){
                request.getRequestDispatcher(ADMIN).forward(request, response);
            }
            if(role.equalsIgnoreCase("editor")){
                request.getRequestDispatcher(EDITOR).forward(request, response);
            }
            if(role.equalsIgnoreCase("journalist")){
                request.getRequestDispatcher(JOURNALIST).forward(request, response);
            }
            if(role.equalsIgnoreCase("reader")){
                request.getRequestDispatcher(READER).forward(request, response);
            }
            
            return;
        }
        request.removeAttribute("txtUsername");
        request.removeAttribute("pwfPassword");
        request.setAttribute("ERROR", "Tên đăng nhập hoặc mật khẩu không đúng.");
        request.getRequestDispatcher(GUEST).forward(request, response);
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
