package com.uber.uberfamily.service;

import com.uber.uberfamily.dao.AdTemplateDao;
import com.uber.uberfamily.framework.BaseService;
import com.uber.uberfamily.model.AdTemplate;

import java.util.List;
import java.util.Map;

/**
 * @Project uber
 * @Package com.uber.uberfamily.service
 * @Description //TODO
 * @Date 16/3/7
 * @USER saxisuer
 * @COMPANY ENMOTECH
 */
public interface AdTemplateService extends BaseService<AdTemplate, Long, AdTemplateDao> {


    void deleteRelByTempId(Long id);

    void createRel(List<Map<String,Object>> insertList);
}
