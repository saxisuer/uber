package com.uber.uberfamily.dao;

import com.uber.uberfamily.framework.BaseDao;
import com.uber.uberfamily.framework.MyBatisRepository;
import com.uber.uberfamily.model.CallCard;

import java.util.Map;

/**
 * @Project uber
 * @Package com.uber.uberfamily.dao
 * @Description //TODO
 * @Date 16/2/21
 * @USER saxisuer
 * @COMPANY ENMOTECH
 */
@MyBatisRepository("callCardDao")
public interface CallCardDao extends BaseDao<CallCard, Long> {
    Map<String, String> sp_setLoss(Map<String, String> paramMap);

    Map<String,String> sp_setDamaged(Map<String, String> paramMap);

    Map<String,String> sp_setReturnCard(Map<String, String> paramMap);

    Map<String,String> sp_setExceptionLock(Map<String, String> paramMap);

    Map<String,String> sp_setExceptionUnLock(Map<String, String> paramMap);

}

