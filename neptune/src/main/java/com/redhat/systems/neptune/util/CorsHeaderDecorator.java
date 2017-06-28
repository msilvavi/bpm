package com.redhat.systems.neptune.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

@WebFilter("/rest/*")
public class CorsHeaderDecorator implements Filter {
    
    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        ((HttpServletResponse)response).addHeader("Access-Control-Allow-Origin", "*");
        ((HttpServletResponse)response).addHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
        ((HttpServletResponse)response).addHeader("Access-Control-Allow-Credentials", "true");
        ((HttpServletResponse)response).addHeader("Access-Control-Allow-Methods", "GET, OPTIONS, POST, PUT, DELETE");
        chain.doFilter(request, response);
    }

    public void init(FilterConfig fConfig) throws ServletException {
    }
}