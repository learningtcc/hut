package com.hut.message.boot;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Created by Jared on 2016/12/11.
 */
public class ApplicationBoot extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{RootConfigBoot.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{ServletConfigBoot.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
