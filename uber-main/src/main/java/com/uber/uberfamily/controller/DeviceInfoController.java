package com.uber.uberfamily.controller;

import com.github.pagehelper.PageInfo;
import com.uber.uberfamily.framework.DataStore;
import com.uber.uberfamily.model.DeviceInfo;
import com.uber.uberfamily.service.DeviceInfoService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Project uber
 * @Package com.uber.uberfamily.controller
 * @Description //TODO
 * @Date 16/2/22
 * @USER saxisuer
 * @COMPANY ENMOTECH
 */
@Controller
@RequestMapping(value = "/deviceinfo")
public class DeviceInfoController {

    private static final Logger logger = Logger.getLogger(DeviceInfoController.class);

    @Autowired
    private DeviceInfoService deviceInfoService;

    private static String LIST = "WEB-INF/jsp/deviceInfo/deviceInfo_list";
    private static String ADD = "WEB-INF/jsp/deviceInfo/deviceInfo_add";
    private static String EDIT = "WEB-INF/jsp/deviceInfo/deviceInfo_edit";

    @RequestMapping(value = "/list")
    public ModelAndView listPage() {
        return new ModelAndView(LIST);
    }


    @RequestMapping(value = "/loadData")
    @ResponseBody
    public DataStore<DeviceInfo> getPageInfo(int page, int rows, DeviceInfo deviceInfo) throws IllegalAccessException, NoSuchMethodException,
            InvocationTargetException {
        PageInfo<DeviceInfo> pageInfo = this.deviceInfoService.getPage(BeanUtils.describe(deviceInfo), page, rows);
        return new DataStore<DeviceInfo>(pageInfo.getTotal(), pageInfo.getList());
    }


    @RequestMapping(value = "/add")
    public ModelAndView addPage() {
        return new ModelAndView(ADD);
    }


    @RequestMapping(value = "/edit")
    public ModelAndView editPage(DeviceInfo deviceInfo) {
        deviceInfo = deviceInfoService.getById(deviceInfo.getId());
        deviceInfo.setTempDate(DateFormatUtils.format(deviceInfo.getInstallationDate(), "yyyy-MM-dd"));
        return new ModelAndView(EDIT, "deviceInfo", deviceInfo);
    }


    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Map<String, String> save(DeviceInfo deviceInfo) {
        Map<String, String> result = new HashMap<String, String>();
        if (null == deviceInfo.getId()) {
            deviceInfoService.create(deviceInfo);
        } else {
            deviceInfoService.update(deviceInfo);
        }
        result.put("result", "SUCCESS");
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/delete")
    public Map<String, String> delete(Long id) {
        Map<String, String> result = new HashMap<String, String>();
        deviceInfoService.delete(id);
        result.put("result", "SUCCESS");
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/statistic")
    public List<HashMap<String, Object>> statistic(Map<String, Object> param) {
        return this.deviceInfoService.statistic(param);
    }
}
