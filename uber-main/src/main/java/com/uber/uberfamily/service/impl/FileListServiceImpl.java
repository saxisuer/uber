package com.uber.uberfamily.service.impl;

import com.uber.uberfamily.dao.FileListDao;
import com.uber.uberfamily.framework.BaseServiceImpl;
import com.uber.uberfamily.framework.MyBatisService;
import com.uber.uberfamily.model.FileList;
import com.uber.uberfamily.service.FileListService;
import org.apache.log4j.Logger;

import javax.annotation.Resource;

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

}
