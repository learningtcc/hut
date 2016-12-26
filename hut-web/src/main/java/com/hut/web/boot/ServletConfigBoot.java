package com.hut.web.boot;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.MediaType;
import org.springframework.validation.Validator;
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
     * dispatcher匹配"/"的时候，应该应用此配置，保证静态资源仍然由默认处理（这种方式比addResourceHandlers（）快很多，直接不经过dispatcherServlet了）
     * 4系之前要自己配置默认servlet，4系给默认配置好了
     * @param configurer
     */
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

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
     * 配置格式化器和类型转换器
     * @param registry
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        // Add formatters and/or converters
    }

    /**
     * 添加全局验证器
     * @return
     */
    @Override
    public Validator getValidator() {
        // return "global" validator
        return null;
    }

    /**
     * 还不是很明白
     * @param configurer
     */
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        //configurer.mediaType("json", MediaType.APPLICATION_JSON);
    }

    /**
     * 我理解的是，当没有controller处理请求时，返回一个视图
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("home");
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
