package com.uber.uberfamily.service;

import com.github.pagehelper.PageInfo;
import com.uber.uberfamily.dao.CityDao;
import com.uber.uberfamily.framework.BaseService;
import com.uber.uberfamily.model.City;

import java.util.List;
import java.util.Map;

/**
 * @Project uber
 * @Package com.uber.uberfamily.service
 * @Description //TODO
 * @Date 16/3/6
 * @USER saxisuer
 * @COMPANY ENMOTECH
 */
public interface CityService extends BaseService<City, Long, CityDao> {

    List<Map<String, Object>> getCityListForCombo();


    PageInfo<City> loadCityByAdTemp(Long tempId);
}
