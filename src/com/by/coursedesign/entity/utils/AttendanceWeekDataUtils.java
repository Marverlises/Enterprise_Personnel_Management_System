package com.by.coursedesign.entity.utils;

import com.by.coursedesign.entity.Attendance;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author Baiyu
 * @Time 2023/6/23 15:06
 * @StudentNumber 2018217662
 * @Description
 */
public class AttendanceWeekDataUtils {
    private ArrayList<Integer> dayLength;       //当日总记录数
    private ArrayList<Integer> dayNormalNum;    //当日正常出勤人数
    private ArrayList<Integer> dayLateNum;      //当日缺勤人数
    private ArrayList<LocalDate> day;           //日期
    
    public AttendanceWeekDataUtils(ArrayList<Integer> dayLength, ArrayList<Integer> dayNormalNum,
                                   ArrayList<Integer> dayLateNum, ArrayList<LocalDate> day) {
        this.dayLength = dayLength;
        this.dayNormalNum = dayNormalNum;
        this.dayLateNum = dayLateNum;
        this.day = day;
    }
    
    public AttendanceWeekDataUtils() {
    }
    
    @Override
    public String toString() {
        return "AttendanceWeekDataUtils{" +
                "dayLength=" + dayLength +
                ", dayNormalNum=" + dayNormalNum +
                ", dayLateNum=" + dayLateNum +
                ", day=" + day +
                '}';
    }
    
    public ArrayList<Integer> getDayLength() {
        return dayLength;
    }
    
    public void setDayLength(ArrayList<Integer> dayLength) {
        this.dayLength = dayLength;
    }
    
    public ArrayList<Integer> getDayNormalNum() {
        return dayNormalNum;
    }
    
    public void setDayNormalNum(ArrayList<Integer> dayNormalNum) {
        this.dayNormalNum = dayNormalNum;
    }
    
    public ArrayList<Integer> getDayLateNum() {
        return dayLateNum;
    }
    
    public void setDayLateNum(ArrayList<Integer> dayLateNum) {
        this.dayLateNum = dayLateNum;
    }
    
    public ArrayList<LocalDate> getDay() {
        return day;
    }
    
    public void setDay(ArrayList<LocalDate> day) {
        this.day = day;
    }
}
