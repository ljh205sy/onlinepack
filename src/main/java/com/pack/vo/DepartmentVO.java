package com.pack.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: liujinhui
 * @Date: 2019/11/9 10:39
 */
@Data
@Api(tags = "部门信息")
public class DepartmentVO implements Serializable {

    @ApiModelProperty(value="部门编号")
    private String departmentid;

    @ApiModelProperty(value="部门名称",example="大数据部门",required=true)
    private String name;

    @ApiModelProperty(value="部门别名",required=true)
    private String aliasname;

    @ApiModelProperty(value="部门注释",required=true)
    private String note;

    /**
     *  部门父节点
     */
    @ApiModelProperty(value="部门上级节点",required=true)
    private DepartmentVO pdepartment;

    public String getDepartmentid() {
        return departmentid;
    }

    public void setDepartmentid(String departmentid) {
        this.departmentid = departmentid;
    }
}
