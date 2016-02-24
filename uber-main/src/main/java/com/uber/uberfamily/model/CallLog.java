package com.uber.uberfamily.model;

import com.uber.uberfamily.framework.BaseModel;

import java.util.Date;

/**
 * @Project uber
 * @Package com.uber.uberfamily.model
 * @Description //TODO
 * @Date 16/2/24
 * @USER saxisuer
 * @COMPANY ENMOTECH
 */
public class CallLog extends BaseModel {
    private String userAcc;
    private String cardNoPhisycal;
    private String accessToken;
    private String productID;
    private String requestID;
    private String state;
    private String errorInfo;
    private Date callTime;
    private Date pickupTime;

    public String getUserAcc() {
        return userAcc;
    }

    public void setUserAcc(String userAcc) {
        this.userAcc = userAcc;
    }

    public String getCardNoPhisycal() {
        return cardNoPhisycal;
    }

    public void setCardNoPhisycal(String cardNoPhisycal) {
        this.cardNoPhisycal = cardNoPhisycal;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getRequestID() {
        return requestID;
    }

    public void setRequestID(String requestID) {
        this.requestID = requestID;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }

    public Date getCallTime() {
        return callTime;
    }

    public void setCallTime(Date callTime) {
        this.callTime = callTime;
    }

    public Date getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(Date pickupTime) {
        this.pickupTime = pickupTime;
    }
}
