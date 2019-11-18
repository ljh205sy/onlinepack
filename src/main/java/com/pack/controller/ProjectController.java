package com.pack.controller;

import com.pack.entity.Demand;
import com.pack.entity.Project;
import com.pack.entity.User;
import com.pack.service.impl.DemandService;
import com.pack.service.impl.ProjectService;
import com.pack.service.impl.UserService;
import com.pack.uilts.Result;
import com.pack.uilts.ResultCodeEnum;
import com.pack.uilts.ResultUtil;
import com.pack.vo.DemandVO;
import com.pack.vo.ProjectVO;
import com.pack.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

/**
 * @Author: liujinhui
 * @Date: 2019/11/8 22:51
 */
@Controller
@RequestMapping("/v1/project")
@Api(tags = "项目管理")
public class ProjectController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private DemandService demandService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    @ApiOperation(value = "项目新增", notes = "项目新增", hidden = false)
    @PostMapping
    @ResponseBody
    public Result<Boolean> save(@RequestBody(required = true) ProjectVO projectVO) {
        Project project = new Project();
        BeanUtils.copyProperties(projectVO, project);
        projectService.save(project);
        return ResultUtil.success(true);
    }


    @ResponseBody
    @PutMapping
    @ApiOperation(value = "项目修改", notes = "项目修改", hidden = false)
    public Result<Boolean> update(@Valid @RequestBody ProjectVO projectVO, BindingResult errors) {
        return this.save(projectVO);
    }

    @DeleteMapping("/{id:\\d}")
    @ResponseBody
    @ApiOperation(value = "项目删除", notes = "项目删除", hidden = false)
    public Result<Boolean> delete(@PathVariable @ApiParam("需求id") Integer id) {
        try {
            projectService.delete(id);
        } catch (Exception e) {
            return ResultUtil.error(ResultCodeEnum.UNKNOW_FAILED);
        }
        return ResultUtil.success(true);
    }
}
