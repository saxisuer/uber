package com.uber.uberfamily.service;

import com.uber.uberfamily.dao.UserDao;
import com.uber.uberfamily.framework.BaseService;
import com.uber.uberfamily.model.User;

/**
 * @Project uber
 * @Package com.uber.uberfamily.service
 * @Description //TODO
 * @Date 16/1/21
 * @USER saxisuer
 * @COMPANY ENMOTECH
 */
public interface UserService extends BaseService<User, Long, UserDao> {

    User getUserByName(String name);

    User save(User user);
}
