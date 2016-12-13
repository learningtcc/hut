package com.hut.web.interceptors;

import com.hut.common.pojos.User;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Jared on 2016/12/11.
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("loginUser");
        if (user!=null)
            return true;
        response.sendRedirect("http://host.com/outsideOfficeHours.html");
        return false;
    }
}
