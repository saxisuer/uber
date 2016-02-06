package com.uber.uberfamily.service;

import com.uber.uberfamily.dao.RoleDao;
import com.uber.uberfamily.framework.BaseService;
import com.uber.uberfamily.model.Role;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Project uber
 * @Package com.uber.uberfamily.service
 * @Description //TODO
 * @Date 16/1/21
 * @USER saxisuer
 * @COMPANY ENMOTECH
 */
public interface RoleService extends BaseService<Role, Long, RoleDao> {

    Set<Role> getRoleSetByUserId(Long userId);


    Role getRoleById(Long id);

    List<Map<String, Object>> getRoleMap(Map<String,Object> paramMap);

    void deleteRoleByUserId(Long userId);

    void createUserRole(Long userId, String roleId);
}
