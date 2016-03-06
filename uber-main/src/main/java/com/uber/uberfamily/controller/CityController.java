package com.uber.uberfamily.controller;

import com.github.pagehelper.PageInfo;
import com.uber.uberfamily.framework.DataStore;
import com.uber.uberfamily.model.City;
import com.uber.uberfamily.service.CityService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
 * @Description //cityController
 * @Date 16/3/6
 * @USER saxisuer
 * @COMPANY ENMOTECH
 */
@Controller
@RequestMapping(value = "/city")
public class CityController {

    private static final Logger logger = Logger.getLogger(CityController.class);
    private static String LIST = "WEB-INF/jsp/city/city_list";
    private static String ADD = "WEB-INF/jsp/city/city_add";
    private static String EDIT = "WEB-INF/jsp/city/city_edit";

    @Autowired
    private CityService cityService;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addPage() {
        return new ModelAndView(ADD);
    }


    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editPage(@PathVariable Long id) {
        City city = cityService.getById(id);
        return new ModelAndView(EDIT, "city", city);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView listPage() {
        return new ModelAndView(LIST);
    }


    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Map<String, String> save(City city) {
        Map<String, String> result = new HashMap<String, String>();
        if (null == city.getId()) {
            cityService.create(city);
        } else {
            cityService.update(city);
        }
        result.put("result", "SUCCESS");
        return result;
    }


    @ResponseBody
    @RequestMapping(value = "/loadData", method = RequestMethod.POST)
    public DataStore<City> loadData(int page, int rows, City city) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Map<String, Object> searchMap = BeanUtils.describe(city);
        PageInfo<City> pageInfo = cityService.getPage(searchMap, page, rows);
        return new DataStore<City>(pageInfo.getTotal(), pageInfo.getList());
    }


    @ResponseBody
    @RequestMapping(value = "/delete/{id}")
    public Map<String, String> deleteById(@PathVariable Long id) {
        Map<String, String> result = new HashMap<String, String>();
        this.cityService.delete(id);
        result.put("result", "SUCCESS");
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/getCityListForCombo", method = RequestMethod.GET)
    public List<Map<String, Object>> getCityListForCombo() {
        return this.cityService.getCityListForCombo();
    }
}
