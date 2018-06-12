package com.witshare.mars.filter;


import com.witshare.mars.config.CurrentThreadContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.web.support.ErrorPageFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 过滤器
 */
@Configuration
public class ConfigurationFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigurationFilter.class);

    @Bean
    public FilterRegistrationBean requestWrapperFilterRegistration() {
        FilterRegistrationBean requestWrapper = new FilterRegistrationBean();
        requestWrapper.setFilter(new RequestBodyFilter());//添加requestBody只能单次读取的过滤器
        requestWrapper.addUrlPatterns("/*");//设置过滤路径，/*所有路径
        requestWrapper.setName("RequestBodyFilter");
        requestWrapper.setOrder(1);//设置优先级
        return requestWrapper;
    }

    @Bean
    public ErrorPageFilter errorPageFilter() {
        return new ErrorPageFilter();
    }

    @Bean
    public FilterRegistrationBean disableSpringBootErrorFilter(ErrorPageFilter filter) {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(filter);
        filterRegistrationBean.setEnabled(false);
        return filterRegistrationBean;
    }

    public class RequestBodyFilter implements Filter {

        @Override
        public void init(FilterConfig filterConfig) throws ServletException {
        }

        @Override
        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
            ServletRequest requestWrapper = null;
            if (request instanceof HttpServletRequest) {
                requestWrapper = new BufferedServletRequestWrapper((HttpServletRequest) request);
            }
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            CurrentThreadContext.setResponse(httpServletResponse);
            CurrentThreadContext.setRequest(httpServletRequest);
            if (null == requestWrapper) {
                chain.doFilter(request, response);
            } else {
                chain.doFilter(requestWrapper, response);
            }
        }

        @Override
        public void destroy() {
        }
    }

}
