package com.pack.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @Author: liujinhui
 * @Date: 2019/11/15 8:58
 */
@Entity
@Table(name = "t_project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private Integer projectId;
    @Column(name = "project_name", length = 255)
    private String projectName;
    @Column(name = "project_profile", nullable = true, length = 255)
    private String projectProfile;
    @Column(name = "project_status", nullable = true)
    private Integer projectStatus;
    @Column(name = "version", nullable = true, length = 255)
    private String version;
    @Column(name = "description", nullable = true, length = 255)
    private String description;

    @ManyToMany(mappedBy = "projects")
    private Set<Demand> demands = new HashSet<>();


    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectProfile() {
        return projectProfile;
    }

    public void setProjectProfile(String projectProfile) {
        this.projectProfile = projectProfile;
    }

    public Integer getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(Integer projectStatus) {
        this.projectStatus = projectStatus;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Demand> getDemands() {
        return demands;
    }

    public void setDemands(Set<Demand> demands) {
        this.demands = demands;
    }
}
