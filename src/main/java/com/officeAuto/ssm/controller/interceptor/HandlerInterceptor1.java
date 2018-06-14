package com.officeAuto.ssm.controller.interceptor;

import com.officeAuto.ssm.model.Employee;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class HandlerInterceptor1 implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        HttpServletRequest request = (HttpServletRequest) httpServletRequest;
        HttpServletResponse response = (HttpServletResponse) httpServletResponse;

        // 获得uri
        String uri = request.getRequestURI();
        // 获得最后一个/的下标
        Integer startLength = uri.lastIndexOf("/");
        // 截取/后的所有字符
        String clazzName = uri.substring(startLength + 1);



        if("login.action".equals(clazzName)){
            return true;
        }


        HttpSession session = httpServletRequest.getSession();
        Employee employee = (Employee) session.getAttribute("employee");

        if(employee != null){
            return true;
        }
        //不符合条件的，跳转到登录界面
        httpServletRequest.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(httpServletRequest, httpServletResponse);

        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
