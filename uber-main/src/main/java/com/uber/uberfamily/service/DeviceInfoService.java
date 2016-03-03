package com.uber.uberfamily.service;

import com.uber.uberfamily.dao.DeviceInfoDao;
import com.uber.uberfamily.framework.BaseService;
import com.uber.uberfamily.model.DeviceInfo;

import java.util.HashMap;
import java.util.Map;

/**
 * @Project uber
 * @Package com.uber.uberfamily.service
 * @Description //TODO
 * @Date 16/2/22
 * @USER saxisuer
 * @COMPANY ENMOTECH
 */
public interface DeviceInfoService extends BaseService<DeviceInfo, Long, DeviceInfoDao> {


    HashMap<String, Object> statistic(Map<String, Object> param);

}
