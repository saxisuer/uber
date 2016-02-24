package com.uber.uberfamily.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.uber.uberfamily.dao.CallLogDao;
import com.uber.uberfamily.framework.BaseServiceImpl;
import com.uber.uberfamily.framework.MyBatisService;
import com.uber.uberfamily.model.CallLog;
import com.uber.uberfamily.service.CallLogService;
import com.uber.uberfamily.vo.CallLogDTO;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Project uber
 * @Package com.uber.uberfamily.service.impl
 * @Description //TODO
 * @Date 16/2/24
 * @USER saxisuer
 * @COMPANY ENMOTECH
 */
@MyBatisService("callLogService")
public class CallLogServiceImpl extends BaseServiceImpl<CallLog, Long, CallLogDao> implements CallLogService {

    @Resource(name = "callLogDao")
    @Override
    public void setBaseDao(CallLogDao baseDao) {
        this.baseDao = baseDao;
    }

    @Override
    public PageInfo<CallLogDTO> getPage(int page, int rows, Map<String, Object> searchMap) {
        PageHelper.startPage(page, rows);
        List<CallLogDTO> list = this.getBaseDao().getDTOList(searchMap);
        return new PageInfo<CallLogDTO>(list);
    }
}
