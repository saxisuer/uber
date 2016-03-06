package com.uber.uberfamily.service.impl;

import com.uber.uberfamily.dao.FileListDao;
import com.uber.uberfamily.framework.BaseServiceImpl;
import com.uber.uberfamily.framework.MyBatisService;
import com.uber.uberfamily.model.FileList;
import com.uber.uberfamily.service.FileListService;
import org.apache.log4j.Logger;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Project uber
 * @Package com.uber.uberfamily.service.impl
 * @Description //广告文件管理上传类
 * @Date 16/2/26
 * @USER saxisuer
 * @COMPANY ENMOTECH
 */
@MyBatisService("fileListService")
public class FileListServiceImpl extends BaseServiceImpl<FileList, Long, FileListDao> implements FileListService {

    private static final Logger logger = Logger.getLogger(FileListServiceImpl.class);


    @Resource(name = "fileListDao")
    @Override
    public void setBaseDao(FileListDao baseDao) {
        this.baseDao = baseDao;
    }

    @Override
    public void bindDeviceInfo(List<Map<String, Object>> list) {
        for (Map<String, Object> m : list) {
            this.getBaseDao().deleteDeviceInfoRel(m);
            this.getBaseDao().insertDeviceInfoRel(m);
        }
    }

    @Override
    public FileList getFileForDevice(Long deviceId) {
        Assert.notNull(deviceId);
        return this.getBaseDao().getFileForDevice(deviceId);
    }

    @Override
    public void delete(Long id) {
        this.getBaseDao().deleteDeviceInfoRelByFileId(id);
        super.delete(id);
    }
}
