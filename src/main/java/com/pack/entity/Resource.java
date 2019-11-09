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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resource resource = (Resource) o;
        return Objects.equals(guid, resource.guid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guid);
    }
}
