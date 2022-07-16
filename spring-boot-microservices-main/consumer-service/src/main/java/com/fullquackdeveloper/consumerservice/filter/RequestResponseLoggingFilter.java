package com.fullquackdeveloper.consumerservice.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

@Component
@Slf4j
public class RequestResponseLoggingFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        log.info("Logging Request  {} : {}", req.getMethod(), req.getRequestURI());
        Enumeration<String> headerNames = ((HttpServletRequest) servletRequest).getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            System.out.println("Header:" + headerName + " value:" + ((HttpServletRequest) servletRequest).getHeader(headerName));
        }
        filterChain.doFilter(servletRequest, servletResponse);
        log.info("Logging Response :{}", res.getContentType());
    }
}
