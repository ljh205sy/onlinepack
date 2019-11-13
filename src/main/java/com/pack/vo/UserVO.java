package com.pack.vo;

import com.pack.entity.Department;
import com.pack.entity.Role;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: liujinhui
 * @Date: 2019/11/9 10:39
 */
@Data
@Api(tags = "用户信息")
public class UserVO extends  BaseUserVO implements Serializable {
    @ApiModelProperty(value="用户guid")
    private String guid;
    @ApiModelProperty(value="用户账号",required=true)
    private String account;
    @ApiModelProperty(value="用户密码",required=true)
    private String password;
    @ApiModelProperty(value="用户名称",required=true)
    private String name;
    @ApiModelProperty(value="电话")
    private String telephone;
    @ApiModelProperty(value="邮件")
    private String email;
    @ApiModelProperty(value="传真")
    private String fax;
    @ApiModelProperty(value="是否可用")
    private Boolean disabled;
    @ApiModelProperty(value="最后登录时间")
    private Timestamp lastlogintime;
    @ApiModelProperty(value="登录次数")
    private Integer logincount;
    @ApiModelProperty(value="是否开启三权")
    private Integer threepower;
    @ApiModelProperty(value="手机号码")
    private String mobile;
    @ApiModelProperty(value="创建时间")
    private String createtime;
    @ApiModelProperty(value="创建用户")
    private String createuserid;
    @ApiModelProperty(value="注释")
    private String note;
    private String identities;
    private String code;
    @ApiModelProperty(value = "用户所在部门", required = false)
    private Department department ;
    @ApiModelProperty(value = "用户所拥有角色", required = false)
    private Set<Role> roles ;

}
