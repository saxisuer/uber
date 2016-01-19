package com.uber.uberfamily.framework;

import java.io.Serializable;

/**
 * @Project uber
 * @Package com.uber.uberfamily.dao
 * @Description //基础DAO
 * @Date 16/1/20
 * @USER saxisuer
 * @COMPANY ENMOTECH
 */
public interface BaseDao<T, PK extends Serializable> {

    T getById(PK id);
}
