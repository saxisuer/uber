package com.uber.uberfamily.controller;

import com.github.pagehelper.PageInfo;
import com.uber.uberfamily.framework.DataStore;
import com.uber.uberfamily.model.AdTemplate;
import com.uber.uberfamily.model.User;
import com.uber.uberfamily.service.AdTemplateService;
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
import java.util.HashMap;
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


    @ResponseBody
    @RequestMapping(value = "/delete/{id}")
    public Map<String, String> delete(@PathVariable Long id) {
        Map<String, String> result = new HashMap<String, String>();
        this.adTemplateService.delete(id);
        result.put("result", "SUCCESS");
        return result;
    }

    public User getCurrentUser() {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        return (User) session.getAttribute("userinfo");
    }
}
