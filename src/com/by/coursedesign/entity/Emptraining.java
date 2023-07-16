package com.by.coursedesign.entity;

/**
 * @Author Baiyu
 * @Time 2023/6/24 11:16
 * @StudentNumber 2018217662
 * @Description
 * -- 参加培训关系表(employee_training)
 * CREATE TABLE employee_training (
 *   employee_id INT,
 *   training_id char(8),
 *   -- 0表示未完成， 1表示完成
 *   done_training TINYINT,
 *   PRIMARY KEY (employee_id, training_id),
 * 	FOREIGN KEY (employee_id) REFERENCES employee(employee_id),
 * 	FOREIGN KEY (training_id) REFERENCES training(training_id)
 * );
 */
public class Emptraining {
    private Integer employee_id;
    private String training_id;
    private  Integer done_training;
    
    public Emptraining(Integer employee_id, String training_id, Integer done_training) {
        this.employee_id = employee_id;
        this.training_id = training_id;
        this.done_training = done_training;
    }
    
    public Emptraining() {
    }
    
    @Override
    public String toString() {
        return "Emptraining{" +
                "employee_id=" + employee_id +
                ", training_id='" + training_id + '\'' +
                ", done_training=" + done_training +
                '}';
    }
    
    public Integer getEmployee_id() {
        return employee_id;
    }
    
    public void setEmployee_id(Integer employee_id) {
        this.employee_id = employee_id;
    }
    
    public String getTraining_id() {
        return training_id;
    }
    
    public void setTraining_id(String training_id) {
        this.training_id = training_id;
    }
    
    public Integer getDone_training() {
        return done_training;
    }
    
    public void setDone_training(Integer done_training) {
        this.done_training = done_training;
    }
}
