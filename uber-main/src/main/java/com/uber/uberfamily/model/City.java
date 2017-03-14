package com.uber.uberfamily.model;

import com.uber.uberfamily.framework.BaseModel;

/**
 * @Project uber
 * @Package com.uber.uberfamily.model
 * @Description //城市信息列表
 * @Date 16/3/6
 * @USER saxisuer
 * @COMPANY ENMOTECH
 */
public class City extends BaseModel {


    private String cityName;

    private Long cityCode;

    private String cityNameCn;


    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
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
