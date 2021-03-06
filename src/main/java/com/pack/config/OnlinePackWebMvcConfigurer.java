//package com.pack.config;
//
//import com.alibaba.fastjson.serializer.SerializerFeature;
//import com.alibaba.fastjson.support.config.FastJsonConfig;
//import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
//import com.pack.filter.OnlinePackFilter;
//import com.pack.interceptor.OnlinePackInterceptor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.converter.HttpMessageConverter;
//import org.springframework.web.servlet.config.annotation.*;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @author wh1107066
// */
//@Configuration
//public class OnlinePackWebMvcConfigurer extends WebMvcConfigurerAdapter {
//
//    @Autowired
//    private OnlinePackInterceptor onlinePackInterceptor;
//    /**
//     * 配置静态访问资源
//     *
//     * @param registry
//     */
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        //自定义项目内目录
//        //registry.addResourceHandler("/my/**").addResourceLocations("classpath:/my/");
//        //指向外部目录
//        registry.addResourceHandler("/my/**").addResourceLocations("file:E:/my/");
//        registry.addResourceHandler("swagger-ui.html")
//                .addResourceLocations("classpath:/META-INF/resources/");
//        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
//        registry.addResourceHandler("/webjars/**")
//                .addResourceLocations("classpath:/META-INF/resources/webjars/");
//        super.addResourceHandlers(registry);
//    }
//
//    /**
//     * 以前要访问一个页面需要先创建个Controller控制类，在写方法跳转到页面
//     * 在这里配置后就不需要那么麻烦了，直接访问http://localhost:8080/toLogin就跳转到login.html页面了
//     *
//     * @param registry
//     */
//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/toLogin").setViewName("login");
//        super.addViewControllers(registry);
//    }
//
//    /**
//     * 拦截器
//     */
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        // addPathPatterns 用于添加拦截规则
//        // excludePathPatterns 用户排除拦截
//        registry.addInterceptor(onlinePackInterceptor).addPathPatterns("/**")
//                .excludePathPatterns("/toLogin", "/login", "/doc.html" );
//
//        super.addInterceptors(registry);
//    }
//
//    /**
//     * 配置全局Filter
//     * @return
//     */
////    @Bean
////    public FilterRegistrationBean timeFilter(){
////        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
////        OnlinePackFilter timeFilter = new OnlinePackFilter();
////        List<String> urls = new ArrayList<String>();
////        urls.add("/*");
////        registrationBean.setUrlPatterns(urls);
////        registrationBean.setFilter(timeFilter);
////        return registrationBean;
////    }
//
//
//    /**
//     * 配置fastJson
//     *
//     * @param converters
//     */
//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
//        FastJsonConfig fastJsonConfig = new FastJsonConfig();
//        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
//        fastConverter.setFastJsonConfig(fastJsonConfig);
//        converters.add(fastConverter);
//        super.configureMessageConverters(converters);
//    }
//}
//
