package com.uber.uberfamily.service.impl;

import com.uber.uberfamily.dao.DeviceInfoDao;
import com.uber.uberfamily.framework.BaseServiceImpl;
import com.uber.uberfamily.framework.MyBatisService;
import com.uber.uberfamily.model.DeviceInfo;
import com.uber.uberfamily.service.DeviceInfoService;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Project uber
 * @Package com.uber.uberfamily.service.impl
 * @Description //TODO
 * @Date 16/2/22
 * @USER saxisuer
 * @COMPANY ENMOTECH
 */
@MyBatisService("deviceInfoService")
public class DeviceInfoServiceImpl extends BaseServiceImpl<DeviceInfo, Long, DeviceInfoDao> implements DeviceInfoService {
    @Override
    @Resource(name = "deviceInfoDao")
    public void setBaseDao(DeviceInfoDao baseDao) {
        this.baseDao = baseDao;
    }

    @Override
    public List<HashMap<String, Object>> statistic(Map<String, Object> param) {
        return this.getBaseDao().statistic(param);
    }
}
