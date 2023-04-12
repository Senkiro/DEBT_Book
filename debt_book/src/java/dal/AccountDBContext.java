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
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;

/**
 * Class: AccountDBContext Description: This class have responsibility to
 * connect to table Account in database
 *
 * @author Hoang Son
 */
public class AccountDBContext extends DBContext {

    /**
     * Method: getAccount Description: this method would get username, email
     * from table Account in database by username and password
     *
     * @param username
     * @param password
     * @return Account a account with username and password equal to parameter
     * or null if there is no account have that username and password
     */
    public Account getAccount(String username, String password) {
//        Account account = new Account();

        String query = "select * from Account\n"
                + "where username = ? and password = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Account acc = new Account();
                acc.setUsername(rs.getString("username"));
                acc.setKey(rs.getString("key"));
                acc.setStatus(rs.getBoolean("status"));
                acc.setEmail(rs.getString("email"));
                acc.setRoleID(rs.getInt("roleID"));

                return acc;
            }

        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    /**
     * Method: getAllAccount Description: this method will get all account in
     * table Accoutn of database then add into an array list
     *
     * @return listAccount an array list of Account
     */
    //get all account from database
    public ArrayList<Account> getAllAccount() {

        //generate an arrayList to store account
        ArrayList<Account> listAccount = new ArrayList<>();
        String query1 = "select * from Account";

        try {
            PreparedStatement ps = connection.prepareStatement(query1);

            ResultSet rs = ps.executeQuery();

            //Loop: read each row in database and add into arrayList
            while (rs.next()) {
                Account acc = new Account();
                acc.setUsername(rs.getString("username"));
                acc.setPassword(rs.getString("password"));
                acc.setEmail(rs.getString("email"));
                acc.setRegisterDate(rs.getDate("registerDate"));
                acc.setStatus(rs.getBoolean("status"));
                acc.setRoleID(rs.getInt("roleID"));

                //add object account to arrayList
                listAccount.add(acc);
            }

            return listAccount;

        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listAccount;
    }

    /**
     * Method: getUser Description: This method would get an account which have
     * username equal to parameter from table Account of database
     *
     * @param username
     * @param email
     * @return acc an Account if it have username equal to parameter username or
     * null other wise
     */
    //check username existed or not
    public Account getUser(String username, String email) {
        //String query
        String query = "select * from Account where username = ? or email = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, username);
            ps.setString(2, email);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Account acc = new Account();
                acc.setUsername(rs.getString("username"));
                acc.setEmail(rs.getString("email"));

                return acc;
            }

        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    /**
     * Method: addAccount Description: this method would add an Account into
     * table Account of database
     *
     * @param username
     * @param password
     * @param email
     * @param key
     * @param date
     * @return 1 if success add into database 0 if fail to add into database
     */
    public int addAccount(String username, String password, String email, String key, Date date) {
        //String query: add new object Account into database
//        String query = "INSERT INTO [Account]\n"
//                + "           ([username]\n"
//                + "           ,[password]\n"
//                + "           ,[email]\n"
//                + "           ,[registerDate]\n"
//                + "           ,[status])\n"
//                + "     VALUES\n"
//                + "           (?"
//                + "           ,?"
//                + "           ,?"
//                + "           ,?"
//                + "           ,?)"
//                + "INSERT INTO [User]\n"
//                + "           ([username])\n"
//                + "            VALUES\n"
//                + "           (?)";

        String query = "INSERT INTO [dbo].[Account]\n"
                + "           ([username]\n"
                + "           ,[password]\n"
                + "           ,[email]\n"
                + "           ,[registerDate]\n"
                + "           ,[key]\n"
                + "           ,[status]\n"
                + "           ,[roleID])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?)"
                + "INSERT INTO [User]\n"
                + "           ([username])\n"
                + "            VALUES\n"
                + "           (?)";

        try {
            PreparedStatement ps = connection.prepareStatement(query);

            //convert date to string
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String dateRegister = df.format(date);

            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, email);
            ps.setString(4, dateRegister);
            ps.setString(5, key);
            ps.setString(6, "false");
            ps.setString(7, "1");
            ps.setString(8, username);

            int rs = ps.executeUpdate();
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    /**
     * Method: deleteAccount Description: this method would delete an account in
     * database where username equal to parameter username
     *
     * @param username
     * @return 1 if success delete and 0 if fail to delete
     */
//    public int deleteAccount(String username) {
//
//        //String query
//        String query = "DELETE FROM [dbo].[Account]\n"
//                + "      WHERE username = ? "
//                + "      DELETE CASCADE";
//
//        try {
//            PreparedStatement pst = connection.prepareStatement(query);
//            pst.setString(1, username);
//
//            //checkDel = 1 if delete success
//            int checkDel = pst.executeUpdate();
//
//            return checkDel;
//        } catch (SQLException ex) {
//            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return 0;
//    }
    public Account checkAccount(String username, String password) {
        String query = "select * from Account\n"
                + "where username = ? and password = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Account acc = new Account();
                acc.setUsername(rs.getString("username"));
                acc.setPassword(rs.getString("password"));
                acc.setEmail(rs.getString("email"));
                acc.setKey(rs.getString("key"));
                acc.setStatus(rs.getBoolean("status"));
                acc.setRegisterDate(rs.getDate("registerDate"));
                acc.setRoleID(rs.getInt("roleID"));
                return acc;
            }

        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     *
     * @param acc
     */
    public int change(Account acc) {
        String sql = "UPDATE [Account]\n"
                + "   SET [password] = ?   \n"
                + " WHERE username = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, acc.getPassword());
            stm.setString(2, acc.getUsername());
            int result = stm.executeUpdate();
            
            return result;
        } catch (Exception ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public Account getAccountByEmailAndUserName(String email, String username) {
        try {
            String query = "select * from Account where email = ? and [username] = ?";
            
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, username);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                Account acc = new Account();
                acc.setUsername(rs.getString("username"));
                acc.setPassword(rs.getString("password"));
                acc.setEmail(rs.getString("email"));
                acc.setRegisterDate(rs.getDate("registerDate"));
                acc.setStatus(rs.getBoolean("status"));
                return acc;
            }
        } catch (Exception ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    //get account by key
    public Account getAccountByKey(String key) {
        try {
            String query = " select * from Account where [key] = ? ";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, key);
            
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                Account acc = new Account();
                acc.setUsername(rs.getString("username"));
                acc.setPassword(rs.getString("password"));
                acc.setEmail(rs.getString("email"));
                acc.setRegisterDate(rs.getDate("registerDate"));
                acc.setKey(rs.getString("key"));
                acc.setStatus(rs.getBoolean("status"));
                acc.setRoleID(rs.getInt("roleID"));
                return acc;
            }
        } catch (Exception ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    //update, verify account
    public int verifyAccount(String key) {
        String query = "Update [dbo].[Account] set [key] = ?\n"
                + "      ,[status] = ? \n"
                + "      WHERE [key] = ? ";

        try {
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, null);
            pst.setString(2, "true");
            pst.setString(3, key);
            //checkDel = 1 if update success
            int checkUpdate = pst.executeUpdate();

            return checkUpdate;
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public int updateAccount(String email, String password) {
        String query = "Update [dbo].[Account] set password = ?"
                + "      WHERE email = ? ";

        try {
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, password);
            pst.setString(2, email);
            //checkDel = 1 if delete success
            int checkDel = pst.executeUpdate();

            return checkDel;
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

}
