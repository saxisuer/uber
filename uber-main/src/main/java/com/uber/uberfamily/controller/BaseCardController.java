package com.uber.uberfamily.controller;

import com.github.pagehelper.PageInfo;
import com.uber.uberfamily.framework.DataStore;
import com.uber.uberfamily.model.BaseCard;
import com.uber.uberfamily.model.User;
import com.uber.uberfamily.service.BaseCardService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * @Project uber
 * @Package com.uber.uberfamily.controller
 * @Description //TODO
 * @Date 16/2/15
 * @USER saxisuer
 * @COMPANY ENMOTECH
 */
@Controller
@RequestMapping(value = "/baseCard")
public class BaseCardController {

    private static final Logger logger = Logger.getLogger(BaseCardController.class);

    private static String LIST = "WEB-INF/jsp/card/baseCard_list";

    @Autowired
    private BaseCardService baseCardService;


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView listPage() {
        return new ModelAndView(LIST);
    }


    @ResponseBody
    @RequestMapping(value = "/loadData", method = RequestMethod.GET)
    public DataStore<BaseCard> loadData(int page, int rows, BaseCard baseCard) throws IllegalAccessException, NoSuchMethodException,
            InvocationTargetException {
        PageInfo<BaseCard> pageInfo = baseCardService.getPage(BeanUtils.describe(baseCard), page, rows);
        return new DataStore<>(pageInfo.getTotal(), pageInfo.getList());
    }

    @RequestMapping(value = "/baseCardfileUpload")
    public Map<String, Object> baseCardfileUpload(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request)
            throws IOException {
        Map<String, Object> result = new HashMap<>();
        String path = request.getServletContext().getRealPath("/upload") + File.separator;
        if (null != file) {
            logger.info(file.getName());
            logger.info(file.getContentType());
            logger.info(file.getOriginalFilename());
            //file.transferTo(new File(path + File.separator + file.getOriginalFilename()));
            //FileCopyUtils.copy(file.getBytes(), new File(path + File.separator + file.getOriginalFilename()));
            importExcel(file.getInputStream(), file.getOriginalFilename());
        }
        result.put("result", "SUCCESS");
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/delete/{id}")
    public Map<String, Object> delete(@PathVariable Long id) {
        Map<String, Object> result = new HashMap<>();
        this.baseCardService.delete(id);
        result.put("result", "SUCCESS");
        return result;
    }


    private void importExcel(InputStream inputStream, String fileName) throws IOException {
        int index = fileName.indexOf("xlsx");
        boolean version07 = false;
        if (index > -1) {
            version07 = true;
        }
        if (!version07) {
            HSSFWorkbook wb = new HSSFWorkbook(inputStream);
            HSSFSheet sheet = wb.getSheetAt(0);
            importData(sheet);
        } else {
            XSSFWorkbook xwb = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = xwb.getSheetAt(0);
            importData(sheet);
        }
    }

    private void importData(Sheet sheet) {
        Iterator<Row> it = sheet.rowIterator();
        List<BaseCard> insertList = new ArrayList<BaseCard>();
        while (it.hasNext()) {
            Row row = it.next();
            Cell c1 = row.getCell(1);
            Cell c2 = row.getCell(2);
            if (StringUtils.isNotBlank(c1.toString()) && StringUtils.isNotBlank(c2.toString())) {
                BaseCard bc = new BaseCard();
                bc.setCardNoLogical(c1.toString());
                bc.setCardNoPhysical(c2.toString());
                bc.setCreateTime(new Date());
                bc.setCreateByWho(((User) SecurityUtils.getSubject().getSession().getAttribute("userinfo")).getName());
                bc.setUuid(UUID.randomUUID().toString().replaceAll("-", ""));
                insertList.add(bc);
            }
        }
        this.baseCardService.saveExcelData(insertList);
    }
}
