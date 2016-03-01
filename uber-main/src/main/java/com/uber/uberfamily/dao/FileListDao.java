package com.uber.uberfamily.dao;

import com.uber.uberfamily.framework.BaseDao;
import com.uber.uberfamily.framework.MyBatisRepository;
import com.uber.uberfamily.model.FileList;

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


}
