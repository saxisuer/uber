package com.uber.uberfamily.dao;

import com.uber.uberfamily.framework.BaseDao;
import com.uber.uberfamily.framework.MyBatisRepository;
import com.uber.uberfamily.model.Permission;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Project uber
 * @Package com.uber.uberfamily.dao
 * @Description //TODO
 * @Date 16/1/20
 * @USER saxisuer
 * @COMPANY ENMOTECH
 */
@MyBatisRepository
public interface PermissionDao extends BaseDao<Permission, Long> {

    void createPermissionRole(Map<String, Object> paramMap);

    List<Permission> getModuleGroups();

    Set<Permission> getChildren(Long permissionId);

    List<Permission> getPermission(Map<String, Object> map);

    List<Map<String, Object>> getPermissionMap(Map<String, Object> param);

    void deletePermissionRoleByRoleId(Long roleId);
}
