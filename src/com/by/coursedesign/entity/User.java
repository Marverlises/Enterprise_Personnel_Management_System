package com.by.coursedesign.entity;


import java.sql.Timestamp;
import java.util.Date;

/**
 * @Author Baiyu
 * @Time 2023/6/13 9:13
 * @StudentNumber 2018217662
 * @Description
 *
 * CREATE TABLE user_info (
 *   user_id INT PRIMARY KEY AUTO_INCREMENT,
 *   username VARCHAR(50) NOT NULL UNIQUE,
 *   user_password VARCHAR(255) NOT NULL,
 *   login_time DATETIME,
 *   -- 0为普通员工，1为管理员
 *   permission_level BIT,
 *   employee_id INT,
 *   FOREIGN KEY (employee_id) REFERENCES employee(employee_id)
 *)
 */

public class User {
    private Integer user_id;
    private String username;
    private String user_password;
    private Timestamp login_time;
    private Integer permission_level;
    private Integer employee_id;
    
    public User() {
    }
    
    public User(Integer user_id, String username, String user_password, Timestamp login_time,
                Integer permission_level, Integer employee_id) {
        this.user_id = user_id;
        this.username = username;
        this.user_password = user_password;
        this.login_time = login_time;
        this.permission_level = permission_level;
        this.employee_id = employee_id;
    }
    
    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", username='" + username + '\'' +
                ", user_password='" + user_password + '\'' +
                ", login_time=" + login_time +
                ", permission_level=" + permission_level +
                ", employee_id=" + employee_id +
                '}';
    }
    
    public Integer getUser_id() {
        return user_id;
    }
    
    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getUser_password() {
        return user_password;
    }
    
    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }
    
    public Timestamp getLogin_time() {
        return login_time;
    }
    
    public void setLogin_time(Timestamp login_time) {
        this.login_time = login_time;
    }
    
    public Integer getPermission_level() {
        return permission_level;
    }
    
    public void setPermission_level(Integer permission_level) {
        this.permission_level = permission_level;
    }
    
    public Integer getEmployee_id() {
        return employee_id;
    }
    
    public void setEmployee_id(Integer employee_id) {
        this.employee_id = employee_id;
    }
}
