package com.uber.uberfamily.model;

import com.uber.uberfamily.framework.BaseModel;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

/**
 * @Project uber
 * @Package com.uber.uberfamily.model
 * @Description //TODO
 * @Date 16/1/20
 * @USER saxisuer
 * @COMPANY ENMOTECH
 */
public class BaseCard extends BaseModel {

    private String uuid;

    private String cardNoLogical;

    private String cardNoPhysical;

    private Long isLocked;

    private Date lockedTime;

    private String lockedByWho;

    private Long isIssued;

    private Date issueTime;

    private String issuedByWho;

    private Date createTime;

    private String createByWho;

    private String ids;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateByWho() {
        return createByWho;
    }

    public void setCreateByWho(String createByWho) {
        this.createByWho = createByWho;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
