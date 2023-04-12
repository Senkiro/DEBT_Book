/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Debtor;
import model.User;

/**
 * Class: DebtorDBContext Description: this class have responsibility to manage
 * CRUD of table Debtor in database
 *
 * @author Hoang Son
 */
public class DebtorDBContext extends DBContext {

    /**
     * Method: getDebtorList Description: this method would get all debtor which
     * have userId equal to parameter userId in the table debtor of database
     *
     * @param userid
     * @return DebtorList an array list of Debtor
     */
    public ArrayList<Debtor> getDebtorList(int userid) {
        //generate a new ArrayList of Debtor
        ArrayList<Debtor> DebtorList = new ArrayList<>();

        //String query
        String query = "SELECT db.debtorid, \n"
                + "		db.debtorName, \n"
                + "		db.debtorAddress, \n"
                + "		db.phoneNumber,\n"
                + "		db.debtorEmail, \n"
                + "		SUM(d.debtValue) AS totalDebt,\n"
                + "		db.createDate,\n"
                + "		db.updateDate\n"
                + "FROM Debtor db\n"
                + "left JOIN Debt d ON d.debtorid = db.debtorid\n"
                + "WHERE db.userid = ?\n"
                + "GROUP BY db.debtorid, \n"
                + "		db.debtorName, \n"
                + "		db.debtorAddress, \n"
                + "		db.phoneNumber,\n"
                + "		db.debtorEmail,\n"
                + "		db.createDate,\n"
                + "		db.updateDate";

        try {
            //prepared statement
            PreparedStatement ps = connection.prepareStatement(query);

            //set userid
            ps.setInt(1, userid);

            ResultSet rs = ps.executeQuery();

            //Loop: take each object debtor and add into arraylist
            while (rs.next()) {
                //generate new debtor object
                Debtor debtor = new Debtor();
                debtor.setDebtorId(rs.getInt("debtorid"));
                debtor.setDebtorName(rs.getString("debtorName"));
                debtor.setDebtorAddress(rs.getString("debtorAddress"));
                debtor.setPhoneNumber(rs.getString("phoneNumber"));
                debtor.setDebtorEmail(rs.getString("debtorEmail"));

                //add total debt
                debtor.setTotalDebt(rs.getInt("totalDebt"));

                debtor.setCreateDate(rs.getDate("createDate"));
                debtor.setUpdateDate(rs.getDate("updateDate"));
//                debtor.setUserId(rs.getInt("userid"));

                //add debtor into arraylist
                DebtorList.add(debtor);
            }

            //return debtor list
            return DebtorList;

        } catch (SQLException ex) {
            Logger.getLogger(DebtorDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return DebtorList;
    }

    /**
     * Method: addDebtor Description: this method would add a new debtor object
     * into table Debtor of the database with parameter: debtorName,
     * debtorAddress, phoneNumber, debtorEmail, createDate, updateDate, userId
     *
     * @param debtorName
     * @param debtorAddress
     * @param phoneNumber
     * @param debtorEmail
     * @param createDate
     * @param updateDate
     * @param userId
     * @return debtorId if add access, and 0 if fail
     */
    public int addDebtor(String debtorName, String debtorAddress, String phoneNumber,
            String debtorEmail, Date createDate, Date updateDate, int userId) {

        //generate a String query
        String query = "INSERT INTO [dbo].[Debtor]\n"
                + "           ([debtorName]\n"
                + "           ,[debtorAddress]\n"
                + "           ,[phoneNumber]\n"
                + "           ,[debtorEmail]\n"
                + "           ,[createDate]\n"
                + "           ,[updateDate]\n"
                + "           ,[userid])\n"
                + "     VALUES\n"
                + "           (?"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?)\n";
//                + "SET @newDebtorId = SCOPE_IDENTITY() \n"
//                + "SELECT @newDebtorId as debtorId ";
        int newDebtorId = -1;
        try {
            PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            //set
            ps.setString(1, debtorName);
            ps.setString(2, debtorAddress);
            ps.setString(3, phoneNumber);
            ps.setString(4, debtorEmail);

            //change Date type to String
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

            String cDate = df.format(createDate);
            String uDate = df.format(updateDate);

            ps.setString(5, cDate);
            ps.setString(6, uDate);
            ps.setInt(7, userId);

            //update
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected == 1) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    newDebtorId = rs.getInt(1);
                }
            }

