package com.uber.uberfamily.controller;

import com.github.pagehelper.PageInfo;
import com.uber.uberfamily.framework.DataStore;
import com.uber.uberfamily.model.BaseApplicant;
import com.uber.uberfamily.model.User;
import com.uber.uberfamily.service.BaseApplicantService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Project uber
 * @Package com.uber.uberfamily.controller
 * @Description //TODO
 * @Date 16/2/18
 * @USER saxisuer
 * @COMPANY ENMOTECH
 */
@Controller
@RequestMapping(value = "/applicant")
public class BaseApplicantController {
    private static final Logger logger = Logger.getLogger(BaseApplicantController.class);


    @Autowired
    private BaseApplicantService baseApplicantService;

    private static String APPLICANT_LIST = "WEB-INF/jsp/card/applicant_list";
    private static String DELIVERY_LIST = "WEB-INF/jsp/card/delivery_list";
    private static String HISTORY_LIST = "WEB-INF/jsp/card/history_list";


    @RequestMapping(value = "/loadApplicantList", method = RequestMethod.GET)
    public ModelAndView loadApplicantList() {
        return new ModelAndView(APPLICANT_LIST);
    }

    @RequestMapping(value = "/loadDeliveryList", method = RequestMethod.GET)
    public ModelAndView loadDeliveryList() {
        return new ModelAndView(DELIVERY_LIST);
    }

    @RequestMapping(value = "/loadHistoryList", method = RequestMethod.GET)
    public ModelAndView loadHistoryList() {
        return new ModelAndView(HISTORY_LIST);
    }


    @ResponseBody
    @RequestMapping(value = "/loadApplicantData", method = RequestMethod.GET)
    public DataStore<BaseApplicant> loadApplicantData(BaseApplicant baseApplicant, int page, int rows) throws IllegalAccessException,
            NoSuchMethodException, InvocationTargetException {
        PageInfo<BaseApplicant> pageInfo = baseApplicantService.loadApplicantData(BeanUtils.describe(baseApplicant), page, rows);
        return new DataStore<BaseApplicant>(pageInfo.getTotal(), pageInfo.getList());
    }


    @ResponseBody
    @RequestMapping(value = "/loadHistoryData", method = RequestMethod.GET)
    public DataStore<BaseApplicant> loadHistoryData(BaseApplicant baseApplicant, int page, int rows) throws IllegalAccessException,
            NoSuchMethodException, InvocationTargetException {
        PageInfo<BaseApplicant> pageInfo = baseApplicantService.loadHistoryData(BeanUtils.describe(baseApplicant), page, rows);
        return new DataStore<BaseApplicant>(pageInfo.getTotal(), pageInfo.getList());
    }

    @ResponseBody
    @RequestMapping(value = "/loadDeliveryData", method = RequestMethod.GET)
    public DataStore<BaseApplicant> loadDeliveryData(BaseApplicant baseApplicant, int page, int rows) throws IllegalAccessException,
            NoSuchMethodException, InvocationTargetException {
        PageInfo<BaseApplicant> pageInfo = baseApplicantService.loadDeliveryData(BeanUtils.describe(baseApplicant), page, rows);
        return new DataStore<BaseApplicant>(pageInfo.getTotal(), pageInfo.getList());
    }


    @ResponseBody
    @RequestMapping(value = "/issueThisCard", method = RequestMethod.POST)
    public Map<String, String> issueThisCard(String userAccs) {
        Map<String, String> resultMap = new HashMap<String, String>();
        String[] userAccounts = userAccs.split(",");
        for (String id : userAccounts) {
            try {
                baseApplicantService.issueThisCard(id, ((User) SecurityUtils.getSubject().getSession().getAttribute("userinfo")).getName());
            } catch (Exception e) {
                resultMap.put("result", id + ":" + e.getMessage());
                return resultMap;
            }
        }
        return resultMap;
    }

}
