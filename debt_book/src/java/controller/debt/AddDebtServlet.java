/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.debt;

import controller.authentication.BaseAuthenticationController;
import dal.DebtDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Debt;
import model.Debtor;
import model.User;

/**
 *
 * @author dell
 */
public class AddDebtServlet extends BaseAuthenticationController {
    
    @Override
    protected void processPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //take info 
        String description = req.getParameter("description");
        String debtValue = req.getParameter("debtValue");
        String type = req.getParameter("type");
        String status = req.getParameter("status");
        
        //take debtorId
//        int debtorId = (int) req.getSession().getAttribute("debtorId");
        int debtorId = Integer.parseInt(req.getParameter("debtorId"));
        
//        int debtorId = (int) req.getSession().getAttribute("debtorId");
        
        //take userId
        User user = (User) req.getSession().getAttribute("user");
        int userId = user.getUserId();

        //take current date
        Date date = new Date();

        //test
        System.out.println("debtValue " + debtValue);
        System.out.println("description " + description);
        System.out.println("type " + type);
        System.out.println("debtorId " + debtorId);
        System.out.println("userId " + userId);
        System.out.println("date " + date);

        //Validation
        //generate a boolean value to check validation
        boolean valid = true;

        //generate string to show for user
        String error = "";

        //valid debtValue: digit
        if (!debtValue.matches("^[0-9]{1,}$")) {
            error += " Debt is not valid!";
            valid = false;
        }

        //if valid != true
        if (!valid) {

            //set session error
            req.setAttribute("errorAddDebt", error);
            
            System.out.println("error add debt: "+error);

            //back to adddebt.jsp
            req.getRequestDispatcher("view/debt/adddebt.jsp").forward(req, resp);
        } //else: add into database
        else {

            //generate a new DebtDBContext
            DebtDBContext debtContext = new DebtDBContext();

            //if type is true -> add "-" before debtValue
            if (type.equals("true")) {
                debtValue = "-" + debtValue;
            }
            
            //if status null -> auto set status is false
            if(status == null){
                status = "false";
            }
            
            //add into database
            int check = debtContext.insertDebt(debtValue, date, description, type, status, debtorId, userId);

            //if check = 1 -> insert success
            if (check == 1) {
                System.out.println("insert sucess!");
                
                req.setAttribute("messageDebtSuccess", " Insert success! ");

                //back to debtlist
                req.getRequestDispatcher("view/debt/debtlist.jsp").forward(req, resp);
            } else if (check == 0) {
                resp.getWriter().println("insert fail!");
                
                req.setAttribute("messageDebtFail", " Insert fail! ");

                //back to insertdebt
                req.getRequestDispatcher("view/debt/adddebt.jsp").forward(req, resp);
            }
        }       
    }
    
    @Override
    protected void processGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //set session debtorId to null
        req.getSession().setAttribute("debtorId", null);

        //take debtorid
        String debtorId = req.getParameter("debtorId");
//        int debtorId = Integer.parseInt(rawDebtorId);
//        int debtorId = Integer.parseInt(req.getParameter("debtorId"));
        
        String debtId = req.getParameter("debtId");
        
        if(!(debtId == null)){
            System.out.println("update debt : "+debtId);
            
            //get debt by debtorId
            DebtDBContext debtContext = new DebtDBContext();
            
            Debt debt = debtContext.getDebtById(debtId);
            
            System.out.println("old debt: "+debt);
            
            //change infor of debtValue
            long oldDebtValue = debt.getDebtValue();
            long newDebtValue = oldDebtValue;
            if(oldDebtValue < 0){
                newDebtValue = -oldDebtValue;
            }
            debt.setDebtValue(newDebtValue);
            
            //change debt value to new
            
            //change type 
            boolean oldType = debt.isType();
            debt.setType(!oldType);
            
            //change status
            boolean oldStatus = debt.isStatus();
            boolean newStatus = oldStatus;
            if(!oldStatus){
                newStatus = !oldStatus;
            }
            debt.setStatus(newStatus);
            
            System.out.println("new debt: "+debt);
            
            //change side
            
            //set attribute
            req.setAttribute("debtUpdate", debt);
            
        }

        //set debtorId to session
        req.getSession().setAttribute("debtorId", debtorId);
        
        req.getRequestDispatcher("view/debt/adddebt.jsp").forward(req, resp);
        
    }
    
}
