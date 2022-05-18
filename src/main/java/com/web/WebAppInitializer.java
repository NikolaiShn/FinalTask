package com.web;

import com.web.utils.AnnotationWebConfig;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    protected Class<?>[] getRootConfigClasses() {
// AppConfig defines beans that would be in root-context.xml
        return new Class[] {AnnotationWebConfig.class, WebSecurityConfig.class};
    }
    protected Class<?>[] getServletConfigClasses() {
// WebConfig defines beans that would be in servlet.xml
        return new Class[] {WebConfig.class};
    }
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }

    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        return new Filter[] {characterEncodingFilter};
    }
}
