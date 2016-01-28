package com.uber.uberfamily.controller;

import com.uber.uberfamily.service.UserService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @Project uber
 * @Package com.uber.uberfamily.controller
 * @Description //TODO
 * @Date 16/1/24
 * @USER saxisuer
 * @COMPANY ENMOTECH
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/user_changePassword")
    @RequiresAuthentication
    public Map<String, String> ChangePassword(String oldpassword, String newpassword) {
        Map<String, String> result = new HashMap<String, String>();
        result.put("resCode", "102004");
        String md5Oldpassword = DigestUtils.md5DigestAsHex(oldpassword.getBytes());

        return result;
    }
}
