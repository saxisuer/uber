package com.uber.uberfamily.service;

import com.github.pagehelper.PageInfo;
import com.uber.uberfamily.dao.CallLogDao;
import com.uber.uberfamily.framework.BaseService;
import com.uber.uberfamily.model.CallLog;
import com.uber.uberfamily.vo.CallLogDTO;

import java.util.Map;

/**
 * @Project uber
 * @Package com.uber.uberfamily.service
 * @Description //SERVICE
 * @Date 16/2/24
 * @USER saxisuer
 * @COMPANY ENMOTECH
 */
public interface CallLogService extends BaseService<CallLog, Long, CallLogDao> {

    PageInfo<CallLogDTO> getPage(int page, int rows, Map<String, Object> searchMap);
}
