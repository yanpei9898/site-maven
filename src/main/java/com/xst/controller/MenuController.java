package com.xst.controller;

import com.alibaba.fastjson.JSON;
import com.xst.common.annotation.ControllerLog;
import com.xst.common.pojo.AjaxResult;
import com.xst.common.util.DataTables;
import com.xst.model.Menu;
import com.xst.server.MenuService;
import com.xst.server.impl.MenuServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @ClassName: MenuController
 * @类描述: MenuController  菜单控制层
 * @Author: xiaoshitou
 * @Eamil: 15324399524@163.com
 * @CreateDate: 2018/8/24 21:02
 */

@Controller
@RequestMapping("/menu/")
public class MenuController extends BaseController {

    @Autowired
    private MenuService menuService = new MenuServiceImpl();


    @RequestMapping("list")
    @ControllerLog("进入菜单 list 页面")
    public String menuList(){
        return "system/menu/index";
    }

    @ResponseBody
    @ControllerLog("菜单 list 页面，分页显示菜单")
    @RequestMapping("showPageMenu")
    public String showPageMenu(@RequestParam(value="state",required=false)String state,@RequestParam(value="menuname",required=false)String menuname, HttpServletRequest request, HttpServletResponse response){
        state = StringUtils.isEmpty(state) ? "" : ("a.state = " + state);
        menuname = StringUtils.isEmpty(menuname) ? "" : ("b.menuname = '" + menuname + "'");
        return JSON.toJSONString(menuService.getPageMenuList(DataTables.getInstance(request,state,menuname)));
    }

    @ResponseBody
    @ControllerLog("改变菜单状态")
    @RequestMapping("menuState")
    public AjaxResult changeMenuState(HttpServletRequest request, HttpServletResponse response){
        return menuService.changeMenuState(request,response);
    }

    @RequestMapping("add")
    @ControllerLog("进入菜单 add 页面")
    public String addMeun(HttpServletRequest request, HttpServletResponse response){
        request.setAttribute("menuList",menuService.selectOneMenu());
        return "system/menu/add";
    }

    @ResponseBody
    @RequestMapping("addUpdate")
    @ControllerLog("添加菜单")
    public AjaxResult addUpdate(Menu menu){
        return menuService.addUpdate(menu);
    }

    @RequestMapping("edit")
    @ControllerLog("进入菜单 edit 页面")
    public String editMeun(HttpServletRequest request, HttpServletResponse response){
        request.setAttribute("menuList",menuService.selectOneMenu());
        request.setAttribute("menu",menuService.getMenuById(request,response));
        return "system/menu/edit";
    }

    @ResponseBody
    @RequestMapping("editUpdate")
    @ControllerLog("修改菜单")
    public AjaxResult editUpdate(Menu menu){
        return menuService.editUpdate(menu);
    }

    @ResponseBody
    @ControllerLog("删除菜单")
    @RequestMapping("/delete")
    public AjaxResult deleteMenu(@RequestParam("idlist[]") List<Integer> idlist){
        return menuService.deleteMenu(idlist);
    }

    @ControllerLog("菜单名重复验证")
    @RequestMapping("menuname/validate")
    public void menuNameValidate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        menuService.menuNameValidate(request,response);
    }

    @ResponseBody
    @ControllerLog("菜单跳转地址重复验证")
    @RequestMapping("url/validate")
    public void urlValidate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        menuService.urlValidate(request,response);
    }


}
