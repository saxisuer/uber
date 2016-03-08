package com.uber.uberfamily.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.uber.uberfamily.dao.DeviceGroupDao;
import com.uber.uberfamily.framework.BaseServiceImpl;
import com.uber.uberfamily.framework.MyBatisService;
import com.uber.uberfamily.model.DeviceGroup;
import com.uber.uberfamily.service.DeviceGroupService;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Project uber
 * @Package com.uber.uberfamily.service.impl
 * @Description //TODO
 * @Date 16/3/6
 * @USER saxisuer
 * @COMPANY ENMOTECH
 */
@MyBatisService
public class DeviceGroupServiceImpl extends BaseServiceImpl<DeviceGroup, Long, DeviceGroupDao> implements DeviceGroupService {

    @Override
    @Resource(name = "deviceGroupDao")
    public void setBaseDao(DeviceGroupDao baseDao) {
        this.baseDao = baseDao;
    }


    @Override
    public List<Map<String, Object>> getDeviceGroupCombo(Long cityCode) {
        return this.baseDao.getDeviceGroupCombo(cityCode);
    }

    @Override
    public PageInfo<DeviceGroup> getListByTempId(Long tempId) {
        Assert.notNull(tempId);
        PageHelper.startPage(0, 0);
        List<DeviceGroup> list = this.getBaseDao().getListByTempId(tempId);
        return new PageInfo<DeviceGroup>(list);
    }
}
