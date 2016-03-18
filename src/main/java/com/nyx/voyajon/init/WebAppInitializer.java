package com.nyx.voyajon.init;

import com.nyx.voyajon.config.JpaConfig;
import com.nyx.voyajon.config.SecurityConfig;
import com.nyx.voyajon.config.WebConfig;
import com.nyx.voyajon.web.filter.CORSFilter;

import javax.servlet.Filter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{JpaConfig.class, SecurityConfig.class, WebConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{WebConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected Filter[] getServletFilters() {

        DelegatingFilterProxy delegatingFilterProxy = new DelegatingFilterProxy("springSecurityFilterChain");

//        MultipartFilter multipartFilter = new MultipartFilter();
//        multipartFilter.setMultipartResolverBeanName("CommonsMultipartResolver");

        return new Filter[]{delegatingFilterProxy,/* multipartFilter, */new CORSFilter()};
    }

}
