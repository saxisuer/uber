package com.uber.uberfamily.framework;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * @Project uber
 * @Package com.uber.uberfamily.framework
 * @Description //TODO
 * @Date 16/1/20
 * @USER saxisuer
 * @COMPANY ENMOTECH
 */
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
public abstract class BaseServiceImpl<T extends BaseModel, PK extends Serializable, E extends BaseDao<T, PK>> implements BaseService<T, PK, E> {

    protected final Logger logger = Logger.getLogger(this.getClass().getName());

    protected E baseDao;

    public E getBaseDao() {
        return baseDao;
    }

    public abstract void setBaseDao(E baseDao);

    @Override
    public T getById(PK id) {
        return this.getBaseDao().getById(id);
    }
}
