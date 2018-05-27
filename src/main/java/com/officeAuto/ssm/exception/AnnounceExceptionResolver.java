package com.officeAuto.ssm.exception;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AnnounceExceptionResolver implements HandlerExceptionResolver {


    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
       e.printStackTrace();

        AnnounceException announceException = null;

        String message = null;
        if(e instanceof AnnounceException){
            announceException = (AnnounceException)e;
        }else{
            //针对非CustomException异常，对这类重新构造成一个CustomException，异常信息为“未知错误”
            announceException= new AnnounceException("未知错误");
        }

        message = announceException.getMessage();

        httpServletRequest.setAttribute("message", message);


        try {
            //转向到错误 页面
            httpServletRequest.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(httpServletRequest, httpServletResponse);
        } catch (ServletException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        } catch (IOException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }

        return new ModelAndView();
    }
}
