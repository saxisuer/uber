package com.uber.uberfamily.controller;

import com.github.pagehelper.PageInfo;
import com.uber.uberfamily.framework.DataStore;
import com.uber.uberfamily.model.*;
import com.uber.uberfamily.service.AdTemplateService;
import com.uber.uberfamily.service.CityService;
import com.uber.uberfamily.service.DeviceGroupService;
import com.uber.uberfamily.service.DeviceInfoService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Project uber
 * @Package com.uber.uberfamily.controller
 * @Description //TODO
 * @Date 16/3/7
 * @USER saxisuer
 * @COMPANY ENMOTECH
 */
@Controller
@RequestMapping(value = "/adTemplate")
public class AdTemplateController {


    private static final Logger logger = Logger.getLogger(AdTemplateController.class);

    private static String LIST = "WEB-INF/jsp/adTemplate/ad_template_list";
    private static String ADD = "WEB-INF/jsp/adTemplate/ad_template_add";
    private static String EDIT = "WEB-INF/jsp/adTemplate/ad_template_edit";

    @Autowired
    private AdTemplateService adTemplateService;

    @Autowired
    private CityService cityService;

    @Autowired
    private DeviceGroupService deviceGroupService;

    @Autowired
    private DeviceInfoService deviceInfoService;


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list() {
        return new ModelAndView(LIST);
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView add() {
        return new ModelAndView(ADD);
    }

    @RequestMapping(value = "edit/{id}")
    public ModelAndView edit(@PathVariable Long id) {
        AdTemplate adTemplate = adTemplateService.getById(id);
        return new ModelAndView(EDIT, "adTemplate", adTemplate);
    }


    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Map<String, String> save(AdTemplate adTemplate) {
        Map<String, String> result = new HashMap<>();
        if (null != adTemplate.getId()) {
            adTemplateService.update(adTemplate);
        } else {
            adTemplate.setCreator(getCurrentUser().getName());
            adTemplateService.create(adTemplate);
        }
        result.put("result", "SUCCESS");
        return result;
    }

    @RequestMapping(value = "/loadData")
    @ResponseBody
    public DataStore<AdTemplate> loadData(int page, int rows, AdTemplate adTemplate) throws IllegalAccessException, NoSuchMethodException,
            InvocationTargetException {
        Map<String, Object> searchMap = BeanUtils.describe(adTemplate);
        PageInfo<AdTemplate> pageInfo = adTemplateService.getPage(searchMap, page, rows);
        return new DataStore<AdTemplate>(pageInfo.getTotal(), pageInfo.getList());
    }

    @RequestMapping(value = "/loadCityData")
    @ResponseBody
    public DataStore<City> loadCityData(Long tempId) throws IllegalAccessException, NoSuchMethodException,
            InvocationTargetException {
        PageInfo<City> pageInfo = cityService.loadCityByAdTemp(tempId);
        return new DataStore<City>(pageInfo.getTotal(), pageInfo.getList());
    }

    @RequestMapping(value = "/loadGroupData")
    @ResponseBody
    public DataStore<DeviceGroup> loadGroupData(Long tempId) throws IllegalAccessException, NoSuchMethodException,
            InvocationTargetException {
        PageInfo<DeviceGroup> pageInfo = deviceGroupService.getListByTempId(tempId);
        return new DataStore<DeviceGroup>(pageInfo.getTotal(), pageInfo.getList());
    }


    @RequestMapping(value = "/loadDeviceData")
    @ResponseBody
    public DataStore<DeviceInfo> loadDeviceData(Long tempId) throws IllegalAccessException, NoSuchMethodException,
            InvocationTargetException {
        PageInfo<DeviceInfo> pageInfo = deviceInfoService.getListByTempId(tempId);
        return new DataStore<DeviceInfo>(pageInfo.getTotal(), pageInfo.getList());
    }

    @ResponseBody
    @RequestMapping(value = "/delete/{id}")
    public Map<String, String> delete(@PathVariable Long id) {
        Map<String, String> result = new HashMap<String, String>();
        this.adTemplateService.delete(id);
        result.put("result", "SUCCESS");
        return result;
    }

    @RequestMapping(value = "/bindDevice")
    @ResponseBody
    public Map<String, String> bindDevice(Long adTempId, String bindType, String bindId) {
        Map<String, String> result = new HashMap<String, String>();
        List<DeviceInfo> deviceInfos = deviceInfoService.getListByType(bindType, bindId);
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (DeviceInfo d : deviceInfos) {
            Map<String, Object> ins = new HashMap<String, Object>();
            ins.put("deviceId", d.getId());
            ins.put("adTempId", adTempId);
            list.add(ins);
        }
        deviceGroupService.batchInsert("com.uber.uberfamily.dao.AdTemplateDao.bindDevice", list);
        result.put("result", "SUCCESS");
        return result;
    }

    public User getCurrentUser() {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        return (User) session.getAttribute("userinfo");
    }
}
