package com.uber.uberfamily.service.impl;

import com.uber.uberfamily.dao.BaseCardDao;
import com.uber.uberfamily.framework.BaseServiceImpl;
import com.uber.uberfamily.framework.MyBatisService;
import com.uber.uberfamily.model.BaseCard;
import com.uber.uberfamily.service.BaseCardService;

import javax.annotation.Resource;
import java.util.List;


/**
 * @Project uber
 * @Package com.uber.uberfamily.service.impl
 * @Description //TODO
 * @Date 16/1/20
 * @USER saxisuer
 * @COMPANY ENMOTECH
 */
@MyBatisService("baseCardService")
public class BaseCardServiceImpl extends BaseServiceImpl<BaseCard, Long, BaseCardDao> implements BaseCardService {


    @Override
    @Resource(name = "baseCardDao")
    public void setBaseDao(BaseCardDao baseCardDao) {
        this.baseDao = baseCardDao;
    }

    @Override
    public void saveExcelData(List<BaseCard> insertList) {
        this.insertBatch("com.uber.uberfamily.dao.BaseCardDao.batchInsert", insertList);
    }
}
