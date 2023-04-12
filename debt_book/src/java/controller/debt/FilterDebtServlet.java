/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.debt;

import dal.DebtDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Locale;
import model.Debt;
import model.User;

/**
 *
 * @author dell
 */
public class FilterDebtServlet extends HttpServlet {

    public String payOff(int debtorId, Debt debt) {
        String payOff = "";
        
        //change description
        String des = "Thanh toán cho bản ghi số "+debt.getDebtId();

        //change infor of debtValue
        long oldDebtValue = debt.getDebtValue();
        long newDebtValue = oldDebtValue;
        if (oldDebtValue < 0) {
            newDebtValue = -oldDebtValue;
        }
        String newStringValue = String.valueOf(newDebtValue);

        //change debt value to new
        //change type 
        boolean oldType = debt.isType();
        boolean newType = !oldType;
        
        String newStringType = String.valueOf(newType);

        //change status
        boolean oldStatus = debt.isStatus();
        boolean newStatus = oldStatus;
        if (!oldStatus) {
            newStatus = !oldStatus;
        }
        String newStringStatus = String.valueOf(newStatus);
        
        payOff += "'"+String.valueOf(debtorId)+"', "+"'"+des+"', "+"'"+newStringValue+"', "+"'"+newStringType+"', "+"'"+newStringStatus+"'";

        return payOff;
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        //take filter information
        String filterIdFrom = req.getParameter("filterIdFrom");
        String filterIdTo = req.getParameter("filterIdTo");

        String filterDescription = req.getParameter("filterDescription");

        String filterType = req.getParameter("filterType");

        String filterValueFrom = req.getParameter("filterValueFrom");
        String filterValueTo = req.getParameter("filterValueTo");

        String filterCreateDateFrom = req.getParameter("filterCreateDateFrom");
        String filterCreateDateTo = req.getParameter("filterCreateDateTo");

//        System.out.println("test take debt filter: " + filterIdFrom + ", " + filterDescription + ", " + filterType +", "+filterValueFrom+", "+filterValueTo);

        //page size, index
        String pageIndex = req.getParameter("pageIndex");
        String pageSize = req.getParameter("pageSize");

        //get debtorId
//        int debtorId = Integer.parseInt(req.getParameter("debtorId"));
        int debtorId = (int) req.getSession().getAttribute("debtorId");

        //set debtorId to session
//        req.getSession().setAttribute("debtorId", debtorId);
        //get userId
        User user = (User) req.getSession().getAttribute("user");

        int userId = user.getUserId();

        DebtDBContext debtContext = new DebtDBContext();

        ArrayList<Debt> debtList = debtContext.filterDebt(userId, debtorId, Integer.parseInt(pageIndex), Integer.parseInt(pageSize),
                filterIdFrom, filterIdTo, filterDescription, filterType, filterValueFrom, filterValueTo, filterCreateDateFrom, filterCreateDateTo);
        
        //get number of debt by userId and debtorId
        int numberDebt = debtContext.getNumberDebt(userId, debtorId);
        
        //count maxPage
        int maxPage = (numberDebt / Integer.parseInt(pageSize)) + 1;
        
        System.out.println("test maxPage: "+maxPage);
        
        //set number format for totalDebt
        NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        format.setCurrency(Currency.getInstance("VND"));

        for (Debt debt : debtList) {

            String style = debt.isType() ? "green" : "red";
            
            String typeDebt = debt.isType() ? "Khoản nợ" : "Khoản vay";
            
            String disabled = "";
            if(debt.isStatus()){
                disabled += "disabled";
            }
            String pay = payOff(debtorId, debt);
//            System.out.println("test pay-off string: "+pay);

            String formattedDebtValue = format.format(debt.getDebtValue());
            
            out.println("<tr>\n"
                    + "                                                    <td >" + debt.getDebtId() + "</td>\n"
                    + "                                                    \n"
                    + "                                                    <td >" + debt.getDescription() + "</td>\n"
                    + "                                                    <td >" + typeDebt + "</td>\n"
                    + "                                                    <td >\n"
                    + "                                                        <strong class=\"debt\">\n"
                    + "                                                            " + formattedDebtValue + "\n"
                    + "                                                        </strong>\n"
                    + "                                                    </td>\n"
                    + "                                                    <td >" + debt.getCreateDate() + "</td>\n"
                    + "                                                <td>\n"
                    + "\n"
                    + "                                                    <div style=\"display: flex\" class=\"action\">\n"
                    + "                                                         <input type=\"hidden\" value=\""+maxPage+"\" id=\"max-pageDebt-count\">\n"
                    + "                                                        \n"
                    + "                                                        <a href=\"#\" onclick=\"showInfor(\'" + debt.getDescription() + "\', \'" + debt.isType() + "\', \'" + debt.getDebtValue() + "\', \'" + debt.getCreateDate() + "\')\" class=\"btn btn-info\"  data-toggle=\"modal\"  data-target=\"#info-model\">\n"
                    + "                                                            <i class=\"material-icons d-block\" title=\"Edit\">&#xe88f;</i>\n"
                    + "                                                            Chi tiết\n"
                    + "                                                        </a> <br>\n"
                    + "                                                        \n"
                    + "                                                            <a href=\"#\" onclick=\"payOff("+pay+")\" \n"
                    + "                                                               data-toggle=\"modal\" data-target=\"#pay-off-modal\"\n"
                    + "                                                                    class=\"mr-1 btn btn-danger "+disabled+"\">\n"
                    + "                                                                    <i class=\"fa fa-minus\"> Thanh toán </i>\n"
                    + "                                                            </a>\n"
                    + "                                                    </div>\n"
                    + "                                                </td>\n"
                    + "                                            </tr>");

        }

        //
        int nullLine = Integer.parseInt(pageSize) - debtList.size();
        String css = "with: 400px; height: 44px;";
        for (int i = 0; i < nullLine; i++) {
            out.println("<tr>"
                    + " <td>&shy;</td>"
                    + " <td>&shy;</td>"
                    + " <td>&shy;</td>"
                    + " <td>&shy;</td>"
                    + " <td>&shy;</td>"
                    + " <td><div style=\"" + css + "\"></div></td>"
                    + "</tr>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
