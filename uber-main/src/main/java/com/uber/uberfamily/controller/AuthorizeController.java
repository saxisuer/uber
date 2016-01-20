package com.uber.uberfamily.controller;

import com.uber.uberfamily.model.Permission;
import com.uber.uberfamily.service.PermissionService;
import com.uber.uberfamily.vo.MenuGroup;
import com.uber.uberfamily.vo.Menus;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

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

    @Autowired
    private PermissionService permissionService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login() {
        return null;
    }

    @ResponseBody
    public Map<String, List<MenuGroup>> authMenus() {
        Subject subject = SecurityUtils.getSubject();
        List<Permission> moduleGroups = permissionService.getModuleGroups();
        List<MenuGroup> groupList = new ArrayList<MenuGroup>();
        for (Permission g : moduleGroups) {
            if (subject.isPermitted(g.getCode())) {
                MenuGroup mg = new MenuGroup();
                mg.setMenuid(Long.toString(g.getId()));
                mg.setIcon(g.getIco());
                mg.setMenuname(g.getName());
                Set<Permission> menuSet = permissionService.getChildren(g.getId());
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
}
