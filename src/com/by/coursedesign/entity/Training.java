package com.by.coursedesign.entity;

import java.util.Date;

/**
 * @Author Baiyu
 * @Time 2023/6/24 8:46
 * @StudentNumber 2018217662
 * @Description
 * -- 培训信息表(training)
 * CREATE TABLE training (
 *   training_id INT PRIMARY KEY,
 *   training_name VARCHAR(50),
 *   training_description VARCHAR(512),
 *   start_date DATE,
 *   end_date DATE,
 *   total_participants INT
 * );
 */
public class Training {
    private String  training_id;
    private String training_name;
    private String training_description;
    private Date start_date;
    private Date end_date;
    private Integer total_participants;
    
    public Training() {
    }
    
    public Training(String training_id, String training_name, String training_description, Date start_date,
                    Date end_date, Integer total_participants) {
        this.training_id = training_id;
        this.training_name = training_name;
        this.training_description = training_description;
        this.start_date = start_date;
        this.end_date = end_date;
        this.total_participants = total_participants;
    }
    
    @Override
    public String toString() {
        return "Training{" +
                "training_id='" + training_id + '\'' +
                ", training_name='" + training_name + '\'' +
                ", training_description='" + training_description + '\'' +
                ", start_date=" + start_date +
                ", end_date=" + end_date +
                ", total_participants=" + total_participants +
                '}';
    }
    
    public String getTraining_id() {
        return training_id;
    }
    
    public void setTraining_id(String training_id) {
        this.training_id = training_id;
    }
    
    public String getTraining_name() {
        return training_name;
    }
    
    public void setTraining_name(String training_name) {
        this.training_name = training_name;
    }
    
    public String getTraining_description() {
        return training_description;
    }
    
    public void setTraining_description(String training_description) {
        this.training_description = training_description;
    }
    
    public Date getStart_date() {
        return start_date;
    }
    
    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }
    
    public Date getEnd_date() {
        return end_date;
    }
    
    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }
    
    public Integer getTotal_participants() {
        return total_participants;
    }
    
    public void setTotal_participants(Integer total_participants) {
        this.total_participants = total_participants;
    }
}
