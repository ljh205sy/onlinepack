package com.pack.controller;

import com.pack.entity.Department;
import com.pack.service.impl.DepartmentService;
import com.pack.uilts.Result;
import com.pack.uilts.ResultCodeEnum;
import com.pack.uilts.ResultUtil;
import com.pack.vo.DepartmentVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * @Author: liujinhui
 * @Date: 2019/11/8 22:51
 */
@RestController
@RequestMapping("/v1/depetment")
@Api(tags = "部门管理")
public class DepartmentController {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private DepartmentService departmentService;

    /**
     * 部门增加
     */
    @ApiOperation(value = "部门新增", notes = "部门新增", hidden = false)
    @PostMapping
    public Result<Boolean> save(@RequestBody(required = true) DepartmentVO departmentVO) {
        Department department = new Department();

        // 父节点为空
        Optional<DepartmentVO> result = Optional.ofNullable(departmentVO.getPdepartment())
                .filter(u -> StringUtils.isNotEmpty(u.getDepartmentid()));
        if (result.isPresent()) {
            String pid = departmentVO.getPdepartment().getDepartmentid();
            Department pDept = departmentService.getOne(pid);
            department.setPdepartment(pDept);
        }

        BeanUtils.copyProperties(departmentVO, department);
        departmentService.save(department);
        return ResultUtil.success(true);
    }

    /**
     * 部分修改
     *
     * @param departmentVO
     * @param errors
     * @return
     */
    @PutMapping
    @ApiOperation(value = "部门修改", notes = "部门修改", hidden = false)
    public Result<Boolean> update(@Valid @RequestBody DepartmentVO departmentVO, BindingResult errors) {
        return this.save(departmentVO);
    }

    /**
     * 存在外键引用，删除失败的情况
     */
    @DeleteMapping("/{departmentid}")
    @ApiOperation(value = "部门删除", notes = "部门删除", hidden = false)
    public Result<Boolean> delete(@PathVariable @ApiParam("部门id") String departmentid) {
        try {
            departmentService.delete(departmentid);
        } catch (Exception e) {
            return ResultUtil.error(ResultCodeEnum.UNKNOW_FAILED);
        }
        return ResultUtil.success(true);
    }

    /**
     * 根据id查询Department， 如果不存在此部门，返回一个Department对象，但是Department的所有值都为空
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "使用ID查询部门")
    @GetMapping("/{departmentid}")
    public Result<DepartmentVO> getDepetmentById(@ApiParam("部门id") @PathVariable("departmentid") String id) {
        Department department = departmentService.findById(id);
        DepartmentVO departmentVO = new DepartmentVO();
        Department department1 = Optional.ofNullable(department).orElse(new Department());
        BeanUtils.copyProperties(department1, departmentVO);
        return ResultUtil.success(departmentVO);
    }

    @ApiOperation(value = "部门查询")
    @GetMapping
    public List<Department> getDepartemntList(@Valid @RequestBody DepartmentVO departmentVO, BindingResult result) {
        if (result.hasErrors()) {
            List<ObjectError> list = result.getAllErrors();
            FieldError error = (FieldError) list.get(0);
            logger.info(error.getObjectName() + "" + error.getField() + "" + error.getDefaultMessage());
        }
        System.out.println(ReflectionToStringBuilder.toString(departmentVO, ToStringStyle.MULTI_LINE_STYLE));
        Department department = new Department();
        BeanUtils.copyProperties(departmentVO, department);
        Example<Department> example = Example.of(department);
        List<Department> list = departmentService.findAll(example);
        return list;
    }
}
