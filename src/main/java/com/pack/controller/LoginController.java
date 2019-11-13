package com.pack.controller;

import com.alibaba.fastjson.JSONObject;
import com.pack.entity.User;
import com.pack.exception.UserNotExistException;
import com.pack.service.impl.UserService;
import com.pack.uilts.Result;
import com.pack.uilts.ResultCodeEnum;
import com.pack.uilts.ResultUtil;
import com.pack.vo.UserVO;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @Author: liujinhui
 * @Date: 2019/11/10 19:48
 */
@Controller
@RequestMapping
public class LoginController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/toLogin")
    @GetMapping
    public ModelAndView toLogin() {
        ModelAndView mv = this.getModelAndView();
        mv.setViewName("login");
        return mv;
    }


    @RequestMapping(value = "/loginCheck", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String loginCheck(HttpServletRequest request) {
        JSONObject obj = new JSONObject();
        String errInfo = "";//错误信息
        obj.put("result", errInfo);
        return obj.toString();
    }

    @GetMapping(value = "/toIndex.do")
    public ModelAndView toIndex() {
        logger.info("跳转到门户网站");
        ModelAndView mv = this.getModelAndView();
        mv.setViewName("index");
        mv.addObject("items", null);
        mv.addObject("categories", null);
        return mv;
    }

//    @PostMapping("/login")
//    @ApiImplicitParams({@ApiImplicitParam(name = "username", value = "用户名称", required = true, dataType = "String"),
//            @ApiImplicitParam(name = "password", value = "空间命名", required = true, dataType = "String"),
//    })
//    public String uploadFile(@RequestParam("username") String username,
//                             @RequestParam("password") String password) {
//        logger.info("用户验证");
//
//        return "index";
//    }

    @PostMapping("/login")
    @ApiImplicitParams({@ApiImplicitParam(name = "username", value = "用户名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "空间命名", required = true, dataType = "String"),
    })
    public String login(HttpServletRequest request, @RequestParam("username") String username, @RequestParam("password") String password) {
        UserVO userVO = new UserVO();
        User user = new User();
        user.setName(username);
        user.setPassword(password);
        List<User> all = userService.findAll(Example.of(user));
        if (all != null && !all.isEmpty()) {
            user = all.get(0);
        } else
            throw new UserNotExistException(username);
        BeanUtils.copyProperties(user, userVO);
        request.getSession().setAttribute("user", userVO);
        return "redirect:/index";
    }

    @GetMapping("/logout")
    @ResponseBody
    @ApiOperation(value = "注销用户", notes = "注销用户", hidden = false)
    public ModelAndView logout(HttpServletRequest request) {
        request.getSession().invalidate();
        ModelAndView mv = this.getModelAndView();
        mv.setViewName("login");
        return mv;
    }

    @GetMapping("/index")
    public ModelAndView index() {
        logger.info("用户验证");

        ModelAndView mv = this.getModelAndView();
        mv.setViewName("index");

        int pageNum = 1;
        int size = 17;
        Pageable pageable = PageRequest.of(pageNum - 1, size, Sort.sort(User.class));
        Page<User> pageAll = userService.findAll(pageable);
        List<User> content = pageAll.getContent();
        List<UserVO> list = new ArrayList<>();
        for (User user : content) {
            UserVO vo = new UserVO();
            BeanUtils.copyProperties(user, vo);
            list.add(vo);
        }
        mv.addObject("items", list);
        return mv;
    }
}
