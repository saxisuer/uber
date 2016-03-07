package com.uber.uberfamily.model;

import com.uber.uberfamily.framework.BaseModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Project uber
 * @Package com.uber.uberfamily.model
 * @Description //广告模板
 * @Date 16/3/6
 * @USER saxisuer
 * @COMPANY ENMOTECH
 */
public class AdTemplate extends BaseModel {

    private String templateName;
    private String templateDesc;
    private Date createTime;
    private String creator;

    private Long fileCount;
    private List<Map<String, Object>> adFileList;

    private List<String> adFileString = new ArrayList<>();

    private String adFileIds;


    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getTemplateDesc() {
        return templateDesc;
    }

    public void setTemplateDesc(String templateDesc) {
        this.templateDesc = templateDesc;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public List<Map<String, Object>> getAdFileList() {
        return adFileList;
    }

    public void setAdFileList(List<Map<String, Object>> adFileList) {
        this.adFileList = adFileList;
    }

    public List<String> getAdFileString() {
        return adFileString;
    }

    public void setAdFileString(List<String> adFileString) {
        this.adFileString = adFileString;
    }

    public String getAdFileIds() {
        return adFileIds;
    }

    public void setAdFileIds(String adFileIds) {
        this.adFileIds = adFileIds;
    }

    public Long getFileCount() {
        return fileCount;
    }

    public void setFileCount(Long fileCount) {
        this.fileCount = fileCount;
    }
}
