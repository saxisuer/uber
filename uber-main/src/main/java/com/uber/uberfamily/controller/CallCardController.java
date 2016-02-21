package com.uber.uberfamily.controller;

import com.github.pagehelper.PageInfo;
import com.uber.uberfamily.framework.DataStore;
import com.uber.uberfamily.model.CallCard;
import com.uber.uberfamily.model.User;
import com.uber.uberfamily.service.CallCardService;
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
 * @Date 16/2/21
 * @USER saxisuer
 * @COMPANY ENMOTECH
 */
@Controller
@RequestMapping(value = "/callcard")
public class CallCardController {

    private static final Logger logger = Logger.getLogger(CallCardController.class);

    @Autowired
    private CallCardService callCardService;

    private String CALLCARDLIST = "WEB-INF/jsp/card/callCard_list";


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView callCardList() {
        return new ModelAndView(CALLCARDLIST);
    }


    @ResponseBody
    @RequestMapping(value = "/loadData", method = RequestMethod.GET)
    public DataStore<CallCard> loadData(int page, int rows, CallCard callCard) throws IllegalAccessException, NoSuchMethodException,
            InvocationTargetException {
        PageInfo<CallCard> pageInfo = callCardService.getPage(BeanUtils.describe(callCard), page, rows);
        return new DataStore<CallCard>(pageInfo.getTotal(), pageInfo.getList());
    }


    @RequestMapping(value = "/setCardLoss")
    public Map<String, Object> setCardLoss(CallCard callCard) {
        Map<String, Object> result = new HashMap<String, Object>();
        String operatorAcc = getCurrentUser().getName();
        Map<String, String> re = callCardService.setCardLoss(callCard.getCardNoLogical(), operatorAcc);
        result.put("result", re);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/setDamaged")
    public Map<String, Object> setDamaged(CallCard callCard) {
        Map<String, Object> result = new HashMap<String, Object>();
        String operatorAcc = this.getCurrentUser().getName();
        Map<String, String> l = callCardService.setDamaged(callCard.getCardNoLogical(), operatorAcc);
        result.put("result", l);
        return result;
    }


    @ResponseBody
    @RequestMapping(value = "/setReturnCard")
    public Map<String, Object> setReturnCard(CallCard callCard) {
        Map<String, Object> result = new HashMap<String, Object>();
        String operatorAcc = this.getCurrentUser().getName();
        Map<String, String> l = callCardService.setReturnCard(callCard.getCardNoLogical(), operatorAcc);
        result.put("result", l);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/setExceptionLock")
    public Map<String, Object> setExceptionLock(CallCard callCard) {
        Map<String, Object> result = new HashMap<String, Object>();
        String operatorAcc = this.getCurrentUser().getName();
        Map<String, String> l = callCardService.setExceptionLock(callCard.getCardNoLogical(), operatorAcc);
        result.put("result", l);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/setExceptionUnLock")
    public Map<String, Object> setExceptionUnLock(CallCard callCard) {
        Map<String, Object> result = new HashMap<String, Object>();
        String operatorAcc = this.getCurrentUser().getName();
        Map<String, String> l = callCardService.setExceptionUnLock(callCard.getCardNoLogical(), operatorAcc);
        result.put("result", l);
        return result;
    }


    private User getCurrentUser() {
        return ((User) SecurityUtils.getSubject().getSession().getAttribute("userinfo"));
    }
}
