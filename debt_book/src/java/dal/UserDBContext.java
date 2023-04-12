/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;

/**
 * Class: UserDBContext Description: this class have responsibility to manage
 * CRUD of table User in database
 *
 * @author Hoang Son
 */
public class UserDBContext extends DBContext {

    /**
     * Method: getUser Description: this method would get User from database
     * which have username equal to parameter username
     *
     * @param username
     * @return user if in database have username equal to parameter username
     * null in otherwise
     */
    public User getUser(String username) {

        //String query 
        String query = "select * from [User] where username = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("userid"));
                user.setDisplayName(rs.getString("displayName"));
                user.setAddress(rs.getString("address"));
                user.setDob(rs.getDate("dob"));
                user.setGender(rs.getBoolean("gender"));
                user.setUsername(rs.getString("username"));

                //return user
                return user;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public int updateProfile(String username, String displayName, boolean gender, String dob, String address) {
        String query = "Update [dbo].[User] set displayName = ?, dob = ?, gender = ?, address = ? where username = ?";

        try {
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, displayName);
            pst.setString(2, dob);
            pst.setBoolean(3, gender);
            pst.setString(4, address);
            pst.setString(5, username);
            //checkDel = 1 if delete success
            int checkDel = pst.executeUpdate();
            
            System.out.println(pst.toString());
            return checkDel;
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return 0;
    }

}
