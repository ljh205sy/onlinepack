package com.pack.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: liujinhui
 * @Date: 2019/11/10 20:52
 */
public class BaseController {
    public Logger log = LoggerFactory.getLogger(BaseController.class);

    public void debug(String message, Exception e) {
        log.debug(message, e);
    }

    public void info(String message, Exception e) {
        log.info(message, e);
    }

    public void error(String message, Exception e) {
        log.error(message, e);
    }

    /***
     * 获取request值
     * @return
     */
    public HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * 得到ModelAndView
     */
    public ModelAndView getModelAndView() {
        return new ModelAndView();
    }

}