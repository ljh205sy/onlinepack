package com.pack.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

/**
 * @Author: liujinhui
 * @Date: 2019/11/16 21:51
 * @desc  上传模块需要哪些模块依赖及版本
 */
@Entity
@Data
@Table(name = "t_upload_dependency")
public class UploadModuleDependency {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "uuid")
    private String uuid;
    @Column(name = "relative_module_id", nullable = false, length = 255)
    private String relativeModuleId;
    @Column(name = "relative_module_version", nullable = true, length = 255)
    private String relativeModuleVersion;
    @ManyToOne
    @JoinColumn(name = "upload_module_id")
    private UploadModule uploadModule;

    public UploadModuleDependency() {

    }

    public UploadModuleDependency(UploadModule uploadModule) {
        this.uploadModule = uploadModule;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UploadModuleDependency that = (UploadModuleDependency) o;
        return Objects.equals(uuid, that.uuid) &&
                Objects.equals(relativeModuleId, that.relativeModuleId) &&
                Objects.equals(relativeModuleVersion, that.relativeModuleVersion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, relativeModuleId, relativeModuleVersion);
    }
}
