package com.uber.uberfamily.dao;

import com.uber.uberfamily.framework.BaseDao;
import com.uber.uberfamily.framework.MyBatisRepository;
import com.uber.uberfamily.model.AdTemplate;

import java.util.List;
import java.util.Map;

/**
 * @Project uber
 * @Package com.uber.uberfamily.dao
 * @Description //TODO
 * @Date 16/3/7
 * @USER saxisuer
 * @COMPANY ENMOTECH
 */
@MyBatisRepository
public interface AdTemplateDao extends BaseDao<AdTemplate, Long> {


    void createRel(List<Map<String, Object>> insertMap);

    void deleteRelByTempId(Long id);
}
