package com.pack.vo;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

/**
 * @Author: liujinhui
 * @Date: 2019/11/16 21:51
 */
@Data
public class UploadModuleVO implements Serializable {
    private String guid;
    private String moduleId;
    private String moduleVersion;
    private String moduleType;
    private String uploadPath;
    private String filename;
    private Timestamp createTime;
    private String createUser;
    private Set<UploadModuleDependencyVO> uploadDependencys;
    private Set<DemandVO> demands;
}
