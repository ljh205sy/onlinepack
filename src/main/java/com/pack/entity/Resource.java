package com.pack.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @Author: liujinhui
 * @Date: 2019/11/9 16:56
 */
@Entity
public class Resource {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "guid")
    private String guid;
    @Column(name = "funcname", nullable = false, length = 255)
    private String funcname;
    @Column(name = "funcname_en", nullable = false, length = 255)
    private String funcnameEn;

    // 资源菜单，子节点关联，自身表关联
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parentid")
    private Resource presource;

    @Column(name = "typeid", nullable = false)
    private int typeid;
    @Column(name = "target", nullable = true, length = 300)
    private String target;
    @Column(name = "url", nullable = true, length = 300)
    private String url;
    @Column(name = "title", nullable = true, length = 300)
    private String title;
    @Column(name = "icon", nullable = true, length = 300)
    private String icon;
    @Column(name = "enable", nullable = true)
    private Boolean enable;
    @Column(name = "sort", nullable = true)
    private Integer sort;

    // 由角色在管理资源
    //
    @ManyToMany(mappedBy = "resources")
    private Set<Role> roles = new HashSet<>();


    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getFuncname() {
        return funcname;
    }

    public void setFuncname(String funcname) {
        this.funcname = funcname;
    }

    public String getFuncnameEn() {
        return funcnameEn;
    }

    public void setFuncnameEn(String funcnameEn) {
        this.funcnameEn = funcnameEn;
    }

    public Resource getPresource() {
        return presource;
    }

    public void setPresource(Resource presource) {
        this.presource = presource;
    }

    public int getTypeid() {
        return typeid;
    }

    public void setTypeid(int typeid) {
        this.typeid = typeid;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
