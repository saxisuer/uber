package com.uber.uberfamily.model;

import com.uber.uberfamily.framework.BaseModel;

import java.util.Date;

/**
 * @Project uber
 * @Package com.uber.uberfamily.model
 * @Description //CALL CARD
 * @Date 16/2/21
 * @USER saxisuer
 * @COMPANY ENMOTECH
 */
public class CallCard extends BaseModel {

    private String uuid;

    private String userAcc;

    private Long provinceID;

    private Long cityID;

    private String cardNoLogical;

    private String cardNoPhysical;

    private Long refreshCount;

    private String callCarCount;

    private Date firstCallCarTime;

    private Date lastCallCarTime;

    private String accessTokenNow;

    private String refreshTokenNow;

    private Long activateMark;

    private Date activateTime;

    private Long cardStatus;

    private String cardStatusDesc;

    private Date createTime;

    private Long isDisabled;

    private Date disabledTime;

    private Long isDeleted;

    private Date deletedTime;

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

    public Long getProvinceID() {
        return provinceID;
    }

    public void setProvinceID(Long provinceID) {
        this.provinceID = provinceID;
    }

    public Long getCityID() {
        return cityID;
    }

    public void setCityID(Long cityID) {
        this.cityID = cityID;
    }

    public String getCardNoLogical() {
        return cardNoLogical;
    }

    public void setCardNoLogical(String cardNoLogical) {
        this.cardNoLogical = cardNoLogical;
    }

    public String getCardNoPhysical() {
        return cardNoPhysical;
    }

    public void setCardNoPhysical(String cardNoPhysical) {
        this.cardNoPhysical = cardNoPhysical;
    }

    public Long getRefreshCount() {
        return refreshCount;
    }

    public void setRefreshCount(Long refreshCount) {
        this.refreshCount = refreshCount;
    }

    public String getCallCarCount() {
        return callCarCount;
    }

    public void setCallCarCount(String callCarCount) {
        this.callCarCount = callCarCount;
    }

    public Date getFirstCallCarTime() {
        return firstCallCarTime;
    }

    public void setFirstCallCarTime(Date firstCallCarTime) {
        this.firstCallCarTime = firstCallCarTime;
    }

    public Date getLastCallCarTime() {
        return lastCallCarTime;
    }

    public void setLastCallCarTime(Date lastCallCarTime) {
        this.lastCallCarTime = lastCallCarTime;
    }

    public String getAccessTokenNow() {
        return accessTokenNow;
    }

    public void setAccessTokenNow(String accessTokenNow) {
        this.accessTokenNow = accessTokenNow;
    }

    public String getRefreshTokenNow() {
        return refreshTokenNow;
    }

    public void setRefreshTokenNow(String refreshTokenNow) {
        this.refreshTokenNow = refreshTokenNow;
    }

    public Long getActivateMark() {
        return activateMark;
    }

    public void setActivateMark(Long activateMark) {
        this.activateMark = activateMark;
    }

    public Date getActivateTime() {
        return activateTime;
    }

    public void setActivateTime(Date activateTime) {
        this.activateTime = activateTime;
    }

    public Long getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(Long cardStatus) {
        this.cardStatus = cardStatus;
    }

    public String getCardStatusDesc() {
        return cardStatusDesc;
    }

    public void setCardStatusDesc(String cardStatusDesc) {
        this.cardStatusDesc = cardStatusDesc;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getIsDisabled() {
        return isDisabled;
    }

    public void setIsDisabled(Long isDisabled) {
        this.isDisabled = isDisabled;
    }

    public Date getDisabledTime() {
        return disabledTime;
    }

    public void setDisabledTime(Date disabledTime) {
        this.disabledTime = disabledTime;
    }

    public Long getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Long isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Date getDeletedTime() {
        return deletedTime;
    }

    public void setDeletedTime(Date deletedTime) {
        this.deletedTime = deletedTime;
    }
}
