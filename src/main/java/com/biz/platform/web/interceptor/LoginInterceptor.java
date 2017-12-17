package com.biz.platform.web.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by huangdonghua on 2017/11/7.
 */
public class LoginInterceptor implements HandlerInterceptor {



    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {

//        String url = request.getRequestURI();
//
//        List<String> anonURLs = ResourcesUtil.gekeyList("anonymousURL");
//        for (String str:
//                anonURLs) {
//            if(url.indexOf(str) >= 0){
//               return true;
//            }
//        }
//
//        if(url.indexOf("/authCode") >= 0 || url.indexOf("/login") >= 0){
//            return true;
//        }
//
//        SystemUser user = (SystemUser) request.getSession().getAttribute("loginUser");
//        if(user != null && !user.getId().equals("")){
//            List<SysPermission> permissions = user.getPermissions();
//            for (SysPermission permissionURL:
//                 permissions) {
//                if(url.indexOf(permissionURL.getUrl()) >= 0){
//                    return true;
//                }
//            }
//        }else {
//            request.getRequestDispatcher("/login.jsp").forward(request, response);
//        }
//
//        return false;
        return true;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
