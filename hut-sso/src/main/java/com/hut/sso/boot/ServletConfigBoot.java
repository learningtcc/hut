package com.hut.sso.boot;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Jared on 2016/12/11.
 */
@Configuration
@EnableWebMvc
public class ServletConfigBoot extends WebMvcConfigurerAdapter {


    /*
    * 添加Spring拦截器
    * */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
 /*       registry.addInterceptor(new LocaleInterceptor());
        registry.addInterceptor(new ThemeInterceptor()).addPathPatterns("*//**").excludePathPatterns("/admin*//**");
        registry.addInterceptor(new SecurityInterceptor()).addPathPatterns("/secure*//*");*/
    }

}
