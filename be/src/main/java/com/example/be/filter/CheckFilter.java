package com.example.be.filter;

import com.alibaba.fastjson.JSON;
import com.example.be.common.BaseContext;
import com.example.be.common.R;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter(filterName = "CheckFilter",urlPatterns = "/*")
public class CheckFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String requestUrl = request.getRequestURI();

        if(request.getSession().getAttribute("user") != null){
            Long uID = (Long) request.getSession().getAttribute("user");
            BaseContext.setCurrentId(uID);

            filterChain.doFilter(request,response);
            return;
        }

        if(request.getSession().getAttribute("staff") != null){
            Long uID = (Long) request.getSession().getAttribute("staff");
            BaseContext.setCurrentId(uID);

            filterChain.doFilter(request,response);
            return;
        }

        response.getWriter().write(JSON.toJSONString(R.success("NOT_LOGIN")));
        return;
    }
}
