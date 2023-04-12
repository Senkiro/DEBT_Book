/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import controller.authentication.BaseAuthenticationController;
import dal.AccountDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import model.Account;

/**
 *
 * @author dell
 */
public class HomeServlet extends BaseAuthenticationController {

    @Override
    protected void processPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //take username of account need to delete
        String username = req.getParameter("user");
        
        //delete account by uername
        resp.getWriter().println("username: "+username);
        
        AccountDBContext accConnnect = new AccountDBContext();
        
        //delete account by username
//        int checkDel = accConnnect.deleteAccount(username);
//        
//        //if checkDel == 1 -> delete success
//        if(checkDel == 1){
//            resp.getWriter().println("Delete success!");
//            resp.sendRedirect("home");
//        }else if (checkDel == 0){
//            resp.getWriter().println("Delete fail!");
//        }else{
//            resp.getWriter().println("checkDel != 1 and 0 ");
//        }
        
    }

    @Override
    protected void processGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //test: show all account

        //get all account from database
        AccountDBContext AccCon = new AccountDBContext();

        //get all account in database
        ArrayList<Account> listAccount = AccCon.getAllAccount();
        
        //set session listAccount
        req.getSession().setAttribute("listAccount", listAccount);
        
        //move to home.jsp
        req.getRequestDispatcher("view/home/dashboard.jsp").forward(req, resp);
        
    }      
}
