package com.uber.uberfamily.framework;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @Project uber
 * @Package com.uber.uberfamily.dao
 * @Description //基础DAO
 * @Date 16/1/20
 * @USER saxisuer
 * @COMPANY ENMOTECH
 */
public interface BaseDao<T extends BaseModel, PK extends Serializable> {

    T getById(PK id);

    T create(T model);

    T update(T model);

    T load(PK id);

    void delete(PK id);

    List<T> getList(Map<String, Object> param);
}
