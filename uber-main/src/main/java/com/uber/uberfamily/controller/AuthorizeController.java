package com.uber.uberfamily.controller;

import com.uber.uberfamily.model.Permission;
import com.uber.uberfamily.model.User;
import com.uber.uberfamily.service.PermissionService;
import com.uber.uberfamily.service.UserService;
import com.uber.uberfamily.vo.MenuGroup;
import com.uber.uberfamily.vo.Menus;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Project uber
 * @Package com.uber.uberfamily.controller
 * @Description //TODO
 * @Date 16/1/20
 * @USER saxisuer
 * @COMPANY ENMOTECH
 */
@Controller
@RequestMapping(value = "/auth")
public class AuthorizeController {

    private static final Logger logger = Logger.getLogger(AuthorizeController.class);
    private static final String SUCCESS = "index";
    private static final String INPUT = "login";
    @Autowired
    private PermissionService permissionService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(User user, HttpServletRequest request) {
        String loginMsg;
        try {
            if (user != null) {
                UsernamePasswordToken token = new UsernamePasswordToken(
                        user.getName(), user.getPassword());
                Subject subject = SecurityUtils.getSubject();
                subject.login(token);
                user = userService.getUserByName(user.getName());
                Session session = subject.getSession();
                session.setAttribute("userinfo", user);
            } else {
                Subject subject = SecurityUtils.getSubject();
                Session session = subject.getSession();
                Object o = session.getAttribute("userinfo");
                if (o != null) {
                    return SUCCESS;
                } else {
                    return INPUT;
                }
            }
        } catch (UnknownAccountException e) {
            logger.warn("登陆失败，用户名不存在！", e);
            loginMsg = "登陆失败，用户名密码错误";
            request.setAttribute("loginMsg", loginMsg);
            return INPUT;
        } catch (IncorrectCredentialsException e) {
            logger.warn("登陆失败，密码错误！", e);
            loginMsg = "登陆失败，用户名密码错误";
            request.setAttribute("loginMsg", loginMsg);
            return INPUT;
        } catch (DisabledAccountException e) {
            logger.warn("登陆失败，账号被禁用！", e);
            loginMsg = "登陆失败，账号被禁用";
            request.setAttribute("loginMsg", loginMsg);
            return INPUT;
        } catch (Exception e) {
            logger.warn("登陆失败，未知错误！", e);
            loginMsg = "登陆失败，未知错误";
            request.setAttribute("loginMsg", loginMsg);
            return INPUT;
        }
        return SUCCESS;
    }

    /**
     * 登出
     *
     * @return
     */
    @RequestMapping(value = "/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return INPUT;
    }

    @RequestMapping(value = "/errors")
    public String showError() throws Exception {
        throw new Exception("aaaaa");
    }

    @RequiresAuthentication
    @ResponseBody
    @RequestMapping(value = "/authMenus", method = RequestMethod.GET)
    public Map<String, List<MenuGroup>> authMenus() {
        Subject subject = SecurityUtils.getSubject();
        List<Permission> moduleGroups = permissionService.getPermission(null);
        List<MenuGroup> groupList = new ArrayList<MenuGroup>();
        for (Permission g : moduleGroups) {
            if (subject.isPermitted(g.getCode())) {
                MenuGroup mg = new MenuGroup();
                mg.setMenuid(Long.toString(g.getId()));
                mg.setIcon(g.getIco());
                mg.setMenuname(g.getName());
                List<Permission> menuSet = g.getPermissionSet();
                List<Menus> mlist = new ArrayList<Menus>();
                for (Permission obj : menuSet) {
                    if (subject.isPermitted(obj.getCode())) {
                        Menus m = new Menus();
                        m.setMenuname(obj.getName());
                        m.setIcon(obj.getIco());
                        m.setUrl(obj.getUrl());
                        mlist.add(m);
                    }
                }
                mg.setMenus(mlist);
                groupList.add(mg);
            }
        }
        Map<String, List<MenuGroup>> menus = new HashMap<String, List<MenuGroup>>();
        menus.put("menus", groupList);
        return menus;
    }


    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Map<String, String> showException(Exception ex) {
        Map<String, String> result = new HashMap<String, String>();
        String errorMessage = ExceptionUtils.getRootCauseMessage(ex);
        result.put("result", "ERROR");
        result.put("str", errorMessage);
        return result;
    }


}
