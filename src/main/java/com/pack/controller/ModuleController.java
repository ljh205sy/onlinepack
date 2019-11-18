package com.pack.controller;

import com.pack.entity.Department;
import com.pack.entity.Module;
import com.pack.entity.Role;
import com.pack.entity.User;
import com.pack.service.impl.DepartmentService;
import com.pack.service.impl.ModuleService;
import com.pack.service.impl.RoleService;
import com.pack.service.impl.UserService;
import com.pack.uilts.Result;
import com.pack.uilts.ResultCodeEnum;
import com.pack.uilts.ResultUtil;
import com.pack.vo.ModuleVO;
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
@RequestMapping("/v1/module")
@Api(tags = "模块管理")
public class ModuleController {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ModuleService moduleService;

    /**
     * 部门增加
     */
    @ApiOperation(value = "模块新增", notes = "模块新增", hidden = false)
    @PostMapping
    @ResponseBody
    public Result<Boolean> save(@RequestBody(required = true) ModuleVO moduleVO) {
        Module module = new Module();
        BeanUtils.copyProperties(moduleVO, module);
        Set<Module> dependency = new HashSet<>();
        if (moduleVO.getDependencyModules() != null && !moduleVO.getDependencyModules().isEmpty()) {
            Iterator<ModuleVO> iterator = moduleVO.getDependencyModules().iterator();
            while (iterator.hasNext()) {
                ModuleVO vo = iterator.next();
                Module temp = new Module();
                BeanUtils.copyProperties(vo, temp);
                dependency.add(temp);
            }
        }
        module.setDependencyModules(dependency);
        moduleService.save(module);
        return ResultUtil.success(true);
    }

}
