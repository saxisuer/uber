package com.uber.uberfamily.controller;

import com.github.pagehelper.PageInfo;
import com.uber.uberfamily.framework.DataStore;
import com.uber.uberfamily.framework.util.JsonMapper;
import com.uber.uberfamily.model.User;
import com.uber.uberfamily.service.RoleService;
import com.uber.uberfamily.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

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

    @Autowired
    private RoleService roleService;


    private String page = "WEB-INF/jsp/auth/user_list";
    private String EDIT = "WEB-INF/jsp/auth/user_edit";
    private String ADD = "WEB-INF/jsp/auth/user_add";

    @ResponseBody
    @RequestMapping(value = "/user_changePassword")
    @RequiresAuthentication
    public Map<String, String> ChangePassword(String oldpassword, String newpassword) {
        Map<String, String> result = new HashMap<String, String>();
        result.put("resCode", "102004");
        String md5Oldpassword = DigestUtils.md5DigestAsHex(oldpassword.getBytes());
        Map<String, Object> searchMap = new HashMap<>();
        searchMap.put("id", this.getCurrentUser().getId());
        searchMap.put("password", md5Oldpassword);
        User user = userService.getByParameter(searchMap);
        if (null != user) {
            user.setPassword(DigestUtils.md5DigestAsHex(newpassword.getBytes()));
            userService.update(user);
            result.put("resCode", "0");
        }
        return result;
    }


    public User getCurrentUser() {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        return (User) session.getAttribute("userinfo");
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String ListPage() {
        return page;
    }

    @ResponseBody
    @RequestMapping(value = "/loadData", method = RequestMethod.GET)
    public DataStore<User> getPageData(User user, int page, int rows) {
        PageInfo<User> pageInfo = userService.getPage(null, page, rows);
        return new DataStore<>(pageInfo.getTotal(), pageInfo.getList());
    }


    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addPage(User user, Model model) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("userId", 0);
        List<Map<String, Object>> roleList = roleService.getRoleMap(paramMap);
        Iterator<Map<String, Object>> it = roleList.iterator();
        List<String> roleStringList = new LinkedList<String>();
        while (it.hasNext()) {
            Map<String, Object> rm = it.next();
            roleStringList.add(JsonMapper.nonDefaultMapper().toJson(rm));
        }
        user.setRoleList(roleStringList);
        model.addAttribute("user", user);
        return ADD;
    }


    @RequestMapping(value = "/edit")
    public String editPage(User user, Model model) {
        if (user != null && user.getId() != null) {
            user = userService.getById(user.getId());
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("userId", user.getId());
            List<Map<String, Object>> roleList = roleService.getRoleMap(paramMap);
            Iterator<Map<String, Object>> it = roleList.iterator();
            List<String> roleStringList = new LinkedList<String>();
            while (it.hasNext()) {
                Map<String, Object> rm = it.next();
                roleStringList.add(JsonMapper.nonDefaultMapper().toJson(rm));
            }
            user.setRoleList(roleStringList);
            model.addAttribute("user", user);
        }
        return EDIT;
    }


    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Map<String, String> save(User user) {
        Map<String, String> result = new HashMap<>();
        //进入新增
        if (null == user.getId() || StringUtils.isBlank(user.getId().toString())) {
            User searchUser = userService.getUserByName(user.getName());
            if (searchUser != null) {
                result.put("result", "ERROR");
                result.put("str", "账号不能重复！");
                return result;
            }
            user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        }
        userService.save(user);
        result.put("result", "SUCCESS");
        return result;
    }


    @RequestMapping(value = "/delete/{id}")
    @ResponseBody
    public Map<String, String> delete(@PathVariable Long id) {
        Map<String, String> result = new HashMap<>();
        this.userService.delete(id);
        result.put("result", "SUCCESS");
        return result;
    }

    @RequestMapping(value = "/resetPassword")
    @ResponseBody
    public Map<String, String> resetPassword(User user) {
        Map<String, String> result = new HashMap<>();
        user.setPassword(DigestUtils.md5DigestAsHex("12345".getBytes()));
        userService.update(user);
        result.put("result", "SUCCESS");
        return result;
    }
}
