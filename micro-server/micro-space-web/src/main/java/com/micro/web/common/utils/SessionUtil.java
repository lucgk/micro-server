package com.micro.web.common.utils;

import com.micro.web.common.exception.CommonException;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtil {


    /**
     * 获取当前HttpRequst
     * @return
     */
    public static HttpServletRequest getHttpRequest(){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return  requestAttributes.getRequest();
    }

    /**
     * @desc 获取当前request 关联的session ,如果当前request 没有，创建一个
     *
     * @return
     */
    public static HttpSession getSession(){
        return  getHttpRequest().getSession();
    }


    /**
     * @desc
     *  create如果为true,如果当前request的session为空,创建一个,如果不为空则返回当前request关联的session.
     *  create如果为false,如果当前request的session为空,返回null;如果不为空则返回当前request关联的session.
     * @param create  为ture 将会不存在将会自动创建
     * @return
     */
    public static HttpSession getSession(boolean create){
        return  getHttpRequest().getSession(create);
    }


    public static boolean isLogin(){
        HttpSession session = SessionUtil.getSession(false);
        if(null == session){
            return false;
        }
        return true;
    }
}
