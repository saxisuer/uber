package com.uber.uberfamily.service;

import com.github.pagehelper.PageInfo;
import com.uber.uberfamily.dao.BaseApplicantDao;
import com.uber.uberfamily.framework.BaseService;
import com.uber.uberfamily.model.BaseApplicant;

import java.util.Map;

/**
 * @Project uber
 * @Package com.uber.uberfamily.service
 * @Description //TODO
 * @Date 16/2/17
 * @USER saxisuer
 * @COMPANY ENMOTECH
 */
public interface BaseApplicantService extends BaseService<BaseApplicant, Long, BaseApplicantDao> {


    PageInfo<BaseApplicant> loadHistoryData(Map<String, Object> paramMap, int page, int rows);

    PageInfo<BaseApplicant> loadApplicantData(Map<String, Object> paramMap, int page, int rows);

    PageInfo<BaseApplicant> loadDeliveryData(Map<String, Object> paramMap, int page, int rows);

    Map issueThisCard(String id, String userinfo) throws Exception;

    void sp_writeDeliverMark(String userAcc, String operatorAcc);
}
