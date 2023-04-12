/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.debtor;

import dal.DebtorDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Locale;
import model.Debtor;
import model.User;

/**
 *
 * @author dell
 */
@WebServlet(name = "SearchDebtorServlet", urlPatterns = {"/search-debtor"})
public class SearchDebtorServlet extends HttpServlet {

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

        DebtorDBContext debtorContext = new DebtorDBContext();

        //get userid
        User user = (User) req.getSession().getAttribute("user");

        int userId = user.getUserId();

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
        String pageIndex = req.getParameter("pageIndex");
        String pageSize = req.getParameter("pageSize");
        

        int intPageIndex = Integer.parseInt(pageIndex);
        
        
        //set new pageIndex and pageSize
        req.getSession().setAttribute("pageIndex", intPageIndex);
        req.getSession().setAttribute("pageSize", pageSize);

        //get debtor list
        ArrayList<Debtor> filterDebtorList = debtorContext.filterDebtorList(userId, filterIdFrom, filterIdTo, filterName,
                filterAddress, filterPhoneNumber, filterEmail, filterTotalDebtFrom, filterTotalDebtTo,
                filterCreateDateFrom, filterCreateDateTo, filterUpdateDateFrom, filterUpdateDateTo,
                intPageIndex, pageSize);
        
        //get number of debtor
        int numberDebtor = debtorContext.getNumberDebtor(userId);
        
        //count max page
        int maxPage = (numberDebtor / Integer.parseInt(pageSize)) + 1;
        
        System.out.println("test page count max page: numberDebtor "+numberDebtor);
        System.out.println("test page count max page: Integer.parseInt(pageSize) "+Integer.parseInt(pageSize));
        
        System.out.println("maxPage debtor: "+maxPage);
        
        //set numberOfDebtor
//        req.getSession().setAttribute("maxPage", maxPage);
        
        //set number format for totalDebt
        NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        format.setCurrency(Currency.getInstance("VND"));
        
        //print new content
        for (Debtor debtor : filterDebtorList) {
            
            String formattedTotalDebt = format.format(debtor.getTotalDebt());
            
            out.println("<tr>\n"
                    + "                                                    <td>"+debtor.getDebtorId()+"</td>\n"
                    + "                                                    <td>"+debtor.getDebtorName()+"</td>\n"
                    + "                                                    <td>"+debtor.getDebtorAddress()+"</td>\n"
                    + "                                                    <td>"+debtor.getPhoneNumber()+"</td>\n"
                    + "                                                    <td>"+debtor.getDebtorEmail()+"</td>\n"
                    + "                                                    <td>\n"
                    + "                                                        <strong class=\"debt\">\n"
                    + "                                                            "+formattedTotalDebt+"\n"
                    + "                                                        </strong>\n"
                    + "                                                    </td>\n"
                    + "                                                    <td>"+debtor.getCreateDate()+"</td>\n"
                    + "                                                    <td>"+debtor.getUpdateDate()+"</td>\n"
                    + "\n"
                    + "                                                    <td class=\"action\">\n"
                    + "                                                        <form action=\"debt\" method=\"get\">\n"
                    + "                                                            <div class=\"actions\"> "
                    + "                                                                 <input type=\"hidden\" value=\""+maxPage+"\" id=\"max-page-count\">\n"
                    + "\n"
                    + "                                                                <a href=\"debt?debtorId="+debtor.getDebtorId()+"\" class=\"btn btn-info\">\n"
                    + "                                                                    <i class=\"material-icons\" data-toggle=\"tooltip\" title=\"Edit\">&#xe88f;</i>\n"
                    + "                                                                    Chi tiết\n"
                    + "                                                                </a>\n"
                    + "                                                                <a href=\"#\" onclick=\"addDebt("+debtor.getDebtorId()+")\" class=\"btn btn-success\" data-toggle=\"modal\" data-target=\"#insert-debt\">"
                    + "                                                                     <i class=\"material-icons\" title=\"Tạo phiếu nợ\">&#xE147;</i>Thêm khoản nợ</a>\n"
                    + "                                                                \n"
                    + "                                                                <a href=\"#update-debtor\" onclick=\"editDebtor(\'"+debtor.getDebtorId()+"\', \'"+debtor.getDebtorName()+"\', \'"+debtor.getDebtorAddress()+"\', \'"+debtor.getPhoneNumber()+"\', \'"+debtor.getDebtorEmail()+"\')\""
                    + "                                                                 class=\"btn btn-warning\" data-toggle=\"modal\" data-target=\"#update-debtor\">\n"
                    + "                                                                    <i class=\"material-icons\" data-toggle=\"tooltip\" title=\"Edit\">&#xe3c9;</i>Chỉnh sửa</a>\n"
                    + "                                                            </div>\n"
                    + "                                                        </form>\n"
                    + "                                                    </td>\n"
                    + "                                                </tr>");
        
        }
        
        int nullLine = Integer.parseInt(pageSize) - filterDebtorList.size();
        String css = "with: 400px; height: 44px;";
        for (int i = 0; i < nullLine; i++) {
            out.println("<tr>"
                    + " <td>&shy;</td>"
                    + " <td>&shy;</td>"
                    + " <td>&shy;</td>"
                    + " <td>&shy;</td>"
                    + " <td>&shy;</td>"
                    + " <td>&shy;</td>"
                    + " <td>&shy;</td>"
                    + " <td>&shy;</td>"
                    + " <td><div style=\""+css+"\"></div></td>"
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
