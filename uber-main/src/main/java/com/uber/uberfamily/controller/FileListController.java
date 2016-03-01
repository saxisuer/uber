package com.uber.uberfamily.controller;

import com.github.pagehelper.PageInfo;
import com.uber.uberfamily.framework.DataStore;
import com.uber.uberfamily.framework.util.MD5Util;
import com.uber.uberfamily.framework.util.PropertiestUtil;
import com.uber.uberfamily.model.FileList;
import com.uber.uberfamily.model.User;
import com.uber.uberfamily.service.FileListService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Project uber
 * @Package com.uber.uberfamily.controller
 * @Description //TODO
 * @Date 16/2/26
 * @USER saxisuer
 * @COMPANY ENMOTECH
 */
@Controller
@RequestMapping(value = "/filelist")
public class FileListController {

    private static final Logger logger = Logger.getLogger(FileListController.class);

    private static String LIST = "WEB-INF/jsp/adFile/fileList_list";

    private static String ADD = "WEB-INF/jsp/adFile/fileList_add";

    private static String EDIT = "WEB-INF/jsp/adFile/fileList_edit";


    @Autowired
    private FileListService fileListService;


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView listPage() {
        return new ModelAndView(LIST);
    }


    @ResponseBody
    @RequestMapping(value = "/loadData", method = RequestMethod.GET)
    public DataStore<FileList> loadData(int page, int rows, FileList fileList) throws IllegalAccessException, NoSuchMethodException,
            InvocationTargetException {
        PageInfo<FileList> pageInfo = fileListService.getPage(BeanUtils.describe(fileList), page, rows);
        return new DataStore<FileList>(pageInfo.getTotal(), pageInfo.getList());
    }


    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addPage(FileList fileList) {
        return new ModelAndView(ADD, "fileList", fileList);
    }


    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView editPage(FileList fileList) {
        fileList = fileListService.getById(fileList.getId());
        return new ModelAndView(EDIT, "fileList", fileList);
    }


    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Map<String, Object> save(FileList fileList, @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
        Map<String, Object> result = new HashMap<String, Object>();
        if (null != file && file.getSize() > 0) {
            logger.info(file.getContentType());
            logger.info(file.getOriginalFilename());
            logger.info(file.getName());
            logger.info(file.getSize());
            String prefix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
            String fileUniqueName = file.getOriginalFilename().substring(0, file.getOriginalFilename().lastIndexOf(".")) + UUID.randomUUID();
            fileList.setUniqueFileName(fileUniqueName + "." + prefix);
            fileList.setFilePostfix("." + prefix);
            fileList.setFileName(file.getOriginalFilename());
            fileList.setFileSize(file.getSize());
            String savePath = PropertiestUtil.pro.get("uploadPath").toString();
            File targetFile = new File(savePath + File.separator + fileList.getUniqueFileName());
            if (!targetFile.exists()) {
                targetFile.mkdirs();
            }
            file.transferTo(targetFile);
            fileList.setMd5Check(MD5Util.getFileMD5String(targetFile));
        }
        fileList.setUploadByWho(((User) SecurityUtils.getSubject().getSession().getAttribute("userinfo")).getCnname());
        fileList.setUploadTime(new Date());
        fileList.setBoardcastMode(1L);
        fileList.setIsAudited(1);
        fileList.setAuditTime(new Date());
        fileList.setIsDisabled(0L);
        fileList.setFileStatus(1L);
        fileList.setFileStatusDesc("OK");
        fileList.setNote("OK");
        fileList.setIsUploaded(1L);
        if (null == fileList.getId() || StringUtils.isBlank(fileList.getId().toString())) {
            fileListService.create(fileList);
        } else {
            fileListService.update(fileList);
        }
        result.put("result", "SUCCESS");
        return result;
    }

    @RequestMapping(value = "/resetPercent")
    @ResponseBody
    public void resetPercent(HttpSession session) {
        session.removeAttribute("percent");
    }

    @RequestMapping(value = "/upload_listenPresent", method = RequestMethod.GET)
    @ResponseBody
    public String listenPresent(HttpServletRequest request) {
        return request.getSession().getAttribute("percent") != null ? request.getSession().getAttribute("percent").toString() : "0%";
    }

    @RequestMapping(value = "/updateData", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> updateData(FileList fileList, @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
        Map<String, String> result = new HashMap<String, String>();
        FileList fileListOld = fileListService.getById(fileList.getId());
        if (null != file && file.getSize() > 0) {
            String savePath = PropertiestUtil.pro.get("uploadPath").toString();
            FileUtils.forceDeleteOnExit(new File(savePath + File.separator + fileListOld.getUniqueFileName()));
            logger.info(file.getContentType());
            logger.info(file.getOriginalFilename());
            logger.info(file.getName());
            logger.info(file.getSize());
            String prefix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
            String fileUniqueName = file.getOriginalFilename().substring(0, file.getOriginalFilename().lastIndexOf(".")) + UUID.randomUUID();
            fileList.setUniqueFileName(fileUniqueName + "." + prefix);
            fileList.setFilePostfix("." + prefix);
            fileList.setFileName(file.getOriginalFilename());
            fileList.setFileSize(file.getSize());
            File targetFile = new File(savePath + File.separator + fileList.getUniqueFileName());
            if (!targetFile.exists()) {
                targetFile.mkdirs();
            }
            file.transferTo(targetFile);
            fileList.setMd5Check(MD5Util.getFileMD5String(targetFile));
            fileList.setUploadTime(new Date());
            fileList.setUploadByWho(((User) SecurityUtils.getSubject().getSession().getAttribute("userinfo")).getCnname());
        }
        fileList.setBoardcastMode(1L);
        fileList.setIsAudited(1);
        fileList.setAuditTime(new Date());
        fileList.setIsDisabled(0L);
        fileList.setFileStatus(1L);
        fileList.setFileStatusDesc("OK");
        fileList.setNote("OK");
        fileList.setIsUploaded(1L);
        fileListService.update(fileList);
        result.put("result", "SUCCESS");
        return result;
    }


}
