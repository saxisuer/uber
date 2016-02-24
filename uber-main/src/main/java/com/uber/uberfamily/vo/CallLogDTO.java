package com.uber.uberfamily.vo;

/**
 * @Project uber
 * @Package com.uber.uberfamily.vo
 * @Description //TODO
 * @Date 16/2/24
 * @USER saxisuer
 * @COMPANY ENMOTECH
 */
public class CallLogDTO {
    private Long id;
    private String userAcc;
    private String cardNoPhysical;
    private String accessToken;
    private String productID;
    private String requestID;
    private String state;
    private String errorInfo;

    private String callTime;

    private String pickupTime;
    private String address;
    private String deviceUUID;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserAcc() {
        return userAcc;
    }

    public void setUserAcc(String userAcc) {
        this.userAcc = userAcc;
    }


    public String getAccessToken() {
        return accessToken;
    }

    public String getCardNoPhysical() {
        return cardNoPhysical;
    }

    public void setCardNoPhysical(String cardNoPhysical) {
        this.cardNoPhysical = cardNoPhysical;
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

    public String getCallTime() {
        return callTime;
    }

    public void setCallTime(String callTime) {
        this.callTime = callTime;
    }

    public String getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(String pickupTime) {
        this.pickupTime = pickupTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDeviceUUID() {
        return deviceUUID;
    }

    public void setDeviceUUID(String deviceUUID) {
        this.deviceUUID = deviceUUID;
    }
}
