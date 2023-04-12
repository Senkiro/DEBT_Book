/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.AccountDBContext;
import dal.UserDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Session;
import model.Account;
import model.User;
import org.apache.tomcat.util.buf.B2CConverter;

/**
 *
 * @author NGO
 */
public class ProfileServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ProfileServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProfileServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        //get all account from database
        
        User user = (User) request.getSession().getAttribute("user");
        if(user != null){
            request.getSession().setAttribute("userDisplayName", user.getDisplayName());
            request.getSession().setAttribute("userUsername", user.getUsername());
            request.getSession().setAttribute("userDob", user.getDob());
            request.getSession().setAttribute("userGender", user.isGender());
            request.getSession().setAttribute("userAddress", user.getAddress());
            
            request.getRequestDispatcher("../view/user/profile.jsp").forward(request, response);
        }
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {
            UserDBContext context = new UserDBContext();
            
            String username = request.getParameter("username");
            String displayName = request.getParameter("displayName");
            String address = request.getParameter("address");
            String dob = request.getParameter("dob");
            String gender = request.getParameter("gender");
            
            context.updateProfile(username, displayName, gender.equals("1"), dob, address);
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            user.setUsername(username);
            user.setDisplayName(displayName);
            user.setAddress(address);
            user.setDob(new SimpleDateFormat("dd-MM-yyyy").parse(dob));
            user.setGender(gender.equals("1"));
            session.setAttribute("user", user);
            request.getRequestDispatcher("../view/user/profile.jsp").forward(request, response);
            //  response.sendRedirect("../home");
        } catch (ParseException ex) {
            Logger.getLogger(ProfileServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
