package com.pack.controller;

import com.pack.entity.Demand;
import com.pack.entity.UploadModule;
import com.pack.entity.UploadModuleDependency;
import com.pack.service.impl.UploadModuleDependencyService;
import com.pack.service.impl.UploadModuleService;
import com.pack.uilts.Result;
import com.pack.uilts.ResultCodeEnum;
import com.pack.uilts.ResultUtil;
import com.pack.vo.DemandVO;
import com.pack.vo.UploadModuleDependencyVO;
import com.pack.vo.UploadModuleVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @Author: liujinhui
 * @Date: 2019/11/8 22:51
 */
@Controller
@RequestMapping("/v1/uploadModule")
@Api(tags = "上传模块管理")
public class UploadModuleController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UploadModuleService uploadModuleService;
    @Autowired
    private UploadModuleDependencyService uploadModuleDependencyService;


    // 查询是否存在相同的module_id与version， 如果存在相同的表示是同一模块，不能新增
    @PostMapping("/moduleExists")
    @ResponseBody
    @ApiOperation(value = "判断模块是否存在", notes = "判断模块是否存在，存在返回true", hidden = false)
    public Result<Boolean> findExistsByModuleIDAndVersion(@RequestBody UploadModuleVO uploadModuleVO) {
        UploadModule module = new UploadModule();
        module.setModuleId(uploadModuleVO.getModuleId());
        module.setModuleVersion(uploadModuleVO.getModuleVersion());
        module.setModuleType(uploadModuleVO.getModuleType());
        Example<UploadModule> example = Example.of(module);
        List<UploadModule> list = uploadModuleService.findAll(example);
        return ResultUtil.success(!list.isEmpty());
    }

    @ApiOperation(value = "上传模块新增", notes = "上传模块及版本新增", hidden = false)
    @PostMapping
    @ResponseBody
    public Result<Boolean> save(@RequestBody(required = true) UploadModuleVO uploadModuleVO) {
        UploadModule uploadModule = new UploadModule();
        BeanUtils.copyProperties(uploadModuleVO, uploadModule);
        // TODO 模块上传时，关联的模块也需要存储。 关联模块与版本时，先删除, 在新增
        Set<UploadModuleDependencyVO> uploadDependencys = uploadModuleVO.getUploadDependencys();
        if (uploadDependencys != null) {
            UploadModuleDependency temp = new UploadModuleDependency(new UploadModule(uploadModuleVO.getGuid()));
            List<UploadModuleDependency> all = uploadModuleDependencyService.findAll(temp);
            all.stream().forEach(s -> {
                if (StringUtils.isNotEmpty(s.getUuid())) {
                    uploadModuleDependencyService.delete(s.getUuid());
                }
            });
        }

        // TODO , 由多的一方来维护关系（模块依赖关系）， 上传模块不进行cascade模块及版本信息表
        Set<UploadModuleDependency> dependencys = new HashSet<>();
        if (uploadDependencys != null && !uploadDependencys.isEmpty()) {
            Iterator<UploadModuleDependencyVO> iterator = uploadModuleVO.getUploadDependencys().iterator();
            while (iterator.hasNext()) {
                UploadModuleDependencyVO next = iterator.next();
                UploadModuleDependency p = new UploadModuleDependency();
                BeanUtils.copyProperties(next, p);
                p.setUploadModule(uploadModule);
                dependencys.add(p);
            }
            uploadModule.setUploadDependencys(dependencys);
        }
        // TODO 模块上传时，关联的需求也需要存储， 一个模块可以解决多个需求。一个需求需要多个模块进行交互处理
        Set<Demand> demands = new HashSet<>();
        Set<DemandVO> demandvos = uploadModuleVO.getDemands();
        if (demandvos != null && !demandvos.isEmpty()) {
            Iterator<DemandVO> iterator = demandvos.iterator();
            while (iterator.hasNext()) {
                DemandVO source = iterator.next();
                Demand target = new Demand();
                BeanUtils.copyProperties(source, target);
                demands.add(target);
            }
            uploadModule.setDemands(demands);
        }


        uploadModuleService.save(uploadModule);
        uploadModuleDependencyService.save(dependencys);// 由多的一方来维护，上传模块与模块、版本之间的关系
        return ResultUtil.success(true);
    }


    @ResponseBody
    @PutMapping
    @ApiOperation(value = "上传模块修改", notes = "上传模块修改", hidden = false)
    public Result<Boolean> update(@RequestBody(required = true) UploadModuleVO uploadModuleVO, BindingResult errors) {
        return this.save(uploadModuleVO);
    }

    @ApiOperation(value = "使用上传模块ID查询")
    @ResponseBody
    @GetMapping("/{uuid}")
    public Result<UploadModuleVO> getUserById(@ApiParam("上传模块id") @PathVariable("uuid") String uuid) {
        UploadModule uploadModule = uploadModuleService.findById(uuid);
        UploadModuleVO uploadModuleVO = new UploadModuleVO();
        if (uploadModule != null) {
            throw new RuntimeException("上传模块不存在，不能删除！！");
        } else {
            BeanUtils.copyProperties(uploadModule, uploadModuleVO);
        }

        return ResultUtil.success(uploadModuleVO);
    }

    @DeleteMapping("/{guid}")
    @ResponseBody
    @ApiOperation(value = "上传模块删除", notes = "上传模块删除", hidden = false)
    public Result<Boolean> delete(@PathVariable @ApiParam("上传模块guid") String guid) {
        try {
            // 删除模块依赖
            UploadModuleDependency temp = new UploadModuleDependency(new UploadModule(guid));
            List<UploadModuleDependency> all = uploadModuleDependencyService.findAll(temp);
            all.stream().forEach(s -> {
                if (StringUtils.isNotEmpty(s.getUuid())) {
                    uploadModuleDependencyService.delete(s.getUuid());
                }
            });
            uploadModuleService.delete(guid);
        } catch (Exception e) {
            return ResultUtil.error(ResultCodeEnum.UNKNOW_FAILED);
        }
        return ResultUtil.success(true);
    }


}
