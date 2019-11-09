package com.pack.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @Author: liujinhui
 * @Date: 2019/11/9 9:52
 */

@Entity
@Data
@Table(name = "department")
public class Department {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String departmentid;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "aliasname", nullable = true, length = 255)
    private String aliasname;

    @Column(name = "note", nullable = true, length = 255)
    private String note;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parentid")
    private Department pdepartment;

    // joincloum对象，由多的一方维护关系,凡是双向关联，mapped必设
    // 由用户去维护部门关系，mappedBy出现在一的一方，由多的一方去维护关系
    // mappedBy以指明在owning-side是哪一个属性持有该实体的关联数据。@OneToMany(mappedBy = "departments")， 所以是User类中属性，departments字段关联此实体的
    // mappedBy指拥有关系（持有关联数据的一方成为owning-side，即外键字段所在的实体）是哪个属性持有的关联数据
    @OneToMany(mappedBy = "departments")
    private Set<User> users = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return Objects.equals(departmentid, that.departmentid) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(departmentid);
    }

    @Override
    public String toString() {
        return "Department{" +
                "departmentid='" + departmentid + '\'' +
                ", name='" + name + '\'' +
                ", aliasname='" + aliasname + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}


