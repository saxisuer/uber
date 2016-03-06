package com.uber.uberfamily.model;

import com.uber.uberfamily.framework.BaseModel;

import java.util.Date;
import java.util.List;

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

    private List<Long> adFileIds;


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


    public List<Long> getAdFileIds() {
        return adFileIds;
    }

    public void setAdFileIds(List<Long> adFileIds) {
        this.adFileIds = adFileIds;
    }
}
