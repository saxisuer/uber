package com.uber.uberfamily.service;

import com.uber.uberfamily.dao.DeviceGroupDao;
import com.uber.uberfamily.framework.BaseService;
import com.uber.uberfamily.model.DeviceGroup;

import java.util.List;
import java.util.Map;

/**
 * @Project uber
 * @Package com.uber.uberfamily.service
 * @Description //TODO
 * @Date 16/3/6
 * @USER saxisuer
 * @COMPANY ENMOTECH
 */
public interface DeviceGroupService extends BaseService<DeviceGroup, Long, DeviceGroupDao> {

    List<Map<String, Object>> getDeviceGroupCombo(Long cityCode);
}
