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
@Data
public class Role {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "guid")
    private String guid;

    @Column(name = "rolename", nullable = false, length = 32)
    private String rolename;

    @Column(name = "note", nullable = true, length = 255)
    private String note;

    @Column(name = "createtime", nullable = true, length = 19)
    private String createtime;

    @Column(name = "createuserid", nullable = true, length = 100)
    private String createuserid;

    @Column(name = "systemrole", nullable = true)
    private Boolean systemrole;

    @Column(name = "enable", nullable = false)
    private boolean enable;

    // 由用户来维护关系，对应的是用户类的set集合的属性值.
    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>();

    // 由角色在支持资源的增删改
    /**
     *  在 @JoinTable中name之间表的表名
     *  joinColumns = {@JoinColumn(name = "role_id" 当前类管理关系， 第三方表的外键列
     *  inverseJoinColumns = {@JoinColumn(name = "resource_id" ，关联表的外键列
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "role_resource", joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "guid")}
               , inverseJoinColumns = {@JoinColumn(name = "resource_id", referencedColumnName = "guid")})
    private Set<Resource> resources = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(guid, role.guid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guid);
    }
}
