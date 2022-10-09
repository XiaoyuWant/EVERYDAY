package com.xiaoyu.util;

import com.xiaoyu.entity.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {
    public static String getFieldFromCookies(Cookie[] cookies, String field) {
        for(Cookie cookie : cookies) {
            try{
                if(cookie.getName().equals(field)) {
                    return cookie.getValue();
                }
            } catch (Exception e){
              e.printStackTrace();
            }
        }
        return null;
    }


    public static void addCookieForHttpResponse(HttpServletResponse response, String userToken) {
        Cookie cookie = new Cookie("user", userToken);
        cookie.setPath("/");
        cookie.setMaxAge(100000000);
        response.addCookie(cookie);
    }


}
