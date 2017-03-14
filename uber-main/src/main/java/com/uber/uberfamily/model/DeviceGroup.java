package com.uber.uberfamily.model;

import com.uber.uberfamily.framework.BaseModel;

/**
 * @Project uber
 * @Package com.uber.uberfamily.model
 * @Description //设备组
 * @Date 16/3/6
 * @USER saxisuer
 * @COMPANY ENMOTECH
 */
public class DeviceGroup extends BaseModel {


    /**
     * 设备组名
     */
    private String groupName;
    /**
     * 设备组描述
     */
    private String groupDesc;

    /**
     * 城市
     */
    private Long cityCode;

    private String cityNameCn;


    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupDesc() {
        return groupDesc;
    }

    public void setGroupDesc(String groupDesc) {
        this.groupDesc = groupDesc;
    }

    public Long getCityCode() {
        return cityCode;
    }

    public void setCityCode(Long cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityNameCn() {
        return cityNameCn;
    }

    public void setCityNameCn(String cityNameCn) {
        this.cityNameCn = cityNameCn;
    }
}
