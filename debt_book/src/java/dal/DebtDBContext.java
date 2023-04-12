/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Debt;

/**
 * Class: DebtDBContext Description: this class have responsibility to manage
 * CRUD of table Debt in database
 *
 * @author Hoang Son
 */
public class DebtDBContext extends DBContext {

    /**
     * method: getAllDebt Description: this method would get all Debt in
     * database which have debtorId equal with parameter debtorid
     *
     * @param debtorid
     * @return an array list of Debt
     */
    public ArrayList<Debt> getAllDebt(int debtorid) {
        //generate arraylist of debt
        ArrayList<Debt> debtList = new ArrayList<>();

        //String query
        String query = "select * from Debt\n"
                + "where debtorid = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(query);

            //convert debtorid to String
            String debtorID = String.valueOf(debtorid);
            ps.setString(1, debtorID);

            ResultSet rs = ps.executeQuery();

            //Loop: add each debt into array list
            while (rs.next()) {

                //generate a debt object
                Debt debt = new Debt();
                debt.setDebtId(rs.getInt("debtid"));
                debt.setDebtValue(rs.getInt("debtValue"));
                debt.setCreateDate(rs.getDate("createDate"));
                debt.setDescription(rs.getString("description"));
                debt.setType(rs.getBoolean("type"));
                debt.setStatus(rs.getBoolean("status"));
                debt.setDebtorId(rs.getInt("debtorid"));
                debt.setUserId(rs.getInt("userid"));

                //add debt into array list
                debtList.add(debt);
            }

            //return debt list
            return debtList;

        } catch (SQLException ex) {
            Logger.getLogger(DebtDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return debtList;
    }

    /**
     * Method: insertDebt Description: this method would insert new Debt into
     * table Debt
     *
     * @param debtValue
     * @param date
     * @param description
     * @param type
     * @param status
     * @param debtorId
     * @param userId
     * @return 1 if insert success or 0 if fail
     */
    public int insertDebt(String debtValue, Date date, String description,
            String type, String status, int debtorId, int userId) {

        //String query
        String query = "INSERT INTO [Debt]\n"
                + "           ([debtValue]\n"
                + "           ,[createDate]\n"
                + "           ,[description]\n"
                + "           ,[type]\n"
                + "           ,[status]\n"
                + "           ,[debtorid]\n"
                + "           ,[userid])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?)";

        try {
            PreparedStatement ps = connection.prepareStatement(query);

            //change date to String
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String D = df.format(date);

            //set
            ps.setString(1, debtValue);
            ps.setString(2, D);
            ps.setString(3, description);
            ps.setString(4, type);

            //set default status is false
            ps.setString(5, status);

            ps.setString(6, String.valueOf(debtorId));
            ps.setString(7, String.valueOf(userId));

            int check = ps.executeUpdate();

            return check;

        } catch (SQLException ex) {
            Logger.getLogger(DebtDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return 0;
    }

    //filter debt
    public ArrayList<Debt> filterDebt(int userId, int debtorId, int pageIndex, int pageSize,
                                        String filterIdFrom, String filterIdTo, String filterDescription,
                                        String filterType, String filterValueFrom, String filterValueTo,
                                        String filterCreateDateFrom, String filterCreateDateTo) {
        //generate a array list of debt
        ArrayList<Debt> debtList = new ArrayList<>();
        
        //String SQL to query
        String query = "DECLARE @PAGEINDEX INT = ?;\n"
                + "DEClARE @PAGESIZE INT = ?;\n"
                + "SELECT * FROM [Debt] \n"
                + "where userid = ? and debtorid = ? \n";
                
        
        try {
            
            
            //set other parametter
            
            //generate a hashmap to store index of parameter and value(query)
            HashMap<Integer, Object> params = new HashMap<>();

            //start with 3 (except pageIndex, pageSize, userid, debtorId)
            int count = 4;

            //filterIdFrom
            if (filterIdFrom != null && filterIdFrom.length() > 0) {
                query += " AND debtid >= ? ";
                count++;
                params.put(count, filterIdFrom);
            }
            //filterIdTo
            if (filterIdTo != null && filterIdTo.length() > 0) {
                query += " AND debtid <= ? ";
                count++;
                params.put(count, filterIdTo);
            }

            //filterDesciption
            if (filterDescription != null && filterDescription.trim().length() > 0) {
                query += " AND [description] like '%'+?+'%' ";
                count++;
                params.put(count, filterDescription);
            }
            
            //and [type] = 'false'
            if (filterType != null && filterType.trim().length() > 0) {
                query += " AND [type] = ''+?+'' ";
                count++;
                params.put(count, filterType);
            }
            
            //and [debtValue] >= ?
            if (filterValueFrom != null && filterValueFrom.trim().length() > 0) {
                query += " AND [debtValue] >= ? ";
                count++;
                params.put(count, filterValueFrom);
            }
            
            //and [debtValue] <= ?
            if (filterValueTo != null && filterValueTo.trim().length() > 0) {
                query += " AND [debtValue] <= ? ";
                count++;
                params.put(count, filterValueTo);
            }
            
            //filter Date
            //filterCreateDateFrom
            if (filterCreateDateFrom != null && filterCreateDateFrom.length() > 0) {
                query += " AND [createDate] >= ? ";
                count++;
                params.put(count, filterCreateDateFrom);
            }

            //filterCreateDateTo
            if (filterCreateDateTo != null && filterCreateDateTo.length() > 0) {
                query += " AND [createDate] <= ? ";
                count++;
                params.put(count, filterCreateDateTo);
            }
            
            query+= "ORDER BY debtid ASC\n"
                + "OFFSET (@PAGEINDEX - 1)*@PAGESIZE ROWS FETCH NEXT @PAGESIZE ROWS ONLY";
            
            PreparedStatement ps = connection.prepareStatement(query);
            // set pageIndex, pageSize, userId and debtorId
            ps.setInt(1, pageIndex);
            ps.setInt(2, pageSize);
            ps.setInt(3, userId);
            ps.setInt(4, debtorId);
            
            //pass key, value with each state
            for (Map.Entry<Integer, Object> entry : params.entrySet()) {
                Integer key = entry.getKey();
                Object val = entry.getValue();
                ps.setObject(key, val);
            }
            
            //result set
            ResultSet rs = ps.executeQuery();
            
            //
            while(rs.next()){
                Debt debt = new Debt();
                debt.setDebtId(rs.getInt("debtid"));
                
                debt.setDebtValue(rs.getInt("debtValue"));
                
                debt.setCreateDate(rs.getDate("createDate"));
                debt.setDescription(rs.getString("description"));
                debt.setType(rs.getBoolean("type"));
                debt.setStatus(rs.getBoolean("status"));
                debt.setDebtorId(rs.getInt("debtorid"));
                debt.setUserId(rs.getInt("userid"));
                
                debtList.add(debt);
            }
            return debtList;
        } catch (SQLException ex) {
            Logger.getLogger(DebtDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        

        return debtList;
    }
    
    //get debt by debtorId
    public Debt getDebtById(String debtId){
        String query = " select * from Debt where debtid = ?";
        
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, debtId);
            
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                Debt debt = new Debt();
                debt.setDebtId(rs.getInt("debtid"));
                debt.setDebtValue(rs.getInt("debtValue"));
                debt.setCreateDate(rs.getDate("createDate"));
                debt.setDescription(rs.getString("description"));
                debt.setType(rs.getBoolean("type"));
                debt.setStatus(rs.getBoolean("status"));
                debt.setDebtorId(rs.getInt("debtorid"));
                debt.setUserId(rs.getInt("userid"));
                
                return debt;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DebtDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    //get number of debt by debtorId and userId
    public int getNumberDebt(int userid, int debtorid){
        String query = " SELECT COUNT(*) FROM [Debt] where userid = ? and debtorid = ?";
        
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, userid);
            ps.setInt(2, debtorid);
            
            ResultSet rs = ps.executeQuery();
            
            int numberDebt = 0;
            
            if(rs.next()){
                numberDebt = rs.getInt(1);
            }
            
            return numberDebt;
        } catch (SQLException ex) {
            Logger.getLogger(DebtorDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return 0;
    }

}
