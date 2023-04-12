/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.register;

import dal.AccountDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;
import model.Account;
import utils.SendMail;

/**
 *
 * @author Hoang Son
 */
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //take raw info
        String rawUser = req.getParameter("rawUser");
        String rawPass = req.getParameter("rawPass");
        String rawConfirm = req.getParameter("rawConfirm");
        String rawEmail = req.getParameter("rawEmail");
        String captcha = req.getParameter("captcha");

        //validation
        boolean valid = true;

        //generate string to show for user
        String error = "";

        //valid username: character from 6 to 12 chars
        if (!rawUser.matches("^[a-zA-Z0-9]{6,12}$")) {
            error += " Tên đăng nhập không hợp lệ!";
            valid = false;
        }

        //valid password: 6 - 18 char, digit
        if (!rawPass.matches("^[0-9a-zA-Z]{6,18}$")) {
            error += " Mật khẩu không hợp lệ!";
            valid = false;
        }

        //validate confirm password
        if (rawPass.matches("^[0-9a-zA-Z]{6,18}$")) {
            //check confirm password equal password or not
            if (!rawPass.equals(rawConfirm)) {
                error += " Mật khẩu nhập lại không trùng với mật khẩu trước đó!";
                valid = false;

            }
        } else {
            error += " Mật khẩu nhập lại không hợp lệ!";
            valid = false;
        }

        //validate email: "^\S+@\S+\.\S+$"
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

        if (!rawEmail.matches(emailRegex)) {
            valid = false;
            error += " Email không hợp lệ!";
        }

        /*captcha*/
        // Get the captcha value stored in the session
        HttpSession session = req.getSession();
        String storedCaptcha = (String) session.getAttribute("captcha");

        // Validate the captcha input
        if (captcha.equals(storedCaptcha)) {
            // Captcha is correct, save the user information to a database

        } else {
            // Captcha is incorrect, show an error message

            // notification
            System.out.println("captcha sai");
            error += " Captcha không chính xác! Vui lòng thử lại!";
            valid = false;
        }

        //if not valid -> show error
        if (!valid) {
            //set error session
            req.setAttribute("error", error);

            //back to register and show error
//            resp.sendRedirect("register");
            req.getRequestDispatcher("../view/user/register.jsp").forward(req, resp);
        } //else: username, password is valid -> add into database
        else {
            //db contact
            AccountDBContext AccCon = new AccountDBContext();

            //check username existed or not by username and email
            Account acc = AccCon.getUser(rawUser, rawEmail);

            //if acc == null -> not existed before -> created new
            if (acc == null) {

                //register account: add new user into database with status is: false -> not verify
                //take current date -> add into database as: dateRegister
                Date date = new Date();

                //send verify email
                //generate a String of subject mail
                String subject = "Xác thực tài khoản";

                //generate a random string by UUID
                UUID randomString = UUID.randomUUID();

                //key
                String key = randomString.toString();

                //add new account into database
                int add = AccCon.addAccount(rawUser, rawPass, rawEmail, key, date);
                
                //generate a String of message for verify
                String message = "Nhấn vào link sau để xác minh tài khoản: http://localhost:9999/ProjectV1/user/verify?key=" + key;
                
                //generate a sendMail class with a parameter is rawEmail (which have been inputed by user)
                SendMail sendMail = new SendMail(rawEmail, subject, message);

                //generate a thread
                Thread send = new Thread(sendMail);

                //start thread
                send.start();

                //if add success 
                if (add == 1) {
                    //show notification: register success
                    System.out.println("insert into database success!");
                    
                    req.setAttribute("message", "Kiểm tra email và kích hoạt tài khoản để đăng nhập hệ thống!");

                    //back to login page
//                    resp.sendRedirect("../user/login");
                    req.getRequestDispatcher("../view/user/login.jsp").forward(req, resp);
                } else {
                    System.out.println("insert into database fail!");
                    
                    req.setAttribute("error", "Đăng ký tài khoản thất bại! Vui lòng thử lại!");

                    //back to login page
//                    resp.sendRedirect("../user/register");
                    req.getRequestDispatcher("../view/user/register.jsp").forward(req, resp);
                }

            } //else -> show alert to tell user choose other username
            else {
                //error: account have existed
                String errorUsernameExisted = "Tên đăng nhập hoặc email đã tồn tại! Vui lòng chọn tên đăng nhập hoặc email khác!";
                req.setAttribute("error", errorUsernameExisted);

                //back to register with error alert                
//                resp.sendRedirect("register");
                req.getRequestDispatcher("../view/user/register.jsp").forward(req, resp);
            }

        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("../view/user/register.jsp").forward(req, resp);
    }

}
