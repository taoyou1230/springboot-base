package com.springboot.base.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Author: Damon.CF
 * Company: UBIOT.CN
 * Date: 2018/12/30$
 * Description:
 */
public class TimeFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(TimeFilter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("TimeFilter init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        logger.info("TimeFilter doFilter: " + dateFormat.format(new Date()));

        HttpServletResponse rpo = (HttpServletResponse) response;
        rpo.setHeader("Access-Control-Allow-Origin", "*");
        rpo.setHeader("Access-Control-Allow-Methods",
                "POST, GET, OPTIONS, DELETE");
        rpo.setHeader("Access-Control-Max-Age", "3600");
        rpo.setHeader("Access-Control-Allow-Headers",
                "Content-Type, x-requested-with, X-Custom-Header, Authorization");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        logger.info("TimeFilter destroy");
    }
}
