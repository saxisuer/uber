package com.uber.uberfamily.framework;

import com.github.pagehelper.PageInfo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @Project uber
 * @Package com.uber.uberfamily.service
 * @Description //TODO
 * @Date 16/1/20
 * @USER saxisuer
 * @COMPANY ENMOTECH
 */
public interface BaseService<T extends BaseModel, PK extends Serializable, E extends BaseDao<T, PK>> {

    E getBaseDao();

    T getById(PK id);

    T create(T model);

    T update(T model);

    void delete(PK id);

    List<T> getList(Map<String, Object> paramMap);

    T getByParameter(Map<String, Object> param);


    PageInfo<T> getPage(Map<String, Object> param, int pageNum, int pageSize);

}
