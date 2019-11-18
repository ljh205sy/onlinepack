package com.pack.controller;

import com.pack.entity.Resource;
import com.pack.entity.Resource;
import com.pack.service.impl.ResourceService;
import com.pack.service.impl.ResourceService;
import com.pack.uilts.Result;
import com.pack.uilts.ResultCodeEnum;
import com.pack.uilts.ResultUtil;
import com.pack.vo.ResourceVO;
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
@RequestMapping("/v1/resource")
@Api(tags = "资源管理")
public class ResourceController {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ResourceService resourceService;

    /**
     * 资源增加
     */
    @ApiOperation(value = "资源新增", notes = "资源新增", hidden = false)
    @PostMapping
    public Result<Boolean> save(@RequestBody(required = true) ResourceVO resourceVO) {
        Resource resource = new Resource();
        BeanUtils.copyProperties(resourceVO, resource);
        // 父节点为空
        Optional<ResourceVO> result = Optional.ofNullable(resourceVO.getPresource())
                .filter(u -> StringUtils.isNotEmpty(u.getGuid()));
        if (result.isPresent()) {
            String pid = result.get().getGuid();
            Resource pResource = resourceService.getOne(Optional.of(pid).get());
            resource.setPresource(pResource);
        }
        resourceService.save(resource);
        return ResultUtil.success(true);
    }

    /**
     * 部分修改
     *
     * @param resourceVO
     * @param errors
     * @return
     */
    @PutMapping
    @ApiOperation(value = "资源修改", notes = "资源修改", hidden = false)
    public Result<Boolean> updateResource(@Valid @RequestBody ResourceVO resourceVO, BindingResult errors) {
        return this.save(resourceVO);
    }

    /**
     * 存在外键引用，删除失败的情况
     */
    @DeleteMapping("/{resourceid}")
    @ApiOperation(value = "资源删除", notes = "资源删除", hidden = false)
    public Result<Boolean> delete(@PathVariable @ApiParam("资源id") String resourceid) {
        try {
            resourceService.delete(resourceid);
        } catch (Exception e) {
            return ResultUtil.error(ResultCodeEnum.UNKNOW_FAILED);
        }
        return ResultUtil.success(true);
    }

    /**
     * 根据id查询Resource， 如果不存在此资源，返回一个Resource对象，但是Resource的所有值都为空
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "使用ID查询资源")
    @GetMapping("/{resourceid}")
    public Result<ResourceVO> getDepetmentById(@ApiParam("资源id") @PathVariable("resourceid") String id) {
        Resource resource = resourceService.findById(id);
        ResourceVO resourceVO = new ResourceVO();
        Resource resource1 = Optional.ofNullable(resource).orElse(new Resource());
        BeanUtils.copyProperties(resource1, resourceVO);
        return ResultUtil.success(resourceVO);
    }

    @ApiOperation(value = "资源查询")
    @GetMapping
    public List<Resource> getDepartemntList(@Valid @RequestBody ResourceVO resourceVO, BindingResult result) {
        if (result.hasErrors()) {
            List<ObjectError> list = result.getAllErrors();
            FieldError error = (FieldError) list.get(0);
            logger.info(error.getObjectName() + "" + error.getField() + "" + error.getDefaultMessage());
        }
        logger.info(ReflectionToStringBuilder.toString(resourceVO, ToStringStyle.MULTI_LINE_STYLE));
        Resource resource = new Resource();
        BeanUtils.copyProperties(resourceVO, resource);
        Example<Resource> example = Example.of(resource);
        List<Resource> list = resourceService.findAll(example);
        return list;
    }
}
