package com.uber.uberfamily.service.impl;

import com.uber.uberfamily.dao.UserDao;
import com.uber.uberfamily.framework.BaseServiceImpl;
import com.uber.uberfamily.framework.MyBatisService;
import com.uber.uberfamily.model.User;
import com.uber.uberfamily.service.RoleService;
import com.uber.uberfamily.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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

    private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

    @Autowired
    private RoleService roleService;

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

    @Override
    public User save(User user) {
        if (user.getId() == null || StringUtils.isBlank(user.getId().toString())) {
            user = this.create(user);
        } else {
            this.update(user);
        }
        if (user.getRoleIds() != null) {
            roleService.deleteRoleByUserId(user.getId());
            String[] ids = user.getRoleIds().split(",");
            for (String id : ids) {
                roleService.createUserRole(user.getId(), NumberUtils.toLong(id));
            }
        }
        return user;
    }

    @Override
    public void delete(Long id) {
        super.delete(id);
        roleService.deleteRoleByUserId(id);
    }
}
