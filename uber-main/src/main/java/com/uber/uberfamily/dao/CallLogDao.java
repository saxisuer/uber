package com.uber.uberfamily.dao;

import com.uber.uberfamily.framework.BaseDao;
import com.uber.uberfamily.framework.MyBatisRepository;
import com.uber.uberfamily.model.CallLog;
import com.uber.uberfamily.vo.CallLogDTO;

import java.util.List;
import java.util.Map;

/**
 * @Project uber
 * @Package com.uber.uberfamily.dao
 * @Description //TODO
 * @Date 16/2/24
 * @USER saxisuer
 * @COMPANY ENMOTECH
 */
@MyBatisRepository("callLogDao")
public interface CallLogDao extends BaseDao<CallLog, Long> {
    List<CallLogDTO> getDTOList(Map<String, Object> searchMap);
}
