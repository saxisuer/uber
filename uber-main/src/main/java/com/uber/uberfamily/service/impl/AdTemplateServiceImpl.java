package com.uber.uberfamily.service.impl;

import com.uber.uberfamily.dao.AdTemplateDao;
import com.uber.uberfamily.framework.BaseServiceImpl;
import com.uber.uberfamily.framework.MyBatisService;
import com.uber.uberfamily.framework.util.JsonMapper;
import com.uber.uberfamily.model.AdTemplate;
import com.uber.uberfamily.service.AdTemplateService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.*;

/**
 * @Project uber
 * @Package com.uber.uberfamily.service.impl
 * @Description //
 * @Date 16/3/7
 * @USER saxisuer
 * @COMPANY ENMOTECH
 */
@MyBatisService
public class AdTemplateServiceImpl extends BaseServiceImpl<AdTemplate, Long, AdTemplateDao> implements AdTemplateService {


    @Override
    @Resource(name = "adTemplateDao")
    public void setBaseDao(AdTemplateDao baseDao) {
        this.baseDao = baseDao;
    }


    @Override
    public AdTemplate getById(Long id) {
        AdTemplate adTemplate = this.baseDao.getById(id);
        for (Map<String, Object> o : adTemplate.getAdFileList()) {
            adTemplate.getAdFileString().add(JsonMapper.nonDefaultMapper().toJson(o));
        }
        adTemplate.setAllowCity(StringUtils.join(adTemplate.getAllowCityList(), ","));
        return adTemplate;
    }


    @Override
    public AdTemplate create(AdTemplate model) {
        this.baseDao.create(model);
        if (StringUtils.isNotEmpty(model.getAdFileIds())) {
            List<String> fileIds = Arrays.asList(model.getAdFileIds().split(","));
            List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
            makeInsrtList(model, fileIds, mapList);
            this.createRel(mapList);
        }
        if (StringUtils.isNotEmpty(model.getAllowCity())) {
            List<String> cityCodes = Arrays.asList(model.getAllowCity().split(","));
            List<Map<String, Object>> cityList = new ArrayList<Map<String, Object>>();
            makeCityList(model, cityCodes, cityList);
            this.createCityRel(cityList);
        }
        return model;
    }

    private void makeInsrtList(AdTemplate model, List<String> fileIds, List<Map<String, Object>> mapList) {
        for (String fileId : fileIds) {
            Map<String, Object> ins = new HashMap<String, Object>();
            ins.put("tempId", model.getId());
            ins.put("fileId", fileId);
            mapList.add(ins);
        }
    }

    @Override
    public AdTemplate update(AdTemplate model) {
        this.baseDao.update(model);
        if (StringUtils.isNotEmpty(model.getAdFileIds())) {
            this.deleteRelByTempId(model.getId());
            List<String> fileIds = Arrays.asList(model.getAdFileIds().split(","));
            List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
            makeInsrtList(model, fileIds, mapList);
            this.createRel(mapList);
        }
        if (StringUtils.isNotEmpty(model.getAllowCity())) {
            this.deleteCityRel(model.getId());
            List<String> cityCodes = Arrays.asList(model.getAllowCity().split(","));
            List<Map<String, Object>> cityList = new ArrayList<Map<String, Object>>();
            makeCityList(model, cityCodes, cityList);
            this.createCityRel(cityList);
        }
        return model;
    }

    private void makeCityList(AdTemplate model, List<String> cityCodes, List<Map<String, Object>> cityList) {
        for (String cityCode : cityCodes) {
            Map<String, Object> ins = new HashMap<String, Object>();
            ins.put("tempId", model.getId());
            ins.put("cityCode", cityCode);
            cityList.add(ins);
        }
    }

    @Override
    public void deleteRelByTempId(Long id) {
        Assert.notNull(id);
        this.baseDao.deleteRelByTempId(id);
    }

    @Override
    public void createRel(List<Map<String, Object>> insertList) {
        this.baseDao.createRel(insertList);
    }

    @Override
    public void deleteCityRel(Long id) {
        this.getBaseDao().deleteCityRel(id);
    }

    @Override
    public void createCityRel(List<Map<String, Object>> insertMap) {
        this.getBaseDao().createCityRel(insertMap);
    }

    @Override
    public void delete(Long id) {
        this.deleteRelByTempId(id);
        super.delete(id);
    }
}
