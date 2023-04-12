/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.authentication;

import controller.authentication.BaseAuthenticationController;
import dal.AccountDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import model.Account;

/**
 *
 * @author lyx
 */
public class ChangepwdServlet extends BaseAuthenticationController {

    @Override
    protected void processPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String user = req.getParameter("user");
        String rawOldpass = req.getParameter("rawOldpass");
        String rawNewpass = req.getParameter("rawNewpass");
        String rawConfirm = req.getParameter("rawConfirm");
        String captcha = req.getParameter("captcha");
        AccountDBContext db = new AccountDBContext();
        Account a = db.checkAccount(user, rawOldpass);

        //validation
        boolean valid = true;

        //generate string to show for user
        String error = "";

        //valid password: 6 - 18 char, digit
        if (!rawNewpass.matches("^[0-9a-zA-Z]{6,18}$")) {
            error += " Password is not valid!";
            valid = false;
        }

        //validate confirm password
        if (rawConfirm.matches("^[0-9a-zA-Z]{6,18}$")) {

            //check confirm password equal password or not
            if (!rawConfirm.equals(rawNewpass)) {
                error += " Confirm password is not equals with password!";
                valid = false;

            }
        } else {
            error += " Confirm password is not valid!";
            valid = false;
        }

        /*captcha*/
        // Get the captcha value stored in the session
        HttpSession session = req.getSession();
        String storedCaptcha = (String) session.getAttribute("captcha");

        // Validate the user input
        if (captcha.equals(storedCaptcha)) {
            // Captcha is correct, save the user information to a database

            // notification
            resp.getWriter().println("Right Captcha!");
            //request.getWriter().println("right captcha");

        } else {
            // Captcha is incorrect, show an error message

            // notification
            resp.getWriter().println("Wrong Captcha!");
            //request.().println("wrong captcha");
            error += " Captcha is not correct!";
            valid = false;
        }

        if (!valid) {
            //set error session
            req.getSession().setAttribute("error", error);

            //back to register and show error
            resp.sendRedirect("changepwd.jsp");
        } //else: username, password is valid -> add into database
        else {
            //db contact
            Account acc = new Account(user, rawNewpass, null, a.getEmail(), a.isStatus(), a.getRegisterDate(), a.getRoleID());
            acc.setUsername(user);
            acc.setPassword(rawNewpass);
            db.change(acc);

            HttpSession session1 = req.getSession();
            session1.setAttribute("account", acc);
            resp.getWriter().println("Change password successful!");
            //back to login page
            resp.sendRedirect("../user/login");
        }
    }

    @Override
    protected void processGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("../view/user/changepwd.jsp").forward(req, resp);
    }

}
