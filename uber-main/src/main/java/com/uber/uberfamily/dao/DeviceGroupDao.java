package com.uber.uberfamily.dao;

import com.uber.uberfamily.framework.BaseDao;
import com.uber.uberfamily.framework.MyBatisRepository;
import com.uber.uberfamily.model.DeviceGroup;

import java.util.List;
import java.util.Map;

/**
 * @Project uber
 * @Package com.uber.uberfamily.dao
 * @Description //TODO
 * @Date 16/3/6
 * @USER saxisuer
 * @COMPANY ENMOTECH
 */
@MyBatisRepository
public interface DeviceGroupDao extends BaseDao<DeviceGroup, Long> {


    List<Map<String, Object>> getDeviceGroupCombo(Long cityCode);
}
