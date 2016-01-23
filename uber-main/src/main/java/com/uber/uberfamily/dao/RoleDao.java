package com.uber.uberfamily.dao;

import com.uber.uberfamily.framework.BaseDao;
import com.uber.uberfamily.framework.MyBatisRepository;
import com.uber.uberfamily.model.Role;

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

}
