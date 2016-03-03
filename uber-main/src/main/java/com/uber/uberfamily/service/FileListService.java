package com.uber.uberfamily.service;

import com.uber.uberfamily.dao.FileListDao;
import com.uber.uberfamily.framework.BaseService;
import com.uber.uberfamily.model.FileList;

import java.util.List;
import java.util.Map;

/**
 * @Project uber
 * @Package com.uber.uberfamily.service
 * @Description //TODO
 * @Date 16/2/26
 * @USER saxisuer
 * @COMPANY ENMOTECH
 */
public interface FileListService extends BaseService<FileList, Long, FileListDao> {
    void bindDeviceInfo(List<Map<String, Object>> list);

    FileList getFileForDevice(Long deviceId);

}
