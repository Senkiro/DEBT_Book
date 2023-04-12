/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.debtor;

import controller.authentication.BaseAuthenticationController;
import dal.DebtorDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import model.Debtor;

/**
 *
 * @author dell
 */
public class EditDebtorServlet extends BaseAuthenticationController {

    @Override
    protected void processPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //get new infor
        String newDebtorName = req.getParameter("newDebtorName");
        String newDebtorAddress = req.getParameter("newDebtorAddress");
        String newPhoneNumber = req.getParameter("newPhoneNumber");
        String newDebtorEmail = req.getParameter("newDebtorEmail");
        
        System.out.println("new debtor name: "+newDebtorName);
        System.out.println("new debtor address: "+newDebtorAddress);
        System.out.println("new phone number: "+newPhoneNumber);
        System.out.println("new debtor email: "+newDebtorEmail);
        
        //get debtorid
        String debtorId = req.getParameter("debtorId");
        

        //get debtor object by debtorId
        DebtorDBContext debtorContext = new DebtorDBContext();
        Debtor debtor = debtorContext.getDebtorById(debtorId);
        
        //set session debtor: debtorToEdit
        req.getSession().setAttribute("debtorToEdit", debtor);
        
        //det debtorId
//        Debtor debtor = (Debtor) req.getSession().getAttribute("debtorToEdit");
        
//        DebtorDBContext debtorContext = new DebtorDBContext();
        int result = debtorContext.updateDebtor(newDebtorName, newDebtorAddress, newPhoneNumber, newDebtorEmail, debtor.getDebtorId());
        
        //set back to null
        req.getSession().setAttribute("debtorToEdit", null);
        
        //result
        if(result == 1){
            //success
            System.out.println("edit success!");
            resp.sendRedirect("debtor");
        }else if(result == 0){
            System.out.println("edit fail!");
            resp.sendRedirect("debtor");
        }
        
    }

    @Override
    protected void processGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        //set back to null
        req.getSession().setAttribute("debtorToEdit", null);
        
        //get debtorid
        String debtorId = req.getParameter("debtorId");
        
        //get debtor object by debtorId
        DebtorDBContext debtorContext = new DebtorDBContext();
        Debtor debtor = debtorContext.getDebtorById(debtorId);
        
        System.out.println("Debtor get by id: "+debtor);
        
        //set session debtor: debtorToEdit
        req.getSession().setAttribute("debtorToEdit", debtor);
        
        req.getRequestDispatcher("view/debtor/edit-debtor.jsp").forward(req, resp);
    }
    
}
