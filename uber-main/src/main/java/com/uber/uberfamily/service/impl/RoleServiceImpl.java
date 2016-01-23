package com.uber.uberfamily.service.impl;

import com.uber.uberfamily.dao.RoleDao;
import com.uber.uberfamily.framework.BaseServiceImpl;
import com.uber.uberfamily.framework.MyBatisService;
import com.uber.uberfamily.model.Role;
import com.uber.uberfamily.service.RoleService;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.Set;

/**
 * @Project uber
 * @Package com.uber.uberfamily.service.impl
 * @Description //TODO
 * @Date 16/1/21
 * @USER saxisuer
 * @COMPANY ENMOTECH
 */
@MyBatisService
public class RoleServiceImpl extends BaseServiceImpl<Role, Long, RoleDao> implements RoleService {
    @Override
    @Resource(name = "roleDao")
    public void setBaseDao(RoleDao roleDao) {
        this.baseDao = roleDao;
    }

    @Override
    public Set<Role> getRoleSetByUserId(Long userId) {
        Assert.notNull(userId);
        return this.getBaseDao().getRoleByUserId(userId);
    }


    @Override
    public Role getRoleById(Long id) {
        return this.getBaseDao().getRoleById(id);
    }
}
