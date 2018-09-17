package com.xst.controller;

import com.alibaba.fastjson.JSON;
import com.xst.common.annotation.ControllerLog;
import com.xst.common.util.DataTables;
import com.xst.server.UserService;
import com.xst.server.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: UserContrller
 * @类描述： UserContrller 用户控制层
 * @Author: xiaoshitou
 * @Email: 15324399524@163.com
 * @CreateDate： 2018/9/17 17:23
 */

@Controller
@RequestMapping("/user/")
public class UserContrller extends BaseController {

    @Autowired
    private UserService userService = new UserServiceImpl();


    @ControllerLog("进入用户 list 页面")
    @RequestMapping("list")
    public String userList(){
        return "system/user/index";
    }

    @ResponseBody
    @ControllerLog("用户 list 页面，分页显示用户")
    @RequestMapping("showPageUser")
    public String showPageUser(@RequestParam(value="state",required=false)String state, @RequestParam(value="fullname",required=false)String fullname, HttpServletRequest request, HttpServletResponse response){
        state = StringUtils.isEmpty(state) ? "" : (" and state = " + state);
        return JSON.toJSONString(userService.getPageUserList(DataTables.getInstance(request,state,fullname)));
    }
}