            return newDebtorId;

        } catch (SQLException ex) {
            Logger.getLogger(DebtorDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return 0;
    }

    /**
     *
     * @param userid
     * @param filterIdFrom
     * @param filterIdTo
     * @param filterName
     * @param filterAddress
     * @param filterPhoneNumber
     * @param filterEmail
     * @param filterTotalDebtFrom
     * @param filterTotalDebtTo
     * @param filterCreateDateFrom
     * @param filterCreateDateTo
     * @param filterUpdateDateFrom
     * @param filterUpdateDateTo
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public ArrayList<Debtor> filterDebtorList(int userid, String filterIdFrom,
            String filterIdTo,
            String filterName,
            String filterAddress,
            String filterPhoneNumber,
            String filterEmail,
            String filterTotalDebtFrom,
            String filterTotalDebtTo,
            String filterCreateDateFrom,
            String filterCreateDateTo,
            String filterUpdateDateFrom,
            String filterUpdateDateTo,
            int pageIndex,
            String pageSize) {
        //generate a new ArrayList of Debtor
        ArrayList<Debtor> DebtorList = new ArrayList<>();

        //String query
        String query = "DECLARE @PAGEINDEX INT = ?;\n"
                + "DEClARE @PAGESIZE INT = ?;"
                + "SELECT db.debtorid, \n"
                + "		db.debtorName, \n"
                + "		db.debtorAddress, \n"
                + "		db.phoneNumber,\n"
                + "		db.debtorEmail, \n"
                + "		SUM(d.debtValue) AS totalDebt,\n"
                + "		db.createDate,\n"
                + "		db.updateDate\n"
                + "FROM Debtor db \n"
                + "LEFT JOIN Debt d ON d.debtorid = db.debtorid\n"
                + "WHERE db.userid = ? \n";

        try {

            //generate a hashmap to store index of parameter and value(query)
            HashMap<Integer, Object> params = new HashMap<>();

            //start with 3 (except pageIndex, pageSize, userid)
            int count = 3;

            //filterIdFrom
            if (filterIdFrom != null && filterIdFrom.length() > 0) {
                query += " AND db.debtorid >= ?";
                count++;
                params.put(count, filterIdFrom);
            }
            //filterIdTo
            if (filterIdTo != null && filterIdTo.length() > 0) {
                query += " AND db.debtorid <= ?";
                count++;
                params.put(count, filterIdTo);
            }

            //filfilterName
            if (filterName != null && filterName.trim().length() > 0) {
                query += " AND db.[debtorName] like '%'+?+'%'";
                count++;
                params.put(count, filterName);
            }

            //filterAddress
            if (filterAddress != null && filterAddress.length() > 0) {
                query += " AND db.[debtorAddress] like '%'+?+'%'";
                count++;
                params.put(count, filterAddress);
            }

            //filterPhoneNumber
            if (filterPhoneNumber != null && filterPhoneNumber.length() > 0) {
                query += " AND db.[phoneNumber] like '%'+?+'%'";
                count++;
                params.put(count, filterPhoneNumber);
            }

            
            //filterEmail
            if (filterEmail != null && filterEmail.length() > 0) {
                query += " AND db.[debtorEmail] like '%'+?+'%'";
                count++;
                params.put(count, filterEmail);
            }

            //filter Date
            //filterCreateDateFrom
            if (filterCreateDateFrom != null && filterCreateDateFrom.length() > 0) {
                query += " AND db.[createDate] >= ? ";
                count++;
                params.put(count, filterCreateDateFrom);
            }

            //filterCreateDateTo
            if (filterCreateDateTo != null && filterCreateDateTo.length() > 0) {
                query += " AND db.[createDate] <= ? ";
                count++;
                params.put(count, filterCreateDateTo);
            }

            //filterUpdateDateFrom
            if (filterUpdateDateFrom != null && filterUpdateDateFrom.length() > 0) {
                query += " AND db.[updateDate] >= ? ";
                count++;
                params.put(count, filterUpdateDateFrom);
            }

            //filterUpdateDateTo
            if (filterUpdateDateTo != null && filterUpdateDateTo.length() > 0) {
                query += " AND db.[updateDate] <= ? ";
                count++;
                params.put(count, filterUpdateDateTo);
            }

            //add group by
            query += " GROUP BY db.debtorid, \n"
                    + "		db.debtorName, \n"
                    + "		db.debtorAddress, \n"
                    + "		db.phoneNumber,\n"
                    + "		db.debtorEmail,\n"
                    + "		db.createDate,\n"
                    + "		db.updateDate \n";

            //totalDebt
            //filterTotalDebtFrom
            if (filterTotalDebtFrom != null && filterTotalDebtFrom.length() > 0) {
                query += " HAVING SUM(d.debtValue) >= ?\n";
                count++;
                params.put(count, filterTotalDebtFrom);
            }
            //filterTotalDebtTo
            if (filterTotalDebtTo != null && filterTotalDebtTo.length() > 0) {
                //if have filterTotalDebtFrom (not null)
                if (filterTotalDebtFrom != null && filterTotalDebtFrom.length() > 0) {
                    //just add query: AND SUM(d.debtValue) <= ?
                    query += " AND SUM(d.debtValue) <= ?\n";
                    count++;
                    params.put(count, filterTotalDebtTo);
                } //else: if filterTotalDebtFrom null
                else if (filterTotalDebtFrom == null || filterTotalDebtFrom.length() == 0) {

                    //add new having query
                    query += " HAVING SUM(d.debtValue) <= ?\n";
                    count++;
                    params.put(count, filterTotalDebtTo);
                }
            }

            //add order and pagging
            query += " ORDER BY debtorid ASC\n"
                    + "OFFSET (@PAGEINDEX - 1)*@PAGESIZE ROWS FETCH NEXT @PAGESIZE ROWS ONLY";

            //prepared statement
            PreparedStatement ps = connection.prepareStatement(query);

            //set userid
            ps.setInt(1, pageIndex);
            ps.setString(2, pageSize);
            ps.setInt(3, userid);

            //pass key, value with each state
            for (Map.Entry<Integer, Object> entry : params.entrySet()) {
                Integer key = entry.getKey();
                Object val = entry.getValue();
                ps.setObject(key, val);
            }

            ResultSet rs = ps.executeQuery();

            //Loop: take each object debtor and add into arraylist
            while (rs.next()) {
                //generate new debtor object
                Debtor debtor = new Debtor();
                debtor.setDebtorId(rs.getInt("debtorid"));
                debtor.setDebtorName(rs.getString("debtorName"));
                debtor.setDebtorAddress(rs.getString("debtorAddress"));
                debtor.setPhoneNumber(rs.getString("phoneNumber"));
                debtor.setDebtorEmail(rs.getString("debtorEmail"));

                //add total debt
                
                int totalDebt = rs.getInt("totalDebt");
                
                debtor.setTotalDebt(totalDebt);

                debtor.setCreateDate(rs.getDate("createDate"));
                debtor.setUpdateDate(rs.getDate("updateDate"));
//                debtor.setUserId(rs.getInt("userid"));

                //add debtor into arraylist
                DebtorList.add(debtor);
            }

            //return debtor list
            return DebtorList;

        } catch (SQLException ex) {
            Logger.getLogger(DebtorDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return DebtorList;
    }

    //get debtor by debtorId and userId
    public Debtor getDebtorById(String debtorId) {

        String query = "select * from Debtor where debtorId = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, Integer.parseInt(debtorId));

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Debtor debtor = new Debtor();

                debtor.setDebtorId(rs.getInt("debtorid"));
                debtor.setDebtorName(rs.getString("debtorName"));
                debtor.setDebtorAddress(rs.getString("debtorAddress"));
                debtor.setPhoneNumber(rs.getString("phoneNumber"));
                debtor.setDebtorEmail(rs.getString("debtorEmail"));

                debtor.setCreateDate(rs.getDate("createDate"));
                debtor.setUpdateDate(rs.getDate("updateDate"));

                return debtor;
            }

        } catch (SQLException ex) {
            Logger.getLogger(DebtorDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    //update debtor information
    public int updateDebtor(String newName, String newAddress, String newPhoneNumber, String newEmail, int debtorId) {

        String query = "UPDATE [Debtor]\n"
                + "   SET [debtorName] = ?\n"
                + "      ,[debtorAddress] = ?\n"
                + "      ,[phoneNumber] = ?\n"
                + "      ,[debtorEmail] = ?\n"
                + " WHERE debtorId = ?";
        
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            
            ps.setString(1, newName);
            ps.setString(2, newAddress);
            ps.setString(3, newPhoneNumber);
            ps.setString(4, newEmail);
            ps.setInt(5, debtorId);
            
            int result = ps.executeUpdate();
            
            return result;
            
        } catch (SQLException ex) {
            Logger.getLogger(DebtorDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return 0;
    }
    
    //get number of debtor
    public int getNumberDebtor(int userid){
        String query = " SELECT COUNT(*) FROM Debtor where userid = ?";
        
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, userid);
            
            ResultSet rs = ps.executeQuery();
            
            int numberDebtor = 0;
            
            if(rs.next()){
                numberDebtor = rs.getInt(1);
            }
            
            return numberDebtor;
        } catch (SQLException ex) {
            Logger.getLogger(DebtorDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return 0;
    }

//    public static void main(String[] args) {
//        DebtorDBContext debtorContext = new DebtorDBContext();
//
//        //get userid
//
//        int result = debtorContext.getNumberDebtor(1);
//
//        //test
//        System.out.println("test number debtor: "+ result );
//    }
}
