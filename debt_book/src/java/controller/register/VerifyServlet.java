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
import java.io.IOException;
import model.Account;

/**
 *
 * @author dell
 */
public class VerifyServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //get verify key
        String key = req.getParameter("key");
        
        System.out.println("key: "+key);
        
        //check key
        
        AccountDBContext accountDAO = new AccountDBContext();
        
        Account account = accountDAO.getAccountByKey(key);
        boolean verify = false;
        
        //if account not null
        if(!(account == null)){
            //update status and update status of key
            int check = accountDAO.verifyAccount(key);
            
            System.out.println("check : "+check);
            
            //show link back to login
            
            //set verify = true;
            verify = true;
            
            req.setAttribute("verify", verify);
            
            System.out.println("check verify: "+req.getAttribute("verify"));
            
//            resp.sendRedirect("../view/verify-account.jsp");
            req.getRequestDispatcher("../view/verify-account.jsp").forward(req, resp);
//            resp.sendRedirect("../user/login");
        }else{
            //set verified = false;
            req.setAttribute("verify", verify);
            
            System.out.println("Error: account have been verified!"+verify);
            
//            resp.sendRedirect("../view/verify-account.jsp");
            req.getRequestDispatcher("../view/verify-account.jsp").forward(req, resp);
        }
        
        
    }
    
    
}
