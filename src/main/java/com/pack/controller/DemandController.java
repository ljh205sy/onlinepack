package com.pack.controller;

import com.pack.entity.*;
import com.pack.service.impl.*;
import com.pack.uilts.Result;
import com.pack.uilts.ResultCodeEnum;
import com.pack.uilts.ResultUtil;
import com.pack.vo.DemandModuleVO;
import com.pack.vo.DemandVO;
import com.pack.vo.ProjectVO;
import com.pack.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
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
@RequestMapping("/v1/demand")
@Api(tags = "需求管理")
public class DemandController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private DemandService demandService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private DemandModuleService demandModuleService;

    @Autowired
    private UserService userService;

    @ApiOperation(value = "需求新增", notes = "用户新增", hidden = false)
    @PostMapping
    @ResponseBody
    public Result<Boolean> save(@RequestBody(required = true) DemandVO demandVO) {
        Demand demand = new Demand();
        BeanUtils.copyProperties(demandVO, demand);
        Set<ProjectVO> projects = demandVO.getProjects();

        // 关联项目，项目不由需求来维护，所以cascade不需要
        Set<Project> pro = new HashSet<>();
        if (projects != null) {
            Iterator<ProjectVO> iterator = projects.iterator();
            while (iterator.hasNext()) {
                ProjectVO next = iterator.next();
                Project p = new Project();
                BeanUtils.copyProperties(next, p);
                pro.add(p);
            }
            demand.setProjects(pro);
        }


        // 关联模块与版本时，先删除指定外键demand_id, 在新增
        DemandModule temp = new DemandModule(new Demand(demandVO.getDemandId()));
        List<DemandModule> all = demandModuleService.findAll(temp);
        all.stream().forEach(s -> {
            if(s.getDemand() != null && s.getDemand().getDemandId() != null ) {
                demandModuleService.delete(s.getGuid());
            }
        });

        // TODO , 由多的一方来维护关系， 需求不进行cascade模块及版本信息表
        Set<DemandModule> demandModules = new HashSet<>();
        if (demandVO.getDemandModules() != null  && !demandVO.getDemandModules().isEmpty()) {
            Iterator<DemandModuleVO> iterator = demandVO.getDemandModules().iterator();
            while (iterator.hasNext()) {
                DemandModuleVO next = iterator.next();
                DemandModule p = new DemandModule();
                BeanUtils.copyProperties(next, p);
                p.setDemand(demand);
                demandModules.add(p);
            }
            demand.setDemandModules(demandModules);
        }
        demandService.save(demand);
        demandModuleService.save(demandModules); // 由多的一方来维护，需求与模块、版本之间的关系


        return ResultUtil.success(true);
    }


    @ResponseBody
    @PutMapping
    @ApiOperation(value = "需求修改", notes = "需求修改", hidden = false)
    public Result<Boolean> update(@Valid @RequestBody DemandVO demandVO, BindingResult errors) {
        return this.save(demandVO);
    }

    @DeleteMapping("/{id:\\d}")
    @ResponseBody
    @ApiOperation(value = "需求删除", notes = "需求删除", hidden = false)
    public Result<Boolean> delete(@PathVariable @ApiParam("需求id") Integer id) {
        try {
            demandService.delete(id);
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
    @ApiOperation(value = "使用ID查询用户")
    @ResponseBody
    @GetMapping("/{userid}")
    public Result<UserVO> getUserById(@ApiParam("用户id") @PathVariable("userid") String id) {
        User user = userService.findById(id);
        UserVO userVO = new UserVO();
        User source = Optional.ofNullable(user).orElse(new User());
        BeanUtils.copyProperties(source, userVO);
        return ResultUtil.success(userVO);
    }

    @ApiOperation(value = "查询所有用户")
    @ResponseBody
    @GetMapping
    public Result<List<UserVO>> getUsers() {
        int pageNum = 1;
        int size = 17;
        Pageable pageable = PageRequest.of(pageNum - 1, size);
        Page<User> pageAll = userService.findAll(pageable);
        List<User> content = pageAll.getContent();
        List<UserVO> list = new ArrayList<>();
        for (User user : pageAll) {
            UserVO userVO = new UserVO();
            BeanUtils.copyProperties(user, userVO);
            list.add(userVO);
        }
        return ResultUtil.success(list);
    }
}
