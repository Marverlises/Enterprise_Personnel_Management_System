package com.by.coursedesign.entity;

/**
 * @Author Baiyu
 * @Time 2023/6/20 9:27
 * @StudentNumber 2018217662
 * @Description
 * -- 岗位信息表(job)
 *CREATE TABLE job (
 *   position_id INT PRIMARY KEY,
 *   position_name VARCHAR(50),
 *   responsibilities VARCHAR(512)
 * );
 */
public class Job {
    private Integer position_id;
    private String position_name;
    private String responsibilities;
    
    public Job(Integer position_id, String position_name, String responsibilities) {
        this.position_id = position_id;
        this.position_name = position_name;
        this.responsibilities = responsibilities;
    }
    
    public Job() {
    }
    
    public Integer getPosition_id() {
        return position_id;
    }
    
    public void setPosition_id(Integer position_id) {
        this.position_id = position_id;
    }
    
    public String getPosition_name() {
        return position_name;
    }
    
    public void setPosition_name(String position_name) {
        this.position_name = position_name;
    }
    
    public String getResponsibilities() {
        return responsibilities;
    }
    
    public void setResponsibilities(String responsibilities) {
        this.responsibilities = responsibilities;
    }
    
    @Override
    public String toString() {
        return "Job{" +
                "position_id=" + position_id +
                ", position_name='" + position_name + '\'' +
                ", responsibilities='" + responsibilities + '\'' +
                '}';
    }
}
