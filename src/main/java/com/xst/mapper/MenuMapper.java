package com.xst.mapper;

import com.xst.model.Menu;
import org.apache.ibatis.annotations.Param;

import javax.annotation.security.PermitAll;
import java.util.List;

/**
 * @ClassName: MenuMapper
 * @类描述： MenuMapper
 * @Author: xiaoshitou
 * @Email: 15324399524@163.com
 * @CreateDate： 2018/8/28 13:30
 */
public interface MenuMapper {

    //登录成功查询菜单
    List<Menu> selectLoginMenus();

    //菜单分页显示
    List<Menu> getPageMenuList(@Param("search") String search, @Param("subSQL") String subSQL);

    //改变菜单状态
    int changeMenuState(@Param("id") int id,@Param("state") int state);

    //删除菜单
    int deleteMenu(@Param("idlist")List<Integer> idlist);

    //判断菜单下面是否存在子菜单
    int selectCountMenu(@Param("idlist")List<Integer> idlist);

    //查询所有一级菜单
    List<Menu> selectOneMenu();

    //插入菜单
    int insertMenu(Menu menu);

    //根据菜单名和地址查询
    Menu selectMenuByMenuNameOrUrl(@Param("menuname") String menuname,@Param("url") String url,@Param("id") String id);

    //根据 id 查询菜单
    Menu getMenuById(@Param("id") int id);

    //更新菜单
    int updateMenu(Menu menu);

    //根据角色id 获取菜单权限
    List<Menu> getMenuListByRoleId(@Param("roleid") int roleid);

    //根据菜单获取菜单 主页
    List<Menu> getMenuListByRoleIdByIndex(@Param("roleid") int roleid);

    //根据上级编号查询最大编号
    String selectMaxOpcopeByPid(@Param("pid") int pid);
}
