package com.uber.uberfamily.controller;

import com.github.pagehelper.PageInfo;
import com.uber.uberfamily.framework.DataStore;
import com.uber.uberfamily.framework.util.MD5Util;
import com.uber.uberfamily.framework.util.PropertiestUtil;
import com.uber.uberfamily.model.FileList;
import com.uber.uberfamily.model.User;
import com.uber.uberfamily.service.DeviceInfoService;
import com.uber.uberfamily.service.FileListService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

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

    @Autowired
    private DeviceInfoService deviceInfoService;


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
    public Map<String, Object> save(FileList fileList, @RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request)
            throws
            IOException {
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
            String context = request.getSession().getServletContext().getRealPath("");
            savePath = context + savePath;
            logger.info(savePath);
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
    public Map<String, String> updateData(FileList fileList, @RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest
            request) throws
            IOException {
        Map<String, String> result = new HashMap<String, String>();
        FileList fileListOld = fileListService.getById(fileList.getId());
        if (null != file && file.getSize() > 0) {
            String savePath = PropertiestUtil.pro.get("uploadPath").toString();
            String context = request.getSession().getServletContext().getRealPath("");
            savePath = context + savePath;
            File oldFile = new File(savePath + File.separator + fileListOld.getUniqueFileName());
            FileUtils.forceDelete(oldFile);
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


    @RequestMapping(value = "/bindDeviceInfo")
    @ResponseBody
    public HashMap<String, String> bindDeviceInfo(String deviceIds, String fileListId, String fileListLevel) {
        HashMap<String, String> result = new HashMap<>();
        String[] deviceId = deviceIds.split(",");
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (String d : deviceId) {
            Map<String, Object> m = new HashMap<>();
            m.put("fileListId", fileListId);
            m.put("deviceId", d);
            m.put("fileListLevel", fileListLevel);
            list.add(m);
        }
        fileListService.bindDeviceInfo(list);
        result.put("result", "success");
        return result;
    }


    @ResponseBody
    @RequestMapping(value = "/delete/{id}")
    public Map<String, String> delete(@PathVariable Long id) {
        Map<String, String> result = new HashMap<String, String>();
        this.fileListService.delete(id);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/getFileByDeviceInfo/{deviceId}")
    public Map<String, String> getFileByDeviceInfo(@PathVariable Long deviceId, HttpServletRequest request) {
        Map<String, String> result = new HashMap<>();
        FileList fileList = fileListService.getFileForDevice(deviceId);
        if (null != fileList) {
            String savePath = File.separator + PropertiestUtil.pro.get("uploadPath").toString();
            String context = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
            String downLoadFilePath = context + savePath + File.separator + fileList.getUniqueFileName();
            logger.info(downLoadFilePath);
            result.put("savePath", downLoadFilePath);
            result.put("fileName", fileList.getFileName());
            result.put("startTime", DateFormatUtils.format(fileList.getStartTime(), "yyyy-MM-dd HH:mm:ss"));
            result.put("endTime", DateFormatUtils.format(fileList.getEndTime(), "yyyy-MM-dd HH:mm:ss"));
        } else {
            result.put("error", "no file find");
        }
        return result;
    }


    @ResponseBody
    @RequestMapping(value = "/getFileListByDeviceInfo/{deviceUUID}")
    public Map<String, Object> getFileListByDeviceInfo(@PathVariable String deviceUUID, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        Long deviceId = deviceInfoService.getCountByUUID(deviceUUID);
        if (null == deviceId) {
            result.put("result", "error");
            result.put("msg", "no device find with this deviceUUID");
            return result;
        } else {
            List<FileList> fileLists = fileListService.getFileListForDevice(deviceId);
            List<Map<String, String>> resultContent = new ArrayList<Map<String, String>>();
            for (FileList fileList : fileLists) {
                Map<String, String> map = new HashMap<String, String>();
                String savePath = File.separator + PropertiestUtil.pro.get("uploadPath").toString();
                String context = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
                String downLoadFilePath = context + savePath + File.separator + fileList.getUniqueFileName();
                logger.info(downLoadFilePath);
                map.put("savePath", downLoadFilePath);
                map.put("fileName", fileList.getFileName());
                map.put("startTime", DateFormatUtils.format(fileList.getStartTime(), "yyyy-MM-dd HH:mm:ss"));
                map.put("endTime", DateFormatUtils.format(fileList.getEndTime(), "yyyy-MM-dd HH:mm:ss"));
                resultContent.add(map);
            }
            result.put("resultContent", resultContent);
            return result;
        }
    }

}
