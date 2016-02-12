package com.uber.uberfamily.controller;

import com.github.pagehelper.PageInfo;
import com.uber.uberfamily.framework.DataStore;
import com.uber.uberfamily.framework.util.JsonMapper;
import com.uber.uberfamily.model.Role;
import com.uber.uberfamily.service.PermissionService;
import com.uber.uberfamily.service.RoleService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @Project uber
 * @Package com.uber.uberfamily.controller
 * @Description //TODO
 * @Date 16/2/9
 * @USER saxisuer
 * @COMPANY ENMOTECH
 */
@Controller
@RequestMapping(value = "/role")
public class RoleController {

    private static final Logger logger = Logger.getLogger(RoleController.class);

    private String LIST = "WEB-INF/jsp/auth/role_list";

    private String ADD = "WEB-INF/jsp/auth/role_add";
    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listPage() {
        return LIST;
    }

    @ResponseBody
    @RequestMapping(value = "/loadData")
    public DataStore<Role> loadData(int page, int rows, Role role) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        PageInfo<Role> pageInfo = roleService.getPage(BeanUtils.describe(role), page, rows);
        return new DataStore<>(pageInfo.getTotal(), pageInfo.getList());
    }


    @RequestMapping(value = "/add")
    public ModelAndView addPage(Role role) {
        List<Map<String, Object>> roleList = permissionService.getPermissionMap(null);
        Iterator<Map<String, Object>> it = roleList.iterator();
        List<String> permissiontringList = new LinkedList<String>();
        while (it.hasNext()) {
            Map<String, Object> rm = it.next();
            permissiontringList.add(JsonMapper.nonDefaultMapper().toJson(rm));
        }
        role.setPermissionList(permissiontringList);
        return new ModelAndView(ADD, "role", role);
    }
}
