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
import java.util.ArrayList;
import model.Debt;
import model.User;

/**
 *
 * @author dell
 */
public class DebtServlet extends BaseAuthenticationController {

    @Override
    protected void processPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        //take filter information
        String filterIdFrom = req.getParameter("filterIdFrom");
        String filterIdTo = req.getParameter("filterIdTo");
        String filterDescription = req.getParameter("filterDescription");
        String filterType = req.getParameter("filterType");
        String filterValueFrom = req.getParameter("filterValueFrom");
        String filterValueTo = req.getParameter("filterValueTo");
        String filterCreateDateFrom = req.getParameter("filterCreateDateFrom");
        String filterCreateDateTo = req.getParameter("filterCreateDateTo");
        
        //page size, index
        String pageIndex = req.getParameter("pageIndex");
        String pageSize = req.getParameter("pageSize");
        
        //get debtorId
        int debtorId = Integer.parseInt(req.getParameter("debtorId"));
        
        //set debtorId to session
        req.getSession().setAttribute("debtorId", debtorId);
        
        //get userId
        User user = (User) req.getSession().getAttribute("user");

        int userId = user.getUserId();
        
        DebtDBContext debtContext = new DebtDBContext();
        
        
        
    }

    @Override
    protected void processGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        //set session debtorId to null
//        req.getSession().setAttribute("debtorId", null);
        
//        req.getParameter("debtorId");
        
        //set pageIndex and pageSize
        int pageIndex = 1;
        int pageSize = 10;
        req.getSession().setAttribute("pageIndex", pageIndex);
        req.getSession().setAttribute("pageSize", pageSize);
        
        //get number of debtor
        int numberDebtor = 10;
        
        //count max page
        int maxPage = (numberDebtor / pageSize) + 1;
        
        //set numberOfDebtor
        req.getSession().setAttribute("maxPage", maxPage);
        
        //take debtorid
        int debtorId = Integer.parseInt(req.getParameter("debtorId"));
        
        System.out.println("debt take debtorId: "+debtorId);
        
        //set debtorId to session
        req.getSession().setAttribute("debtorId", debtorId);
        
        //get all debt by debtorid
        DebtDBContext debtContext = new DebtDBContext();
        ArrayList<Debt> debtList = debtContext.getAllDebt(debtorId);
        
        //set session debtList
        req.getSession().setAttribute("debtList", debtList);
        
        //move to debtlist.jsp
        req.getRequestDispatcher("view/debt/debtlist.jsp").forward(req, resp);
    }
    
}
