package com.uber.uberfamily.controller;

import com.github.pagehelper.PageInfo;
import com.uber.uberfamily.framework.DataStore;
import com.uber.uberfamily.model.DeviceGroup;
import com.uber.uberfamily.service.DeviceGroupService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Project uber
 * @Package com.uber.uberfamily.controller
 * @Description //设备组
 * @Date 16/3/6
 * @USER saxisuer
 * @COMPANY ENMOTECH
 */
@Controller
@RequestMapping(value = "/deviceGroup")
public class DeviceGroupController {


    private static final Logger logger = Logger.getLogger(DeviceGroupController.class);

    private static String LIST = "WEB-INF/jsp/deviceGroup/device_group_list";
    private static String ADD = "WEB-INF/jsp/deviceGroup/device_group_add";
    private static String EDIT = "WEB-INF/jsp/deviceGroup/device_group_edit";

    @Autowired
    private DeviceGroupService deviceGroupService;


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView getList() {
        return new ModelAndView(LIST);
    }


    @RequestMapping(value = "/add")
    public ModelAndView add() {
        return new ModelAndView(ADD);
    }

    @RequestMapping(value = "/edit/{id}")
    public ModelAndView edit(@PathVariable Long id) {
        DeviceGroup deviceGroup = deviceGroupService.getById(id);
        return new ModelAndView(EDIT, "deviceGroup", deviceGroup);
    }


    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Map<String, String> save(DeviceGroup deviceGroup) {
        Map<String, String> result = new HashMap<String, String>();
        if (null == deviceGroup.getId()) {
            deviceGroupService.create(deviceGroup);
        } else {
            deviceGroupService.update(deviceGroup);
        }
        result.put("result", "SUCCESS");
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/loadData", method = RequestMethod.GET)
    public DataStore<DeviceGroup> loadData(int page, int rows, DeviceGroup deviceGroup) throws IllegalAccessException, NoSuchMethodException,
            InvocationTargetException {
        Map<String, Object> searchMap = BeanUtils.describe(deviceGroup);
        PageInfo<DeviceGroup> pageInfo = deviceGroupService.getPage(searchMap, page, rows);
        return new DataStore<DeviceGroup>(pageInfo.getTotal(), pageInfo.getList());
    }


    @RequestMapping(value = "/delete/{id}")
    @ResponseBody
    public Map<String, String> delete(@PathVariable Long id) {
        Map<String, String> result = new HashMap<String, String>();
        this.deviceGroupService.delete(id);
        result.put("result", "SUCCESS");
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/getCombobo", method = RequestMethod.GET)
    public List<Map<String, Object>> getCombobo(Long cityCode) {
        return this.deviceGroupService.getDeviceGroupCombo(cityCode);
    }


    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Map<String, String> showException(Exception ex) {
        logger.error(ex);
        Map<String, String> result = new HashMap<String, String>();
        String errorMessage = ExceptionUtils.getRootCauseMessage(ex);
        result.put("result", "ERROR");
        result.put("str", errorMessage);
        return result;
    }
}
