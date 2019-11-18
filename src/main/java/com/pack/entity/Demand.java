package com.pack.entity;

import lombok.Data;

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
@Table(name = "t_demand")
public class Demand {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "demand_id")
    private Integer demandId;
    @Column(name = "demand_name", nullable = true, length = 255)
    private String demandName;
    @Column(name = "description", nullable = true, length = 255)
    private String description;
    @Column(name = "create_time", nullable = true)
    private Timestamp createTime;
    @Column(name = "create_user", nullable = true, length = 255)
    private String createUser;

    // 由需求来维护与项目之间的相关
    // 新增需求时，不会级联项目增加或删除,所以不需要cascade属性
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "t_demand_project",
            joinColumns = {@JoinColumn(name = "demand_id", referencedColumnName = "demand_id")},
            inverseJoinColumns = {@JoinColumn(name = "project_id", referencedColumnName = "project_id")})
    private Set<Project> projects = new HashSet<>();

    // 增加需求时，增加需要的模块及版本, 增加外键约束
    // 需求可以修改模块的依赖及版本，由多的一方来维护
   @OneToMany(mappedBy = "demand")
    private  Set<DemandModule> demandModules = new HashSet<>();

   public Demand(){
    }

    public Demand(Integer demandId){
       this.demandId = demandId;
    }

    public Integer getDemandId() {
        return demandId;
    }

    public void setDemandId(Integer demandId) {
        this.demandId = demandId;
    }

    public String getDemandName() {
        return demandName;
    }

    public void setDemandName(String demandName) {
        this.demandName = demandName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    public Set<DemandModule> getDemandModules() {
        return demandModules;
    }

    public void setDemandModules(Set<DemandModule> demandModules) {
        this.demandModules = demandModules;
    }
}
