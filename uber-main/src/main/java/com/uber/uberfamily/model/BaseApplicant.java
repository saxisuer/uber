package com.uber.uberfamily.model;

import com.uber.uberfamily.framework.BaseModel;

import java.util.Date;

/**
 * @Project uber
 * @Package com.uber.uberfamily.model
 * @Description //TODO
 * @Date 16/2/17
 * @USER saxisuer
 * @COMPANY ENMOTECH
 */
public class BaseApplicant extends BaseModel {

    private String uuid;

    private String userAcc;

    private String cardNoPhysical;

    private String cardNoLogical;

    private String contactMan;

    private String telAreaCode;

    private String mobile;

    private String province;

    private String city;

    private String county;

    private String addrDetail;

    private Long requestMark;

    private Date requestDate;

    private Date requestTime;

    private Long isIssued;

    private Date issueTime;

    private String issuedByWho;

    private Long isDelivered;

    private Date deliverTime;

    private Long isLocked;

    private Date lockedTime;

    private String lockedByWho;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUserAcc() {
        return userAcc;
    }

    public void setUserAcc(String userAcc) {
        this.userAcc = userAcc;
    }

    public String getCardNoPhysical() {
        return cardNoPhysical;
    }

    public void setCardNoPhysical(String cardNoPhysical) {
        this.cardNoPhysical = cardNoPhysical;
    }

    public String getContactMan() {
        return contactMan;
    }

    public void setContactMan(String contactMan) {
        this.contactMan = contactMan;
    }

    public String getTelAreaCode() {
        return telAreaCode;
    }

    public void setTelAreaCode(String telAreaCode) {
        this.telAreaCode = telAreaCode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getAddrDetail() {
        return addrDetail;
    }

    public void setAddrDetail(String addrDetail) {
        this.addrDetail = addrDetail;
    }

    public Long getRequestMark() {
        return requestMark;
    }

    public void setRequestMark(Long requestMark) {
        this.requestMark = requestMark;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public Date getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
    }

    public Long getIsIssued() {
        return isIssued;
    }

    public void setIsIssued(Long isIssued) {
        this.isIssued = isIssued;
    }

    public Date getIssueTime() {
        return issueTime;
    }

    public void setIssueTime(Date issueTime) {
        this.issueTime = issueTime;
    }

    public String getIssuedByWho() {
        return issuedByWho;
    }

    public void setIssuedByWho(String issuedByWho) {
        this.issuedByWho = issuedByWho;
    }

    public Long getIsDelivered() {
        return isDelivered;
    }

    public void setIsDelivered(Long isDelivered) {
        this.isDelivered = isDelivered;
    }

    public Date getDeliverTime() {
        return deliverTime;
    }

    public void setDeliverTime(Date deliverTime) {
        this.deliverTime = deliverTime;
    }

    public Long getIsLocked() {
        return isLocked;
    }

    public void setIsLocked(Long isLocked) {
        this.isLocked = isLocked;
    }

    public Date getLockedTime() {
        return lockedTime;
    }

    public void setLockedTime(Date lockedTime) {
        this.lockedTime = lockedTime;
    }

    public String getLockedByWho() {
        return lockedByWho;
    }

    public void setLockedByWho(String lockedByWho) {
        this.lockedByWho = lockedByWho;
    }

    public String getCardNoLogical() {
        return cardNoLogical;
    }

    public void setCardNoLogical(String cardNoLogical) {
        this.cardNoLogical = cardNoLogical;
    }
}
