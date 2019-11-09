package com.pack.vo;

import com.pack.entity.Department;
import com.pack.entity.Resource;
import com.pack.entity.Role;
import com.pack.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: liujinhui
 * @Date: 2019/11/9 10:39
 */
@Data
@Api(tags = "角色信息")
public class RoleVO implements Serializable {
    @ApiModelProperty(value = "角色guid")
    private String guid;
    @ApiModelProperty(value = "角色名称", required = true)
    private String rolename;
    @ApiModelProperty(value = "角色注释")
    private String note;
    @ApiModelProperty(value = "创建时间")
    private String createtime;
    @ApiModelProperty(value = "创建人")
    private String createuserid;
    @ApiModelProperty(value = "是否是系统角色")
    private Boolean systemrole;
    @ApiModelProperty(value = "角色是否可用")
    private boolean enable;
    @ApiModelProperty(value = "拥有角色用户集合")
    private Set<UserVO> users ;
    @ApiModelProperty(value = "角色拥有资源集合")
    private Set<ResourceVO> resources;

}
