package com.pack.controller;

import com.pack.entity.Department;
import com.pack.entity.Role;
import com.pack.entity.User;
import com.pack.service.impl.DepartmentService;
import com.pack.service.impl.RoleService;
import com.pack.service.impl.UserService;
import com.pack.uilts.Result;
import com.pack.uilts.ResultCodeEnum;
import com.pack.uilts.ResultUtil;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @Author: liujinhui
 * @Date: 2019/11/8 22:51
 */
@Controller
@RequestMapping("/v1/user")
@Api(tags = "用户管理")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    /**
     * 部门增加
     */
    @ApiOperation(value = "用户新增", notes = "用户新增", hidden = false)
    @PostMapping
    @ResponseBody
    public Result<Boolean> save(@RequestBody(required = true) UserVO userVO) {
        User user = new User();
        BeanUtils.copyProperties(userVO, user);

        // 新增部门，父节点为空
        Optional<Department> result = Optional.ofNullable(userVO.getDepartment())
                .filter(u -> StringUtils.isNotEmpty(u.getDepartmentid()));
        if (result.isPresent()) {
            String deptid = userVO.getDepartment().getDepartmentid();
            Department pDept = departmentService.getOne(deptid);
            user.setDepartments(pDept);
        }

        // 新增角色
        Set<Role> roles = userVO.getRoles();
        if (roles != null && !roles.isEmpty()) {
            user.setRoles(roles);
        }

        userService.save(user);
        return ResultUtil.success(true);
    }

    @ResponseBody
    @PutMapping
    @ApiOperation(value = "用户修改", notes = "用户修改", hidden = false)
    public Result<Boolean> update(@Valid @RequestBody UserVO userVO, BindingResult errors) {
        return this.save(userVO);
    }

    @DeleteMapping("/{userid}")
    @ResponseBody
    @ApiOperation(value = "用户删除", notes = "删除用户", hidden = false)
    public Result<Boolean> delete(@PathVariable @ApiParam("用户id") String userid) {
        try {
            userService.delete(userid);
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
