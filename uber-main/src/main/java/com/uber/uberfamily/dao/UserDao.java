package com.uber.uberfamily.dao;

import com.uber.uberfamily.framework.BaseDao;
import com.uber.uberfamily.framework.MyBatisRepository;
import com.uber.uberfamily.model.User;

/**
 * @Project uber
 * @Package com.uber.uberfamily.dao
 * @Description //TODO
 * @Date 16/1/21
 * @USER saxisuer
 * @COMPANY ENMOTECH
 */
@MyBatisRepository
public interface UserDao extends BaseDao<User, Long> {
    User getUserByName(String name);
}
