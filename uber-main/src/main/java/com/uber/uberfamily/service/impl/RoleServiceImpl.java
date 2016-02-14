package com.uber.uberfamily.service.impl;

import com.uber.uberfamily.dao.RoleDao;
import com.uber.uberfamily.framework.BaseServiceImpl;
import com.uber.uberfamily.framework.MyBatisService;
import com.uber.uberfamily.model.Role;
import com.uber.uberfamily.service.PermissionService;
import com.uber.uberfamily.service.RoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Project uber
 * @Package com.uber.uberfamily.service.impl
 * @Description //TODO
 * @Date 16/1/21
 * @USER saxisuer
 * @COMPANY ENMOTECH
 */
@MyBatisService("roleService")
public class RoleServiceImpl extends BaseServiceImpl<Role, Long, RoleDao> implements RoleService {

    @Autowired
    private PermissionService permissionService;

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

    @Override
    public List<Map<String, Object>> getRoleMap(Map<String, Object> paramMap) {
        return this.getBaseDao().getRoleMap(paramMap);
    }

    @Override
    public void deleteRoleByUserId(Long userId) {
        this.getBaseDao().deleteRoleByUserId(userId);
    }

    @Override
    public void createUserRole(Long userId, Long roleId) {
        Assert.notNull(userId, "用户 ID 不能为空");
        Map<String, Long> insertMap = new HashMap<>();
        insertMap.put("userId", userId);
        insertMap.put("roleId", roleId);
        this.getBaseDao().createUserRole(insertMap);
    }

    @Override
    public Role getRoleByName(String name) {
        Assert.notNull(name);
        return this.getBaseDao().getRoleByName(name);
    }

    @Override
    public Role save(Role role) {
        if (null == role.getId() || StringUtils.isBlank(role.getId().toString())) {
            role = this.create(role);
        } else {
            this.update(role);
        }
        if (StringUtils.isNotBlank(role.getPermissionIds())) {
            permissionService.deletePermissionRoleByRoleId(role.getId());
            permissionService.batchInsert(role.getId(), role.getPermissionIds());
        }
        return role;
    }

    @Override
    public void delete(Long id) {
        super.delete(id);
        permissionService.deletePermissionRoleByRoleId(id);
    }
}
