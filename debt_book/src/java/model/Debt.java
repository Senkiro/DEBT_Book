/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 * Class: Debt
 * Description: this class contain attributes: debtId, debtValue, createDate, 
 *              description, type, status, debtorId, userId
 * @author Hoàng Son
 */
public class Debt {
    private int debtId;
    private long debtValue;
    private Date createDate;
    private String description;
    private boolean type;
    private boolean status;
    private int debtorId;
    private int userId;

    public int getDebtId() {
        return debtId;
    }

    public void setDebtId(int debtId) {
        this.debtId = debtId;
    }

    public long getDebtValue() {
        return debtValue;
    }

    public void setDebtValue(long debtValue) {
        this.debtValue = debtValue;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
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

    @Override
    public String toString() {
        return "Debt{" + "debtId=" + debtId + ", debtValue=" + debtValue + ", createDate=" + createDate + ", description=" + description + ", type=" + type + ", status=" + status + ", debtorId=" + debtorId + ", userId=" + userId + '}';
    }   
}
