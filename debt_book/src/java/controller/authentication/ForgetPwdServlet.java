/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.authentication;

import utils.SendMail;
import dal.AccountDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;

/**
 *
 * @author DELL
 */
public class ForgetPwdServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Forgetpassword</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Forgetpassword at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        request.getRequestDispatcher("../view/user/forgetpwd.jsp").forward(request, response);
    }

    public static String getAlphaNumericString() {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";
        StringBuilder sb = new StringBuilder(8);

        for (int i = 0; i < 8; i++) {
            int index
                    = (int) (AlphaNumericString.length()
                    * Math.random());
            sb.append(AlphaNumericString
                    .charAt(index));
        }
        return sb.toString();
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
        String captcha = request.getParameter("captcha");
        String email = request.getParameter("rawEmail");
        String username = request.getParameter("rawUser");

        HttpSession session = request.getSession();
        String code = (String) session.getAttribute("captcha");

        //if captcha wrong -> send back error message
        if (!captcha.equals(code)) {
            request.setAttribute("error", "Nhập sai Captcha !!");
            request.getRequestDispatcher("../view/user/forgetpwd.jsp").forward(request, response);
        } //else: rigth captcha -> check email
        else {
            AccountDBContext adc = new AccountDBContext();

            //get account by email and user name
            Account acc = adc.getAccountByEmailAndUserName(email, username);

            //if acc == null -> not have email -> send back error message
            if (acc == null) {
                request.setAttribute("error", "Username hoặc Email không đúng!!");
                request.getRequestDispatcher("../view/user/forgetpwd.jsp").forward(request, response);
            } //else: reset password
            else {

                //get new password string
                String newPassword = getAlphaNumericString();

                //generate account with new pass
                Account newAcc = new Account();
                newAcc.setUsername(acc.getUsername());
                newAcc.setPassword(newPassword);

                System.out.println("reseted password");

                //change new password
                int result = adc.change(newAcc);
                System.out.println("check reset password: " + result);
                //send mail

                //set mail subject
                String subject = "Reset password";

                //set string mail message:
                String message = " Here is your new password: " + newPassword;

                //generate SendMail object
                SendMail sendMail = new SendMail(email, subject, message);

                //generate a new thread for sending email
                Thread send = new Thread(sendMail);

                //start thread
                send.start();

//                if (newPassword == null) {
//                    request.setAttribute("error", "Email not existed");
//                    doGet(request, response);
//                }
//                else {
//                    adc.updateAccount(email, newPassword);
//                    response.sendRedirect("../user/login");
//                }
                //back to login
                //String message
                String loginMessage = "Vui lòng kiểm tra email để nhận mật khẩu mới!";

                //set message
                request.setAttribute("message", loginMessage);
                request.getRequestDispatcher("../view/user/login.jsp").forward(request, response);
            }
        }
    }
}
