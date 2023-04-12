/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.authentication;

import dal.AccountDBContext;
import dal.UserDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import model.Account;
import model.User;

/**
 *
 * @author dell
 */
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //take raw info
        String rawUser = req.getParameter("rawUser");
        String rawPass = req.getParameter("rawPass");
        
        //get account by username and password
        AccountDBContext AccCon = new AccountDBContext();
        Account acc = AccCon.getAccount(rawUser, rawPass);
        
        //if acc == null
        if(acc == (null)){
            
            System.out.println("Sai tên đăng nhập hoặc mật khẩu! Vui lòng thử lại!");
            
            //set error
//            req.getSession().setAttribute("errorLogin", "Username or password is not correct! Please try again!");
            
            //Username or password is not correct! Please try again!
            req.setAttribute("errorLogin", "Sai tên đăng nhập hoặc mật khẩu! Vui lòng thử lại!");
            
            //back to login
//            resp.sendRedirect("../user/login");
            
            req.getRequestDispatcher("../view/user/login.jsp").forward(req, resp);
            
        }
        //else: check verify
        else{
            
            //get status and key of accoutn
            String key = acc.getKey();
            boolean status = acc.isStatus();
            
            //if: status is false and key not null
            if(!status && !(key == null)){
                //account hasn't verified
                System.out.println("account not been verified!");
                
                //String message
                String message = "Tài khoản chưa được xác thực! Vui lòng thực hiện xác thực tài khoản!";
                
                //set message
                req.setAttribute("message", message);
                
                //back to login page
                req.getRequestDispatcher("../view/user/login.jsp").forward(req, resp);
                
            }
            //else: -> login to system
            else if(status){
                req.getSession().setAttribute("account", acc);

                //get user
                UserDBContext userContext = new UserDBContext();
                User user = userContext.getUser(acc.getUsername());

                //set sesssion user
                req.getSession().setAttribute("user", user);

                System.out.println("Login sucess");

                resp.sendRedirect("../home");
                
            }
        }
        
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //set null session
        req.getRequestDispatcher("../view/user/login.jsp").forward(req, resp);
    }
    
}
