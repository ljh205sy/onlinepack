package com.pack.vo;

import com.pack.entity.Demand;
import com.pack.entity.Department;
import com.pack.entity.Role;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: liujinhui
 * @Date: 2019/11/9 10:39
 */
@Data
@Api(tags = "项目信息")
public class ProjectVO implements Serializable {
    @ApiModelProperty(value = "项目id")
    private Integer projectId;
    @ApiModelProperty(value = "项目名称", required = true)
    private String projectName;
    @ApiModelProperty(value = "项目数据库标识", required = true)
    private String projectProfile;
    @ApiModelProperty(value = "项目状态", required = true)
    private Integer projectStatus;
    @ApiModelProperty(value = "项目版本", required = true)
    private String version;
    @ApiModelProperty(value = "项目描述", required = true)
    private String description;
    @ApiModelProperty(value = "项目需求", required = true)
    private Set<Demand> demands;

}
