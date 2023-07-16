package com.by.coursedesign.entity.utils;

import java.sql.Timestamp;

/**
 * @Author Baiyu
 * @Time 2023/6/21 8:23
 * @StudentNumber 2018217662
 * @Description
 */
public class AttendanceDataUtils {
    private Integer attendance_id;
    private Integer employee_id;
    private Timestamp log_time;
    private Integer log_type;
    //0为正常出勤，1为迟到，2为早退
    private Integer late_type;
    
    
    public AttendanceDataUtils() {
    }
    
    public AttendanceDataUtils(Integer attendance_id, Integer employee_id, Timestamp log_time, Integer log_type,
                               Integer late_type) {
        this.attendance_id = attendance_id;
        this.employee_id = employee_id;
        this.log_time = log_time;
        this.log_type = log_type;
        this.late_type = late_type;
    }
    
    @Override
    public String toString() {
        return "AttendanceDataUtils{" +
                "attendance_id=" + attendance_id +
                ", employee_id=" + employee_id +
                ", log_time=" + log_time +
                ", log_type=" + log_type +
                ", late_type=" + late_type +
                '}';
    }
    
    public Integer getAttendance_id() {
        return attendance_id;
    }
    
    public void setAttendance_id(Integer attendance_id) {
        this.attendance_id = attendance_id;
    }
    
    public Integer getEmployee_id() {
        return employee_id;
    }
    
    public void setEmployee_id(Integer employee_id) {
        this.employee_id = employee_id;
    }
    
    public Timestamp getLog_time() {
        return log_time;
    }
    
    public void setLog_time(Timestamp log_time) {
        this.log_time = log_time;
    }
    
    public Integer getLog_type() {
        return log_type;
    }
    
    public void setLog_type(Integer log_type) {
        this.log_type = log_type;
    }
    
    public Integer getLate_type() {
        return late_type;
    }
    
    public void setLate_type(Integer late_type) {
        this.late_type = late_type;
    }
}
