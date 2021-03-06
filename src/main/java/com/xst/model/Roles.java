package com.xst.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * @ClassName: Roles
 * @类描述： Roles 角色实体类
 * @Author: xiaoshitou
 * @Email: 15324399524@163.com
 * @CreateDate： 2018/8/23 9:47
 */
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //角色名称
    private String rolename;

    //描述
    private String description;

    //插入时间
    private Date insertdatetime;

    //修改时间
    private Date operatordatetime;

    //角色等级  0超级管理员  1 管理员  2 地区管理员  3 特殊管理员
    private int level;

    //是否停用标识  0停用  1启用（默认）
    private int state;

    //是否删除  0 删除 1正常
    private int flag;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getInsertdatetime() {
        return insertdatetime;
    }

    public void setInsertdatetime(Date insertdatetime) {
        this.insertdatetime = insertdatetime;
    }

    public Date getOperatordatetime() {
        return operatordatetime;
    }

    public void setOperatordatetime(Date operatordatetime) {
        this.operatordatetime = operatordatetime;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}
