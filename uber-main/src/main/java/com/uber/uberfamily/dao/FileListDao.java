package com.uber.uberfamily.dao;

import com.uber.uberfamily.framework.BaseDao;
import com.uber.uberfamily.framework.MyBatisRepository;
import com.uber.uberfamily.model.FileList;

import java.util.List;
import java.util.Map;

/**
 * @Project uber
 * @Package com.uber.uberfamily.dao
 * @Description //TODO
 * @Date 16/2/26
 * @USER saxisuer
 * @COMPANY ENMOTECH
 */
@MyBatisRepository("fileListDao")
public interface FileListDao extends BaseDao<FileList, Long> {

    void deleteDeviceInfoRel(Map<String, Object> map);

    void insertDeviceInfoRel(Map<String, Object> map);

    FileList getFileForDevice(Long deviceId);

    void deleteDeviceInfoRelByFileId(Long fileListId);

    List<FileList> getFileListForDevice(Long deviceId);
}
