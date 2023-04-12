/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 * Class: Account
 * Description: This class contain attributes: username, password, email, status, dateRegister
 * @author Hoàng Son
 */
public class Account {
    private String username;
    private String password;
    private String email;
    private String key;
    private boolean status;
    private Date registerDate;
    private int roleID;

    public Account() {
    }

    public Account(String username, String password, String email, String key, boolean status, Date registerDate, int roleID) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.key = key;
        this.status = status;
        this.registerDate = registerDate;
        this.roleID = roleID;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    @Override
    public String toString() {
        return "Account{" + "username=" + username + ", password=" + password + ", email=" + email + ", status=" + status + ", registerDate=" + registerDate + ", roleID=" + roleID + '}';
    }
    
        
}
