package com.uber.uberfamily.service.impl;

import com.uber.uberfamily.dao.AdTemplateDao;
import com.uber.uberfamily.framework.BaseServiceImpl;
import com.uber.uberfamily.framework.MyBatisService;
import com.uber.uberfamily.model.AdTemplate;
import com.uber.uberfamily.service.AdTemplateService;

import javax.annotation.Resource;

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
}
