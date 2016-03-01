package com.uber.uberfamily.model;

import com.uber.uberfamily.framework.BaseModel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Project uber
 * @Package com.uber.uberfamily.model
 * @Description //TODO
 * @Date 16/2/26
 * @USER saxisuer
 * @COMPANY ENMOTECH
 */
public class FileList extends BaseModel {
    private String fileUUID;

    private String company;

    private String fileName;

    private String filePostfix;

    private String fileTitle;

    private String fileLevel;

    private String md5Check;

    private String uploadByWho;

    private Date uploadTime;

    private Long isUploaded;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    private Long fileVersion;

    private Long boardcastMode;

    private int isAudited;

    private Date auditTime;

    private String auditByWho;

    private Long isDisabled;

    private String disableByWho;

    private Date disableTime;

    private Long fileStatus;

    private String fileStatusDesc;

    private String note;

    private String uniqueFileName;

    private Long fileSize;

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileUUID() {
        return fileUUID;
    }

    public void setFileUUID(String fileUUID) {
        this.fileUUID = fileUUID;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePostfix() {
        return filePostfix;
    }

    public void setFilePostfix(String filePostfix) {
        this.filePostfix = filePostfix;
    }

    public String getFileTitle() {
        return fileTitle;
    }

    public void setFileTitle(String fileTitle) {
        this.fileTitle = fileTitle;
    }

    public String getMd5Check() {
        return md5Check;
    }

    public void setMd5Check(String md5Check) {
        this.md5Check = md5Check;
    }

    public String getUploadByWho() {
        return uploadByWho;
    }

    public void setUploadByWho(String uploadByWho) {
        this.uploadByWho = uploadByWho;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public Long getIsUploaded() {
        return isUploaded;
    }

    public void setIsUploaded(Long isUploaded) {
        this.isUploaded = isUploaded;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Long getFileVersion() {
        return fileVersion;
    }

    public void setFileVersion(Long fileVersion) {
        this.fileVersion = fileVersion;
    }

    public Long getBoardcastMode() {
        return boardcastMode;
    }

    public void setBoardcastMode(Long boardcastMode) {
        this.boardcastMode = boardcastMode;
    }

    public int getIsAudited() {
        return isAudited;
    }

    public void setIsAudited(int isAudited) {
        this.isAudited = isAudited;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public String getAuditByWho() {
        return auditByWho;
    }

    public void setAuditByWho(String auditByWho) {
        this.auditByWho = auditByWho;
    }

    public Long getIsDisabled() {
        return isDisabled;
    }

    public void setIsDisabled(Long isDisabled) {
        this.isDisabled = isDisabled;
    }

    public String getDisableByWho() {
        return disableByWho;
    }

    public void setDisableByWho(String disableByWho) {
        this.disableByWho = disableByWho;
    }

    public Date getDisableTime() {
        return disableTime;
    }

    public void setDisableTime(Date disableTime) {
        this.disableTime = disableTime;
    }

    public Long getFileStatus() {
        return fileStatus;
    }

    public void setFileStatus(Long fileStatus) {
        this.fileStatus = fileStatus;
    }

    public String getFileStatusDesc() {
        return fileStatusDesc;
    }

    public void setFileStatusDesc(String fileStatusDesc) {
        this.fileStatusDesc = fileStatusDesc;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getUniqueFileName() {
        return uniqueFileName;
    }

    public void setUniqueFileName(String uniqueFileName) {
        this.uniqueFileName = uniqueFileName;
    }

    public String getFileLevel() {
        return fileLevel;
    }

    public void setFileLevel(String fileLevel) {
        this.fileLevel = fileLevel;
    }
}
