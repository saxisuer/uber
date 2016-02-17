package com.uber.uberfamily.framework;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private SqlSessionTemplate batchSqlSession;

    @Override
    public E getBaseDao() {
        return baseDao;
    }

    public abstract void setBaseDao(E baseDao);

    @Override
    public T getById(PK id) {
        return this.getBaseDao().getById(id);
    }

    @Override
    public T create(T model) {
        this.getBaseDao().create(model);
        return model;
    }

    @Override
    public T update(T model) {
        return this.getBaseDao().update(model) > 0 ? model : null;
    }

    @Override
    public void delete(PK id) {
        this.getBaseDao().delete(id);
    }

    @Override
    public List<T> getList(Map<String, Object> paramMap) {
        return this.getBaseDao().getList(paramMap);
    }

    @Override
    public PageInfo<T> getPage(Map<String, Object> param, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<T> list = this.getBaseDao().getList(param);
        return new PageInfo<T>(list);
    }

    @Override
    public T getByParameter(Map<String, Object> param) {
        return this.getBaseDao().getByParameter(param);
    }


    @Override
    public void batchInsert(String sqlMapper, List<Map<String, Object>> params) {
        for (Map<String, Object> map : params) {
            batchSqlSession.insert(sqlMapper, map);
        }
    }

    @Override
    public void insertBatch(String sqlMapper, List<T> params) {
        for (T t : params) {
            batchSqlSession.insert(sqlMapper, t);
        }
    }
}
