package com.pack.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @Author: liujinhui
 * @Date: 2019/11/9 16:56
 */
@Entity
public class User {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "guid")
    private String guid;

    @Column(name = "account", nullable = false, length = 32)
    private String account;
    @Column(name = "password", nullable = false, length = 100)
    private String password;
    @Column(name = "name", nullable = false, length = 32)
    private String name;
    @Column(name = "telephone", nullable = true, length = 32)
    private String telephone;
    @Column(name = "email", nullable = true, length = 32)
    private String email;
    @Column(name = "fax", nullable = true, length = 60)
    private String fax;
    @Column(name = "disabled", nullable = true)
    private Boolean disabled;
    @Column(name = "lastlogintime", nullable = true)
    private Timestamp lastlogintime;
    @Column(name = "logincount", nullable = true)
    private Integer logincount;
    @Column(name = "threepower", nullable = true)
    private Integer threepower;
    @Column(name = "mobile", nullable = true, length = 30)
    private String mobile;
    @Column(name = "createtime", nullable = true, length = 30)
    private String createtime;
    @Column(name = "createuserid", nullable = true, length = 50)
    private String createuserid;
    @Column(name = "note", nullable = true, length = 255)
    private String note;
    @Column(name = "identities", nullable = true, length = 255)
    private String identities;
    @Column(name = "code", nullable = true, length = 255)
    private String code;

    // 在user表中外键列， 关联部门表中的主键类。 name指的外键列
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "departmentid")
    private Department departments;
    /**
     * name代表的是第三方表,    joincolumn 当前表在第三方表中的外键列。 由谁来维护，用户维护角色
     * joinColumns = {@JoinColumn(name="user_id")}), 新建外键(第三方表中的外键列)， user维护关系
     * 栏位 user_id， 被参考表user ， 参考栏位user_id
     * <p>
     * 定义连接表中名为userid的外键列，该外键列参照当前实体对应的主键列
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "guid")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "guid")})
    private Set<Role> roles = new HashSet<>();


    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public Timestamp getLastlogintime() {
        return lastlogintime;
    }

    public void setLastlogintime(Timestamp lastlogintime) {
        this.lastlogintime = lastlogintime;
    }

    public Integer getLogincount() {
        return logincount;
    }

    public void setLogincount(Integer logincount) {
        this.logincount = logincount;
    }

    public Integer getThreepower() {
        return threepower;
    }

    public void setThreepower(Integer threepower) {
        this.threepower = threepower;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getCreateuserid() {
        return createuserid;
    }

    public void setCreateuserid(String createuserid) {
        this.createuserid = createuserid;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getIdentities() {
        return identities;
    }

    public void setIdentities(String identities) {
        this.identities = identities;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Department getDepartments() {
        return departments;
    }

    public void setDepartments(Department departments) {
        this.departments = departments;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
