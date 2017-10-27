/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package luattlg.servlet.general;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tuanvxm.DAOs.UserDAO;
import tuanvxm.DTOs.UserDTO;
import tuanvxm.other.GenderList;

/**
 *
 * @author luattlgse62386
 */
@WebServlet(name = "UpdateInformationServlet", urlPatterns = {"/UpdateInformation.action"})
public class UpdateInformationServlet extends HttpServlet {

    private static final String PATH = "info.jsp";
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
            //Get information
            String name = request.getParameter("txtName");
//            DateFormat format = DateFormat.getDateInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            Date getTime = sdf.parse(request.getParameter("txtDateOfBirth").substring(0, 9));
            Timestamp dateOfBirTimestamp = new Timestamp(getTime.getTime());
            int gender = GenderList.toInt(request.getParameter("cbGender"));
            String address = request.getParameter("txtAddress");
            String phoneNumber = request.getParameter("txtPhoneNumber");
            String email = request.getParameter("txtEmail");
            String identityCard = request.getParameter("identityCard");
            
            //Update
           UserDTO user = (UserDTO)request.getSession().getAttribute("USER");
           user.setName(name);
           user.setBirthday(dateOfBirTimestamp);
           user.setGender(gender);
           user.setAddress(address);
           user.setPhone(phoneNumber);
           user.setEmail(email);
           user.setPeopleIndentityCard(identityCard);
           
           if(!new UserDAO().updateUser(user)){
               request.setAttribute("ERROR", "Có lỗi xảy ra. Vui lòng thử lại sau");
           }else{
               request.setAttribute("USER", user);
           }
           request.getRequestDispatcher(PATH).forward(request, response);
        } catch (Exception ex) {
            System.out.println("" + ex);
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
