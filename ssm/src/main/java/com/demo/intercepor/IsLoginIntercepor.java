package com.demo.intercepor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by zhaojr on 18-12-6.
 * 是否登录拦截器
 */
public class IsLoginIntercepor implements HandlerInterceptor{

    /**
     * 该方法将在请求处理之前进行调用,只有该方法返回true,才会继续执行后续的Interceptor和Controller
     * 当返回值为true 时就会继续调用下一个Interceptor的preHandle方法
     * 如果已经是最后一个Interceptor的时候就会是调用当前请求的Controller方法
     * 是否登录
     * 权限验证
     */
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o)
            throws Exception {
        String url = httpServletRequest.getRequestURI();
        System.out.println("url = " + url);
        return true;
    }

    /**
     * 该方法将在请求处理之后,DispatcherServlet进行视图返回渲染之前进行调用
     * 可以在这个方法中对Controller处理之后的ModelAndView对象进行操作
     */
    public void postHandle(HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            Object o,
            ModelAndView modelAndView) throws Exception {

    }

    /**
     * 该方法也是需要当前对应的Interceptor的preHandle方法的返回值为true时才会执行
     * 该方法将在整个请求结束之后，也就是在DispatcherServlet渲染了对应的视图之后执行
     * 用于进行资源清理
     */
    public void afterCompletion(HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            Object o,
            Exception e) throws Exception {

    }
}
