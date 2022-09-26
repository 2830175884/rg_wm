package com.inquistivecat.filter;

import com.alibaba.fastjson.JSON;
import com.inquistivecat.commom.BaseContext;
import com.inquistivecat.commom.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Filter 过滤未登录用户直接进后台
 * @author jh
 */
@WebFilter(filterName = "loginCheckFilter",urlPatterns = "/*")
@Slf4j
public class LoginCheckFilter implements Filter {
    public static final AntPathMatcher ANT_PATH_MATCHER = new AntPathMatcher();
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        String requestURI = request.getRequestURI();
        String[] urls = {
                "/employee/login",
                "/employee/logout",
                "/backend/**",
                "/front/**",
                "user/sendMsg",
                "/user/login"
        };
//        log.info("拦截到请求：{}",request.getRequestURI());
        boolean check = check(urls, requestURI);
        if (check) {
//            log.info("本次请求不需要处理,{}",requestURI);
            filterChain.doFilter(request,response);
            return;
        }
        boolean isin1 = request.getSession().getAttribute("employee") != null;
        if (isin1) {
//            log.info("用户已登录，用户id为：{}",request.getSession().getAttribute("employee"));
            Long employeeId = (Long)request.getSession().getAttribute("employee");
            BaseContext.setCurrentId(employeeId);
            filterChain.doFilter(request,response);
            return;
        }
        boolean isin2 = request.getSession().getAttribute("user") != null;
        if (isin2) {
//            log.info("用户已登录，用户id为：{}",request.getSession().getAttribute("employee"));
            Long userId = (Long)request.getSession().getAttribute("user");
            BaseContext.setCurrentId(userId);
            filterChain.doFilter(request,response);
            return;
        }
//        log.info("用户未登录");
        response.getWriter().write(JSON.toJSONString(Result.error("NOTLOGIN")));

    }

    public boolean check(String [] urls, String requestURI){
        for (String url : urls) {
            boolean match = ANT_PATH_MATCHER.match(url, requestURI);
            if (match) {
                return true;
            }
        }
        return false;
    }
}
