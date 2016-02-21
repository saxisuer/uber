package com.uber.uberfamily.service.impl;

import com.uber.uberfamily.dao.CallCardDao;
import com.uber.uberfamily.framework.BaseServiceImpl;
import com.uber.uberfamily.framework.MyBatisService;
import com.uber.uberfamily.model.CallCard;
import com.uber.uberfamily.service.CallCardService;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Project uber
 * @Package com.uber.uberfamily.service.impl
 * @Description //TODO
 * @Date 16/2/21
 * @USER saxisuer
 * @COMPANY ENMOTECH
 */
@MyBatisService("callCardService")
public class CallCardServiceImpl extends BaseServiceImpl<CallCard, Long, CallCardDao> implements CallCardService {


    @Override
    @Resource(name = "callCardDao")
    public void setBaseDao(CallCardDao baseDao) {
        this.baseDao = baseDao;
    }

    @Override
    public Map<String, String> setCardLoss(String cardNoLogical, String operatorAcc) {
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("cardNoLogical", cardNoLogical);
        paramMap.put("operatorAcc", operatorAcc);
        return this.getBaseDao().sp_setLoss(paramMap);
    }

    @Override
    public Map<String, String> setDamaged(String cardNoLogical, String operatorAcc) {
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("cardNoLogical", cardNoLogical);
        paramMap.put("operatorAcc", operatorAcc);
        return this.getBaseDao().sp_setDamaged(paramMap);
    }

    @Override
    public Map<String, String> setReturnCard(String cardNoLogical, String operatorAcc) {
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("cardNoLogical", cardNoLogical);
        paramMap.put("operatorAcc", operatorAcc);
        return this.getBaseDao().sp_setReturnCard(paramMap);
    }

    @Override
    public Map<String, String> setExceptionLock(String cardNoLogical, String operatorAcc) {
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("cardNoLogical", cardNoLogical);
        paramMap.put("operatorAcc", operatorAcc);
        return this.getBaseDao().sp_setExceptionLock(paramMap);
    }

    @Override
    public Map<String, String> setExceptionUnLock(String cardNoLogical, String operatorAcc) {
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("cardNoLogical", cardNoLogical);
        paramMap.put("operatorAcc", operatorAcc);
        return this.getBaseDao().sp_setExceptionUnLock(paramMap);
    }
}
