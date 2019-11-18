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
 * @Date: 2019/11/15 8:58
 */
@Entity
@Table(name = "t_module")
@Data
public class Module {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "module_id")
    private String moduleId;

    // 模块名称
    @Column(name = "module_name", nullable = true, length = 255)
    private String moduleName;

    // 模块版本
    @Column(name = "module_version", nullable = true, length = 255)
    private String moduleVersion;

    // 模块分组
    @Column(name = "module_group", nullable = true, length = 255)
    private String moduleGroup;

    // 模块责任人
    @Column(name = "responsibility_person", nullable = true, length = 255)
    private String responsibilityPerson;

    // 创建时间
    @Column(name = "create_time", nullable = true)
    private Timestamp createTime;

    // 创建人
    @Column(name = "create_user", nullable = true, length = 255)
    private String createUser;

    // 排序
    @Column(name = "module_sort", nullable = true, length = 255)
    private Integer moduleSort;

    // 模块依赖其他模块
    @OneToMany
    @JoinTable(name = "t_module_relative",
            joinColumns = @JoinColumn(name = "module_id"),
     inverseJoinColumns = @JoinColumn(name = "module_dependency"))
    private Set<Module> dependencyModules = new HashSet<>();


    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getModuleVersion() {
        return moduleVersion;
    }

    public void setModuleVersion(String moduleVersion) {
        this.moduleVersion = moduleVersion;
    }

    public String getModuleGroup() {
        return moduleGroup;
    }

    public void setModuleGroup(String moduleGroup) {
        this.moduleGroup = moduleGroup;
    }

    public String getResponsibilityPerson() {
        return responsibilityPerson;
    }

    public void setResponsibilityPerson(String responsibilityPerson) {
        this.responsibilityPerson = responsibilityPerson;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Integer getModuleSort() {
        return moduleSort;
    }

    public void setModuleSort(Integer moduleSort) {
        this.moduleSort = moduleSort;
    }

    public Set<Module> getDependencyModules() {
        return dependencyModules;
    }

    public void setDependencyModules(Set<Module> dependencyModules) {
        this.dependencyModules = dependencyModules;
    }
}
