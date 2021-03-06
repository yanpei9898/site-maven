package com.xst.server;

import com.xst.common.pojo.AjaxResult;
import com.xst.model.Areas;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @ClassName: AreasService
 * @类描述: AreasService
 * @Author: xiaoshitou
 * @Eamil: 15324399524@163.com
 * @CreateDate: 2018/9/21 14:53
 */
public interface AreasService {

    //查询下级的省市区县
    List<Areas> getAreasByPid(int pid,int level);

    //查询全部省
    List<Areas> getAreasByProvince();

    //根据省份id查询全部市
    List<Areas> getAreasByCityByProvince(int pid);

    //根据市id查询全部区县
    List<Areas> getAreasByCountyByCity(int pid);

    //根据全部省市区县
    List<Areas> getAreas(List<Integer> idlist);

    //保存用户地区数据
    AjaxResult saveAreasZtree(HttpServletRequest request, HttpServletResponse response);
}
