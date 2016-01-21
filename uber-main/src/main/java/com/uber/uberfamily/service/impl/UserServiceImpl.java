package com.uber.uberfamily.service.impl;

import com.uber.uberfamily.dao.UserDao;
import com.uber.uberfamily.framework.BaseServiceImpl;
import com.uber.uberfamily.framework.MyBatisService;
import com.uber.uberfamily.model.User;
import com.uber.uberfamily.service.UserService;
import org.springframework.util.Assert;

import javax.annotation.Resource;

/**
 * @Project uber
 * @Package com.uber.uberfamily.service.impl
 * @Description //TODO
 * @Date 16/1/21
 * @USER saxisuer
 * @COMPANY ENMOTECH
 */
@MyBatisService
public class UserServiceImpl extends BaseServiceImpl<User, Long, UserDao> implements UserService {
    @Override
    @Resource(name = "userDao")
    public void setBaseDao(UserDao userDao) {
        this.baseDao = userDao;
    }

    @Override
    public User getUserByName(String name) {
        Assert.notNull(name, "userName must not be null");
        return this.getBaseDao().getUserByName(name);
    }
}
