package com.uber.uberfamily.framework;

import java.io.Serializable;

/**
 * @Project uber
 * @Package com.uber.uberfamily.service
 * @Description //TODO
 * @Date 16/1/20
 * @USER saxisuer
 * @COMPANY ENMOTECH
 */
public interface BaseService<T extends BaseModel, PK extends Serializable, E extends BaseDao<T, PK>> {

    T getById(PK id);
}
