package com.pack.vo;

import com.pack.entity.DemandModule;
import com.pack.entity.Department;
import com.pack.entity.Project;
import com.pack.entity.Role;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
@Api(tags = "需求信息")
public class DemandVO implements Serializable {

    @ApiModelProperty(value = "需求id")
    private Integer demandId;
    @ApiModelProperty(value = "需求名称")
    private String demandName;
    @ApiModelProperty(value = "需求描述")
    private String description;
    @ApiModelProperty(value = "需求创建时间")
    private Timestamp createTime;

    @ApiModelProperty(value = "创建者")
    private String createUser;

    @ApiModelProperty(value = "需求所属项目")
    private Set<ProjectVO> projects;

    @ApiModelProperty(value = "需求关联模块及版本信息")
    private Set<DemandModuleVO> demandModules;
}
