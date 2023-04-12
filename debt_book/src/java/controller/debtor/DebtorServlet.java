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
import java.util.ArrayList;
import model.Account;
import model.Debtor;
import model.User;

/**
 *
 * @author dell
 */
public class DebtorServlet extends BaseAuthenticationController {

    @Override
    protected void processPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //take filter infor
        String filterIdFrom = req.getParameter("filterIdFrom");
        String filterIdTo = req.getParameter("filterIdTo");

        String filterName = req.getParameter("filterName");

        String filterAddress = req.getParameter("filterAddress");

        String filterPhoneNumber = req.getParameter("filterPhoneNumber");

        String filterEmail = req.getParameter("filterEmail");

        String filterTotalDebtFrom = req.getParameter("filterTotalDebtFrom");
        String filterTotalDebtTo = req.getParameter("filterTotalDebtTo");

        String filterCreateDateFrom = req.getParameter("filterCreateDateFrom");
        String filterCreateDateTo = req.getParameter("filterCreateDateTo");

        String filterUpdateDateFrom = req.getParameter("filterUpdateDateFrom");
        String filterUpdateDateTo = req.getParameter("filterUpdateDateTo");

        //take pagging infor
//        String movePage = req.getParameter("move1");
        String pageIndex = req.getParameter("pageIndex");
        String pageSize = req.getParameter("pageSize");
        
        //set session
        req.getSession().setAttribute("filterIdFrom", filterIdFrom);
        req.getSession().setAttribute("filterIdTo", filterIdTo);
        req.getSession().setAttribute("filterName", filterName);
        req.getSession().setAttribute("filterAddress", filterAddress);
        req.getSession().setAttribute("filterPhoneNumber", filterPhoneNumber);
        req.getSession().setAttribute("filterEmail", filterEmail);
        req.getSession().setAttribute("filterTotalDebtFrom", filterTotalDebtFrom);
        req.getSession().setAttribute("filterTotalDebtTo", filterTotalDebtTo);
        req.getSession().setAttribute("filterCreateDateFrom", filterCreateDateFrom);
        req.getSession().setAttribute("filterCreateDateTo", filterCreateDateTo);
        req.getSession().setAttribute("filterUpdateDateFrom", filterUpdateDateFrom);
        req.getSession().setAttribute("filterUpdateDateTo", filterUpdateDateTo);
        
        //validation
        //generate a boolean value to check
        boolean checkValid = true;

        //generate a string to store error message
        String error = "";

        //validate filterIdFrom
        if (filterIdFrom.length() > 0) {
            int intFilterIdFrom = Integer.parseInt(filterIdFrom);
            if (intFilterIdFrom <= 0) {

                error += " ID must be greater than 0!";
                checkValid = false;
            }
        }

        //validate filterIdFrom
        if (filterIdFrom.length() > 0 && filterIdTo.length() > 0) {
            int intFilterIdFrom = Integer.parseInt(filterIdFrom);
            int intFilterIdTo = Integer.parseInt(filterIdTo);
            if (intFilterIdFrom > intFilterIdTo) {

                error += " ID to must be greater or equal to ID from!";
                checkValid = false;
            }
        } else if (filterIdTo.length() > 0) {
            //parse to int
            int intFilterIdTo = Integer.parseInt(filterIdTo);
            if (intFilterIdTo <= 0) {

                error += " ID must be greater than 0!";
                checkValid = false;
            }
        }

        //validate filterName
        if (!filterName.matches("^[a-zA-Z0-9 ]{0,50}$")) {

            error += " Debtor name is not valid!";
            checkValid = false;
        }

        
        resp.getWriter().println("error: " + error);

        //clear error message 
        req.getSession().setAttribute("errorDebtor", null);

        //if check = false -> have error
        if (!checkValid) {

            //set session error
            req.getSession().setAttribute("errorDebtor", error);

            //back to debtorlist
            resp.sendRedirect("debtor");
        } //else: do filter and show result
        else {
            //DebtorDBContext
            DebtorDBContext debtorContext = new DebtorDBContext();

            //get userid
            User user = (User) req.getSession().getAttribute("user");

            int userId = user.getUserId();

            //set back to null
            req.getSession().setAttribute("debtorList", null);

            //change pageIndex to int
            int intPageIndex = Integer.parseInt(pageIndex);

//            //if: movePage not null
//            if (!(movePage == null)) {
//                if (movePage.equals("Before") && intPageIndex > 1) {
//                    intPageIndex -= 1;
//                } else if (movePage.equals("Next")) {
//                    intPageIndex += 1;
//                }
//            }

            //set new pageIndex and pageSize
            req.getSession().setAttribute("pageIndex", intPageIndex);
            req.getSession().setAttribute("pageSize", pageSize);

            //get debtor list
            ArrayList<Debtor> filterDebtorList = debtorContext.filterDebtorList(userId, filterIdFrom, filterIdTo, filterName,
                    filterAddress, filterPhoneNumber, filterEmail, filterTotalDebtFrom, filterTotalDebtTo,
                    filterCreateDateFrom, filterCreateDateTo, filterUpdateDateFrom, filterUpdateDateTo,
                    intPageIndex, pageSize);

            //add debtorlist into session
            req.getSession().setAttribute("debtorList", filterDebtorList);

            //test
            System.out.println("test filter debtor list: "+filterDebtorList);

            //move to view/debtor/debtorlist.jsp
            req.getRequestDispatcher("view/debtor/debtorlist.jsp").forward(req, resp);

            //get debtor list
//            ArrayList<Debtor> DebtorList = debtorContext.filterDebtorList(userId, null, null
//            , null, null, null, null, null, null, null, null, null, null);
//            
//            
//            //test
//            resp.getWriter().println("test2: "+DebtorList);   
        }

    }

    @Override
    protected void processGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // get all debtor list in database

        //DebtorDBContext
        DebtorDBContext debtorContext = new DebtorDBContext();

        //get userid
        User user = (User) req.getSession().getAttribute("user");

        int userId = user.getUserId();

        //set pageIndex and pageSize
        int pageIndex = 1;
        int pageSize = 10;
        req.getSession().setAttribute("pageIndex", pageIndex);
        req.getSession().setAttribute("pageSize", pageSize);
        
        //get number of debtor
        int numberDebtor = debtorContext.getNumberDebtor(userId);
        
        //count max page
        int maxPage = (numberDebtor / pageSize) + 1;
        
        //set numberOfDebtor
        req.getSession().setAttribute("maxPage", maxPage);

        //get debtor list
//        ArrayList<Debtor> DebtorList = debtorContext.getDebtorList(userId);
        ArrayList<Debtor> DebtorList = debtorContext.filterDebtorList(userId, null, null, null,
                    null, null, null, null, null,
                    null, null, null, null,
                    pageIndex, String.valueOf(pageSize));
        req.getSession().setAttribute("debtorList", DebtorList);

        //test
//        System.out.println("test get debtor list: "+DebtorList);

        //move to view/debtor/debtorlist.jsp
        req.getRequestDispatcher("view/debtor/debtorlist.jsp").forward(req, resp);
    }

}
