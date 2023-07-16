package com.by.coursedesign.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author Baiyu
 * @Time 2023/6/24 19:29
 * @StudentNumber 2018217662
 * @Description
 */
public class WebUtils {
    public static boolean isAjaxRequest(HttpServletRequest request) {
        return "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
    }
}
