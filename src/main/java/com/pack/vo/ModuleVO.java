package com.pack.vo;

import lombok.Data;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * @author liujinhui
 * @date 2019年11月15日23:30:54
 */
@Data
public class ModuleVO {

    private String moduleId;

    // 模块名称
    private String moduleName;

    // 模块版本
    private String moduleVersion;

    // 模块分组
    private String moduleGroup;

    // 模块责任人
    private String responsibilityPerson;

    // 创建时间
    private Timestamp createTime;

    // 创建人
    private String createUser;

    // 排序
    private Integer moduleSort;

    private Set<ModuleVO> dependencyModules ;


}
