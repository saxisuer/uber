package com.uber.uberfamily.dao;

import com.uber.uberfamily.framework.BaseDao;
import com.uber.uberfamily.framework.MyBatisRepository;
import com.uber.uberfamily.model.Role;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Project uber
 * @Package com.uber.uberfamily.dao
 * @Description //TODO
 * @Date 16/1/21
 * @USER saxisuer
 * @COMPANY ENMOTECH
 */
@MyBatisRepository
public interface RoleDao extends BaseDao<Role, Long> {

    Set<Role> getRoleByUserId(Long userId);

    Role getRoleById(Long id);

    /**
     * 获得 ROLE 结果集的 MAP 封装,有的权限是 TRUE 没有的权限是 FALSE
     *
     * @param paramMap
     * @return
     */
    List<Map<String, Object>> getRoleMap(Map<String, Object> paramMap);

    void deleteRoleByUserId(Long id);

    void createUserRole(Map<String, Long> insertMap);
}
