package com.pack.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: liujinhui
 * @Date: 2019/11/10 19:15
 */
@Data
public class BaseUserVO implements Serializable {
    @ApiModelProperty(value="用户guid",required=true)
    private String guid;
    @ApiModelProperty(value="用户账号",required=true)
    private String account;
    @ApiModelProperty(value="用户名称",required=true)
    private String name;
}
