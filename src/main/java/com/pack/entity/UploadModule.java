package com.pack.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @Author: liujinhui
 * @Date: 2019/11/16 21:51
 */
@Entity
@Data
@Table(name = "t_upload_module")
public class UploadModule implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "guid")
    private String guid;

    @Column(name = "module_id", length = 255, nullable = false)
    private String moduleId;

    @Column(name = "module_version", nullable = false, length = 255)
    private String moduleVersion;

    @Column(name = "module_type", nullable = true, length = 255)
    private String moduleType;

    @Column(name = "upload_path", nullable = true, length = 255)
    private String uploadPath;

    @Column(name = "filename", nullable = true, length = 255)
    private String filename;

    @Column(name = "create_time", nullable = true)
    private Timestamp createTime;

    @Column(name = "create_user", nullable = true, length = 255)
    private String createUser;

    @OneToMany(mappedBy = "uploadModule")
    private Set<UploadModuleDependency> uploadDependencys = new HashSet<>();

    // 单向多对多， 一个需求可能由多个模块解决。  一个模块可以解决多个需求。 由模块上传维护需求关系
    // joinColumn， 中间表t_uploadmodule_demand，   外键列upload_module_guid， 外键列：demand_guid
    @ManyToMany
    @JoinTable(name = "t_uploadmodule_demand",
            joinColumns = @JoinColumn(name = "upload_module_guid"),
            inverseJoinColumns = @JoinColumn(name="demand_guid"))
    private Set<Demand> demands = new HashSet<>();

    public UploadModule() {
    }

    public UploadModule(String guid) {
        this.guid = guid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UploadModule that = (UploadModule) o;
        return Objects.equals(guid, that.guid) &&
                Objects.equals(moduleId, that.moduleId) &&
                Objects.equals(moduleVersion, that.moduleVersion) &&
                Objects.equals(moduleType, that.moduleType) &&
                Objects.equals(uploadPath, that.uploadPath) &&
                Objects.equals(filename, that.filename) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(createUser, that.createUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guid, moduleId, moduleVersion, moduleType, uploadPath, filename, createTime, createUser);
    }
}
