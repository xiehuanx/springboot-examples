package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XiaoBingBy on 2017/9/12.
 */
public class TreeNode implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;
    /**
     * 上级ID
     */
    private Long pid;
    /**
     * 权限名
     */
    private String name;
    /**
     * 类型 0、菜单 1、功能
     */
    private Integer type;
    /**
     * 状态 0、禁用 1、正常
     */
    private Integer state;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 地址
     */
    private String url;
    /**
     * 图标
     */
    private String icon;

    /**
     * 子菜单
     */
    private List<TreeNode> children = new ArrayList<TreeNode>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }

}
