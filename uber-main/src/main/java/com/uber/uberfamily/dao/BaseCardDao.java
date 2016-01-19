package com.uber.uberfamily.dao;

import com.uber.uberfamily.framework.BaseDao;
import com.uber.uberfamily.model.BaseCard;

/**
 * @Project uber
 * @Package com.uber.uberfamily.dao
 * @Description //TODO
 * @Date 16/1/20
 * @USER saxisuer
 * @COMPANY ENMOTECH
 */
@MyBatisRepository(value = "baseCardDao")
public interface BaseCardDao extends BaseDao<BaseCard, Long> {

}
