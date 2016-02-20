package com.uber.uberfamily.dao;

import com.uber.uberfamily.framework.BaseDao;
import com.uber.uberfamily.framework.MyBatisRepository;
import com.uber.uberfamily.model.BaseApplicant;

import java.util.Map;

/**
 * @Project uber
 * @Package com.uber.uberfamily.dao
 * @Description //TODO
 * @Date 16/2/17
 * @USER saxisuer
 * @COMPANY ENMOTECH
 */
@MyBatisRepository("baseApplicantDao")
public interface BaseApplicantDao extends BaseDao<BaseApplicant, Long> {

    Map sp_issueThisCard(Map<String, String> paramMap);

    void sp_writeDeliverMark(Map<String, String> paramMap);

}
