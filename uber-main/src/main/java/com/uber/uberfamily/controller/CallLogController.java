package com.uber.uberfamily.controller;

import com.github.pagehelper.PageInfo;
import com.uber.uberfamily.framework.DataStore;
import com.uber.uberfamily.service.CallLogService;
import com.uber.uberfamily.vo.CallLogDTO;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.InvocationTargetException;

/**
 * @Project uber
 * @Package com.uber.uberfamily.controller
 * @Description //TODO
 * @Date 16/2/24
 * @USER saxisuer
 * @COMPANY ENMOTECH
 */
@Controller
@RequestMapping(value = "/calllog")
public class CallLogController {

    @Autowired
    private CallLogService callLogService;

    private static String LIST = "WEB-INF/jsp/callLog/callLog_list";

    @RequestMapping(value = "/list")
    public ModelAndView getList() {
        return new ModelAndView(LIST);
    }

    @ResponseBody
    @RequestMapping(value = "/loadData")
    public DataStore<CallLogDTO> getPage(CallLogDTO callLogDTO, int page, int rows) throws IllegalAccessException, NoSuchMethodException,
            InvocationTargetException {
        PageInfo<CallLogDTO> callLogDTOPageInfo = callLogService.getPage(page, rows, BeanUtils.describe(callLogDTO));
        return new DataStore<CallLogDTO>(callLogDTOPageInfo.getTotal(), callLogDTOPageInfo.getList());
    }

}

