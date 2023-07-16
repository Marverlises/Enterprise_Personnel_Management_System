package com.by.coursedesign.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author Baiyu
 * @Time 2023/6/23 16:57
 * @StudentNumber 2018217662
 * @Description
 * -- 薪资状况表(salary)
 * CREATE TABLE salary (
 *   salary_id INT PRIMARY KEY,
 *   employee_id INT REFERENCES employee(employee_id),
 *   salary_amount NUMERIC(18, 2),
 *   salary_category VARCHAR(50),
 *   payment_date DATE
 * );
 */
public class Salary {
    private String salary_id;
    private Integer employee_id;
    private BigDecimal salary_amount;
    private String salary_category;
    private Date payment_date;
    
    public Salary() {
    }
    
    public Salary(String salary_id, Integer employee_id, BigDecimal salary_amount, String salary_category,
                  Date payment_date) {
        this.salary_id = salary_id;
        this.employee_id = employee_id;
        this.salary_amount = salary_amount;
        this.salary_category = salary_category;
        this.payment_date = payment_date;
    }
    
    @Override
    public String toString() {
        return "Salary{" +
                "salary_id='" + salary_id + '\'' +
                ", employee_id=" + employee_id +
                ", salary_amount=" + salary_amount +
                ", salary_category='" + salary_category + '\'' +
                ", payment_date=" + payment_date +
                '}';
    }
    
    public String getSalary_id() {
        return salary_id;
    }
    
    public void setSalary_id(String salary_id) {
        this.salary_id = salary_id;
    }
    
    public Integer getEmployee_id() {
        return employee_id;
    }
    
    public void setEmployee_id(Integer employee_id) {
        this.employee_id = employee_id;
    }
    
    public BigDecimal getSalary_amount() {
        return salary_amount;
    }
    
    public void setSalary_amount(BigDecimal salary_amount) {
        this.salary_amount = salary_amount;
    }
    
    public String getSalary_category() {
        return salary_category;
    }
    
    public void setSalary_category(String salary_category) {
        this.salary_category = salary_category;
    }
    
    public Date getPayment_date() {
        return payment_date;
    }
    
    public void setPayment_date(Date payment_date) {
        this.payment_date = payment_date;
    }
}
