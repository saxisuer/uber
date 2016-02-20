package com.uber.uberfamily.service.impl;

import com.github.pagehelper.PageInfo;
import com.uber.uberfamily.dao.BaseApplicantDao;
import com.uber.uberfamily.framework.BaseServiceImpl;
import com.uber.uberfamily.framework.MyBatisService;
import com.uber.uberfamily.model.BaseApplicant;
import com.uber.uberfamily.service.BaseApplicantService;
import org.apache.commons.collections.MapUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

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

    @Override
    public PageInfo<BaseApplicant> loadHistoryData(Map<String, Object> paramMap, int page, int rows) {
        if (MapUtils.isEmpty(paramMap)) {
            paramMap = new HashMap<String, Object>();
        }
        paramMap.put("isDelivered", 1);
        return this.getPage(paramMap, page, rows);
    }

    @Override
    public PageInfo<BaseApplicant> loadApplicantData(Map<String, Object> paramMap, int page, int rows) {
        if (MapUtils.isEmpty(paramMap)) {
            paramMap = new HashMap<String, Object>();
        }
        paramMap.put("isIssued", 0);
        return this.getPage(paramMap, page, rows);
    }

    @Override
    public PageInfo<BaseApplicant> loadDeliveryData(Map<String, Object> paramMap, int page, int rows) {
        if (MapUtils.isEmpty(paramMap)) {
            paramMap = new HashMap<String, Object>();
        }
        paramMap.put("isIssued", 1);
        paramMap.put("isDelivered", 0);
        return this.getPage(paramMap, page, rows);
    }

    @Override
    public Map issueThisCard(String id, String userinfo) throws Exception {
        Map<String, String> para = new HashMap<String, String>();
        para.put("userAcc", "id");
        para.put("operatorAcc", "operuserinfoatorAcc");
        Map l = this.getBaseDao().sp_issueThisCard(para);
        if (l.get("code").toString().equals("-1")) {
            throw new Exception(l.get("info").toString());
        }
        return l;
    }


}
