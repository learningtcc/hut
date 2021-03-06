package com.hut.file.boot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Jared on 2016/12/11.
 */
@EnableWebMvc
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ComponentScan({"com.hut.file.controller"})
public class ServletConfigBoot extends WebMvcConfigurerAdapter {


    /**
     * 用默认servlet进行静态资源处理
     * @param configurer
     */
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    /**
    * 添加Spring拦截器
    * */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //registry.addInterceptor(new LocaleInterceptor());
        //registry.addInterceptor(new ThemeInterceptor()).addPathPatterns("*//**").excludePathPatterns("/admin*//**");
        //registry.addInterceptor(new SecurityInterceptor()).addPathPatterns("/secure*//*");
    }

    /**
     * 文件上传解析器
     * @return
     */
    @Bean
    public CommonsMultipartResolver multipartResolver()
    {
        CommonsMultipartResolver cmr = new CommonsMultipartResolver();
        cmr.setMaxUploadSize(1024*1024*3);
        return cmr;
    }

}
