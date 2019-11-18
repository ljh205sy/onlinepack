package com.pack.controller;

import com.pack.entity.Resource;
import com.pack.entity.Role;
import com.pack.service.impl.RoleService;
import com.pack.uilts.Result;
import com.pack.uilts.ResultCodeEnum;
import com.pack.uilts.ResultUtil;
import com.pack.vo.ResourceVO;
import com.pack.vo.RoleVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

/**
 * @Author: liujinhui
 * @Date: 2019/11/8 22:51
 */
@RestController
@RequestMapping("/v1/role")
@Api(tags = "角色管理")
public class RoleController {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private RoleService roleService;

    /**
     * 部门增加
     */
    @ApiOperation(value = "角色新增", notes = "角色新增", hidden = false)
    @PostMapping
    public Result<RoleVO> save(@RequestBody(required = true) RoleVO roleVO) {
        Role role = new Role();
        BeanUtils.copyProperties(roleVO, role);
        // 新增角色
        Set<ResourceVO> resources = roleVO.getResources();
        Set<Resource> newRes = new HashSet<>();
        if (resources != null && !resources.isEmpty()) {
            Iterator<ResourceVO> iterator = resources.iterator();
            while (iterator.hasNext()) {
                ResourceVO next = iterator.next();
                Resource res = new Resource();
                BeanUtils.copyProperties(next, res);
                newRes.add(res);
            }
            role.setResources(newRes);
        }
        roleService.save(role);
        return ResultUtil.success(roleVO);
    }

    @PutMapping
    @ApiOperation(value = "角色修改", notes = "角色修改", hidden = false)
    public Result<RoleVO> update(@Valid @RequestBody RoleVO roleVO, BindingResult errors) {
        return this.save(roleVO);
    }

    @DeleteMapping("/{roleid}")
    @ApiOperation(value = "角色删除", notes = "删除角色", hidden = false)
    public Result<Boolean> delete(@PathVariable @ApiParam("角色id") String roleid) {
        try {
            roleService.delete(roleid);
        } catch (Exception e) {
            return ResultUtil.error(ResultCodeEnum.UNKNOW_FAILED);
        }
        return ResultUtil.success(true);
    }

    @ApiOperation(value = "使用ID查询角色")
    @GetMapping("/{roleid}")
    public Result<RoleVO> getRoleById(@ApiParam("角色id") @PathVariable("roleid") String id) {
        Role role = roleService.findById(id);
        RoleVO roleVO = new RoleVO();
        Role source = Optional.ofNullable(role).orElse(new Role());
        BeanUtils.copyProperties(source, roleVO);
        return ResultUtil.success(roleVO);
    }


}
