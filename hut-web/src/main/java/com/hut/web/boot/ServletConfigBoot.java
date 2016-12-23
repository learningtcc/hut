package com.hut.web.boot;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.*;

/**
 * Created by Jared on 2016/12/11.
 */
@EnableWebMvc
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ComponentScan({"com.hut.web.controller"})
public class ServletConfigBoot extends WebMvcConfigurerAdapter {

    /**
     * 当设置dispatcherServlet匹配"/"时，设置静态文件的映射关系
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        //registry.addResourceHandler("/rs/**").addResourceLocations("/rs/").setCachePeriod(31556926);
    }

    /**
    * 添加spring拦截器
    * */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //registry.addInterceptor(new LocaleInterceptor());
        //registry.addInterceptor(new ThemeInterceptor()).addPathPatterns("*//**").excludePathPatterns("/admin*//**");
        //registry.addInterceptor(new SecurityInterceptor()).addPathPatterns("/secure*//*");
    }

    /**
     * 配置视图解析器
     */
    public void configureViewResolvers(ViewResolverRegistry registry){
        registry.jsp("/WEB-INF/views/",".jsp");
    }

    /**
     * dispatcher匹配"/"的时候，应该应用此配置，保证静态资源仍然由默认处理（这种方式比addResourceHandlers（）快很多，直接不经过dispatcherServlet了）
     * 4系之前要自己配置默认servlet，4系给默认配置好了
     * @param configurer
     */
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    /**
     * 文件上传解析器
     * @return
     */
   /* @Bean
    public CommonsMultipartResolver multipartResolver()
    {
        CommonsMultipartResolver cmr = new CommonsMultipartResolver();
        cmr.setMaxUploadSize(1024*1024*3);
        return cmr;
    }*/
}
