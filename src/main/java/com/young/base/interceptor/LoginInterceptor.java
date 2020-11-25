package com.young.base.interceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.young.base.constant.Const;
import com.young.base.utils.PublicUtils;
import com.young.model.Staff;

import jodd.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
 
@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
 
    //这个方法是在访问接口之前执行的，我们只需要在这里写验证登陆状态的业务逻辑，就可以在用户调用指定接口之前验证登陆状态了
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    	String url=request.getRequestURL().toString();
    	String ip = PublicUtils.getIpAddrByRequest(request);
    	if(ip.length() > 15) {
    		response.sendError(404);
    		log.error(ip + "拦截隐藏访问");
    		return false;
    	}
    	//每一个项目对于登陆的实现逻辑都有所区别，我这里使用最简单的Session提取User来验证登陆。
        HttpSession session = request.getSession();
        //这里的User是登陆时放入session的
        Staff staff = (Staff)session.getAttribute(Const.LOGIN_SESSION_STAFF);
        //如果session中没有user，表示没登陆
        if (null == staff){
            //这个方法返回false表示忽略当前请求，如果一个用户调用了需要登陆才能使用的接口，如果他没有登陆这里会直接忽略掉
            //当然你可以利用response给用户返回一些提示信息，告诉他没登陆
             request.getRequestDispatcher("/admin").forward(request, response);
             return false;
        }else {
            return true;    //如果session里有user，表示该用户已经登陆，放行，用户即可继续调用自己需要的接口
        }
    }
 
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }
 
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }
}