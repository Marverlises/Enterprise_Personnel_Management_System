package com.by.coursedesign.filter;

import com.by.coursedesign.entity.User;
import com.by.coursedesign.utils.DataUtils;
import com.by.coursedesign.utils.WebUtils;
import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @Author Baiyu
 * @Time 2023/6/24 19:17
 * @StudentNumber 2018217662
 * @Description
 */
public class AuthFilter implements Filter {
    private List<String> excludedUrls;
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //获取到配置excludedUrls
        String strExcludedUrls = filterConfig.getInitParameter("excludedUrls");
        String[] splitUrl = strExcludedUrls.split(",");
        //将 splitUrl 转成 List
        excludedUrls = Arrays.asList(splitUrl);
    }
    
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        //得到请求的url
        String url = request.getServletPath();
        //判断是否要验证
        if (!excludedUrls.contains(url)) {
            User user = (User) request.getSession().getAttribute("user");
            if (user == null) {
                //判断是不是Ajax请求
                if (!WebUtils.isAjaxRequest(request)) {//如果不是ajax
                    //转发到登录页面, 转发不走过滤器
                    request.getRequestDispatcher("/views/user/login.jsp")
                            .forward(servletRequest, servletResponse);
                } else {//如果是ajax
                    //返回一个url=> 按照json格式
                    HashMap<String, Object> resultMap = new HashMap<>();
                    resultMap.put("url", "views/user/login.jsp");
                    String resultJson = new Gson().toJson(resultMap);
                    servletResponse.getWriter().write(resultJson);
                }
                return;
            }
            //如果为普通员工，则不允许访问
            // /views/manage/*
            // /views/edit/*
            // /manage/*
            // 目录下的内容
            if (user.getPermission_level() == 0) {
                if (url.contains("manage") || url.contains("edit")) {
                    //转发到登录页面, 转发不走过滤器
                    //PrintWriter writer = servletResponse.getWriter();
                    //writer.println("<html>");
                    //writer.println("<script>");
                    //writer.println("window.open ('"+request.getContextPath()+"/views/employee/index.jsp','_top')");
                    //writer.println("</script>");
                    //writer.println("</html>");
                    //转发到登录页面, 转发不走过滤器
                    request.getRequestDispatcher(request.getContextPath() + "/views/employee/index.jsp")
                            .forward(servletRequest, servletResponse);
                }
            }
            DataUtils.user = user;
        }
        
        //继续访问
        filterChain.doFilter(servletRequest, servletResponse);
    }
    
    @Override
    public void destroy() {
    
    }
}
