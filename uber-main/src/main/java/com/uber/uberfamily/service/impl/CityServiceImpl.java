package com.uber.uberfamily.service.impl;

import com.uber.uberfamily.dao.CityDao;
import com.uber.uberfamily.framework.BaseServiceImpl;
import com.uber.uberfamily.framework.MyBatisService;
import com.uber.uberfamily.model.City;
import com.uber.uberfamily.service.CityService;
import org.apache.log4j.Logger;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Project uber
 * @Package com.uber.uberfamily.service.impl
 * @Description //TODO
 * @Date 16/3/6
 * @USER saxisuer
 * @COMPANY ENMOTECH
 */
@MyBatisService("cityService")
public class CityServiceImpl extends BaseServiceImpl<City, Long, CityDao> implements CityService {
    private static final Logger logger = Logger.getLogger(CityServiceImpl.class);

    @Override
    @Resource(name = "cityDao")
    public void setBaseDao(CityDao baseDao) {
        this.baseDao = baseDao;
    }

    @Override
    public List<Map<String, Object>> getCityListForCombo() {
        return this.getBaseDao().getCityListForCombo();
    }
}
