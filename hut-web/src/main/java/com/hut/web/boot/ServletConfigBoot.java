package com.hut.web.boot;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Locale;

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
