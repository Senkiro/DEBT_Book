/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.debtor;

import controller.authentication.BaseAuthenticationController;
import dal.DebtDBContext;
import dal.DebtorDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import model.User;

/**
 *
 * @author dell
 */
public class AddDebtorServlet extends BaseAuthenticationController {

    @Override
    protected void processPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //get raw info from adddebtor.jsp
        String debtorName = req.getParameter("debtorName");
        String debtorAddress = req.getParameter("debtorAddress");
        String phoneNumber = req.getParameter("phoneNumber");
        String debtorEmail = req.getParameter("debtorEmail");

        //debt infor: description, type, debtValue
        String debtDescription = req.getParameter("description");
        String debtType = req.getParameter("type");
        String debtValue = req.getParameter("debtValue");

        //get current date
        Date date = new Date();

        //get userId
        User user = (User) req.getSession().getAttribute("user");

        //test:
        resp.getWriter().println("name: " + debtorName);
        resp.getWriter().println("addres: " + debtorAddress);
        resp.getWriter().println("phoneNumber: " + phoneNumber);
        resp.getWriter().println("Email: " + debtorEmail);
        resp.getWriter().println("Date: " + date);
        resp.getWriter().println("userid: " + user.getUserId());
        resp.getWriter().println("---------------------");
        resp.getWriter().println("Description: " + debtDescription);
        resp.getWriter().println("debtType: " + debtType);
        resp.getWriter().println("debtValue: " + debtValue);

        //Validation
        //generate String to store error alert
        String error = "";

        //generate a boolean variable to check validate
        boolean valid = true;

        //valid debor name: from 3-50 character, digit or char
        if (!debtorName.matches("^[a-zA-Z0-9 ]{3,50}$")) {
            error += " Debtor name is not valid!";
            valid = false;
        }

        //valid debor address: from 3-50 character, digit or char
        if (!debtorAddress.matches("^[a-zA-Z0-9 ]{3,100}$")) {
            error += " Address is not valid!";
            valid = false;
        }

        //valid phone number: 10 - 11 char, digit, first char must be 0
        if (!phoneNumber.matches("^[0]{1}[0-9]{9,10}$")) {
            error += " Phone number is not valid!";
            valid = false;
        }

        //validate email: "^\S+@\S+\.\S+$"
        if (!debtorEmail.matches("^\\S+@\\S+\\.\\S+$")) {
            valid = false;
            error += " Email is not valid!";
        }

        //if not valid -> back to adddebtor and show error alert
        if (!valid) {
            //set error session
            req.getSession().setAttribute("errorAddDebtor", error);

            resp.getWriter().println("error: " + error);

            //back to adddebtor and show error
//            resp.sendRedirect("adddebtor");
        } //else: add debtor
        else {
            resp.getWriter().println("now add debtor");

            //generate object
            DebtorDBContext debtorContext = new DebtorDBContext();

            //get debtorId of debtor would be add
            int debtorId = debtorContext.addDebtor(debtorName, debtorAddress, phoneNumber, debtorEmail, date, date, user.getUserId());
            String messageDebtor = "";
            
            
            resp.getWriter().println(" debtorId: " + debtorId);

            //if debtorId != 0 -> add success
            if (debtorId != 0) {

                resp.getWriter().println(" debtorId: " + debtorId);

                resp.getWriter().println(" Add debtor success! ");
                messageDebtor += " Add new debtor success!";
                
                //userid
                //user.getUserId();

                //call add debt function
                //generate a new DebtDBContext
//                DebtDBContext debtContext = new DebtDBContext();
//
//                //add into database
//                int check = debtContext.insertDebt(debtValue, date, debtDescription, debtType, debtorId, user.getUserId());
//
//                if (check == 1) {
//                    resp.getWriter().println("insert debt success!");
//                    messageDebtor += " Add new debt success!";
//                    
//                    //back to debtlist
//                    resp.sendRedirect("debtor");
//                } else if (check == 0) {
//                    resp.getWriter().println("insert debt fail!");
//
//                    req.getSession().setAttribute("messageDebtor", " Add new debtor fail!");
//
//                    //back to insertdebt
//                    resp.sendRedirect("debtor");
//                }

                //back to debtor list
                resp.sendRedirect("debtor");
            } else if (debtorId == 0) {
                resp.getWriter().println(" Add debtor fail! ");

                error += " Fail to add new debtor!";

                //set error session
                req.getSession().setAttribute("errorAddDebtor", error);

                resp.getWriter().println("error: " + error);

                //back to adddebtor and show error
//                resp.sendRedirect("adddebtor");
            }

        }

    }

    @Override
    protected void processGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("view/debtor/adddebtor.jsp").forward(req, resp);
    }

}
