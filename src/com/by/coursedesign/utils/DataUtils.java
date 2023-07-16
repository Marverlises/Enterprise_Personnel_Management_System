package com.by.coursedesign.utils;

import com.by.coursedesign.entity.User;

import java.util.Calendar;
import java.util.Date;

/**
 * @Author Baiyu
 * @Time 2023/6/18 23:16
 * @StudentNumber 2018217662
 * @Description
 */
public class DataUtils {
    public static User user = null;
    //将字符串转成整数,否则返回默认值
    public static int parseInt(String val, int defaultVal) {
        
        try {
            return Integer.parseInt(val);
        } catch (NumberFormatException e) {
            System.out.println(val + " 格式不正确...");
        }
        return defaultVal;
    }
    
    public static boolean isSameDay(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        
        int year1 = cal1.get(Calendar.YEAR);
        int month1 = cal1.get(Calendar.MONTH);
        int day1 = cal1.get(Calendar.DAY_OF_MONTH);
        
        int year2 = cal2.get(Calendar.YEAR);
        int month2 = cal2.get(Calendar.MONTH);
        int day2 = cal2.get(Calendar.DAY_OF_MONTH);
        
        return year1 == year2 && month1 == month2 && day1 == day2;
    }
    
}
