package com.uber.uberfamily.service;

import com.uber.uberfamily.dao.CallCardDao;
import com.uber.uberfamily.framework.BaseService;
import com.uber.uberfamily.model.CallCard;

import java.util.Map;

/**
 * @Project uber
 * @Package com.uber.uberfamily.service
 * @Description //TODO
 * @Date 16/2/21
 * @USER saxisuer
 * @COMPANY ENMOTECH
 */
public interface CallCardService extends BaseService<CallCard, Long, CallCardDao> {

    Map<String, String> setCardLoss(String cardNoLogical, String operatorAcc);

    Map<String,String> setDamaged(String cardNoLogical, String operatorAcc);

    Map<String,String> setReturnCard(String cardNoLogical, String operatorAcc);

    Map<String,String> setExceptionLock(String cardNoLogical, String operatorAcc);

    Map<String,String> setExceptionUnLock(String cardNoLogical, String operatorAcc);

}
