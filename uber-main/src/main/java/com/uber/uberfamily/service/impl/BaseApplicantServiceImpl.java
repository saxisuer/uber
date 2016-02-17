package com.uber.uberfamily.service.impl;

import com.uber.uberfamily.dao.BaseApplicantDao;
import com.uber.uberfamily.framework.BaseServiceImpl;
import com.uber.uberfamily.framework.MyBatisService;
import com.uber.uberfamily.model.BaseApplicant;
import com.uber.uberfamily.service.BaseApplicantService;

import javax.annotation.Resource;

/**
 * @Project uber
 * @Package com.uber.uberfamily.service.impl
 * @Description //TODO
 * @Date 16/2/17
 * @USER saxisuer
 * @COMPANY ENMOTECH
 */
@MyBatisService("baseApplicantService")
public class BaseApplicantServiceImpl extends BaseServiceImpl<BaseApplicant, Long, BaseApplicantDao> implements BaseApplicantService {


    @Override
    @Resource(name = "baseApplicantDao")
    public void setBaseDao(BaseApplicantDao baseDao) {
        this.baseDao = baseDao;
    }

}
