package com.uber.uberfamily.dao;

import com.uber.uberfamily.framework.BaseDao;
import com.uber.uberfamily.framework.MyBatisRepository;
import com.uber.uberfamily.model.DeviceInfo;

/**
 * @Project uber
 * @Package com.uber.uberfamily.dao
 * @Description //一体机设备 信息管理 DAO 层
 * @Date 16/2/22
 * @USER saxisuer
 * @COMPANY ENMOTECH
 */
@MyBatisRepository("deviceInfoDao")
public interface DeviceInfoDao extends BaseDao<DeviceInfo, Long> {
}
