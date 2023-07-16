package com.by.coursedesign.entity;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @Author Baiyu
 * @Time 2023/6/20 16:04
 * @StudentNumber 2018217662
 * @Description
 *
 * -- 考勤状况表(attendance)
 * CREATE TABLE attendance (
 *   attendance_id INT AUTO_INCREMENT,
 *   employee_id INT,
 * 	 -- 打卡时间
 *   log_time DATETIME,
 *   -- 打卡类型（上班/下班...）
 *   log_type TINYINT,
 * 	PRIMARY KEY(attendance_id, employee_id),
 * 	FOREIGN KEY (employee_id) REFERENCES employee(employee_id)
 * );
 */
public class Attendance {
    private Integer attendance_id;
    private Integer employee_id;
    private Timestamp log_time;
    private Integer log_type;
    private Integer late_type;
    
    public Attendance() {
    }
    
    public Attendance(Integer attendance_id, Timestamp log_time) {
        this.attendance_id = attendance_id;
        this.log_time = log_time;
    }
    
    public Attendance(Integer attendance_id, Integer employee_id, Timestamp log_time, Integer log_type,
                      Integer late_type) {
        this.attendance_id = attendance_id;
        this.employee_id = employee_id;
        this.log_time = log_time;
        this.log_type = log_type;
        this.late_type = late_type;
    }
    
    public Integer getLate_type() {
        return late_type;
    }
    
    public void setLate_type(Integer late_type) {
        this.late_type = late_type;
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
    
    @Override
    public String toString() {
        return "Attendance{" +
                "attendance_id=" + attendance_id +
                ", employee_id=" + employee_id +
                ", log_time=" + log_time +
                ", log_type=" + log_type +
                ", late_type=" + late_type +
                '}';
    }
}
