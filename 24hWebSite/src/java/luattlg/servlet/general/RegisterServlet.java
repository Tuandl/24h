/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package luattlg.servlet.general;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tuanvxm.DAOs.UserDAO;
import tuanvxm.DTOs.UserDTO;
import tuanvxm.other.GenderList;
import tuanvxm.other.RoleList;

/**
 *
 * @author luattlgse62386
 */
@WebServlet(name = "RegisterServlet", urlPatterns = {"/Register.action"})
public class RegisterServlet extends HttpServlet {

    private static final String RESULT = "tuanda/index.jsp";

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

        try {
            //Get data 
            String username = request.getParameter("txtUsername");
            String password = request.getParameter("pwfPassword");
            String confirmPassword = request.getParameter("pwfConfirmPassword");
            String name = request.getParameter("txtName");
            DateFormat format = DateFormat.getDateInstance();
            Date getTime = format.parse(request.getParameter("txtDateOfBirth"));
            Timestamp dateOfBirTimestamp = new Timestamp(getTime.getTime());
            int gender = GenderList.toInt(request.getParameter("cbGender"));
            String address = request.getParameter("txtAddress");
            String phoneNumber = request.getParameter("txtPhoneNumber");
            String email = request.getParameter("txtEmail");
            String identityCard = request.getParameter("identityCard");
            int roleID = RoleList.getID("reader");
            
            //Create new user and insert to data base
            UserDTO user = new UserDTO(username, password, name, dateOfBirTimestamp, gender, address, phoneNumber, email, identityCard, roleID, UserDTO.STATUS_AVAILABLE);
            if(!new UserDAO().createUser(user)){
                Map<String,String> error = new HashMap<String, String>();
                error.put("EXISTENCE", "This user is already exist. Please choose another");
                request.setAttribute("ERROR", error);
                request.setAttribute("OLD-INFO", user);
            }
        } catch (Exception ex) {
            System.out.println("Register controller " + ex.getMessage());
            log(ex.getMessage());
        }
        finally{
            System.out.println(RESULT);
            request.getRequestDispatcher(RESULT).forward(request, response);
        }
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
