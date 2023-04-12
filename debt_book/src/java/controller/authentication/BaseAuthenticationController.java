/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.authentication;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author dell
 */
public abstract class BaseAuthenticationController extends HttpServlet {
    
    public boolean isAuthenticated(HttpServletRequest req){
        return req.getSession().getAttribute("account") != null;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(isAuthenticated(req)){
            processPost(req, resp);
        }else{
            resp.getWriter().println("access denied!");
            
            //String error:
            String noti = "You need login first!";
            
            //set a new session: noti
            req.getSession().setAttribute("notification", noti);
            
            //to notifictaion.jsp
            req.getRequestDispatcher("view/notification.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(isAuthenticated(req)){
            processGet(req, resp);
        }else{
            resp.getWriter().println("access denied!");
            
            //String error:
            String noti = "You need login first!";
            
            //set a new session: noti
            req.getSession().setAttribute("notification", noti);
            
            //to notifictaion.jsp
            req.getRequestDispatcher("view/notification.jsp").forward(req, resp);
            
        }
    }
    
    protected abstract void processPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
    protected abstract void processGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
    
}
