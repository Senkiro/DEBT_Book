/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 * Class: Debtor
 * Description: this class contain attributes: debtorId, debtorName, debtorAddress,
 *              debtorEmail, phoneNumber, createDate, updateDate,
 *              userId
 * @author Hoàng Son
 */
public class Debtor {
    private int debtorId;
    private String debtorName;
    private String debtorAddress;
    private String debtorEmail;
    private int totalDebt;
    private Date createDate;
    private Date updateDate;
    private String phoneNumber;
    private int userId;

    public long getTotalDebt() {
        return totalDebt;
    }

    public void setTotalDebt(int totalDebt) {
        this.totalDebt = totalDebt;
    }
    
    

    public int getDebtorId() {
        return debtorId;
    }

    public void setDebtorId(int debtorId) {
        this.debtorId = debtorId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDebtorName() {
        return debtorName;
    }

    public void setDebtorName(String debtorName) {
        this.debtorName = debtorName;
    }

    public String getDebtorAddress() {
        return debtorAddress;
    }

    public void setDebtorAddress(String debtorAddress) {
        this.debtorAddress = debtorAddress;
    }

    public String getDebtorEmail() {
        return debtorEmail;
    }

    public void setDebtorEmail(String debtorEmail) {
        this.debtorEmail = debtorEmail;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber; 
    }

    @Override
    public String toString() {
        return "Debtor{" + "debtorId=" + debtorId + ", debtorName=" + debtorName + ", debtorAddress=" + debtorAddress + ", debtorEmail=" + debtorEmail + ", createDate=" + createDate + ", updateDate=" + updateDate + ", phoneNumber=" + phoneNumber + ", userId=" + userId + '}';
    }
    
}
