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
@Data
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
    @JoinTable(name = "user_role", joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "guid")}, inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "guid")})
    private Set<Role> roles = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(guid, user.guid) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(guid);
    }
}
