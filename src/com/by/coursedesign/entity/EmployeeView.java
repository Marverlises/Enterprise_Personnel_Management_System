package com.by.coursedesign.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author Baiyu
 * @Time 2023/6/25 10:07
 * @StudentNumber 2018217662
 * @Description
 * -- 创建普通员工视图
 * CREATE VIEW employee_view AS
 * SELECT t1.employee_id, t1.employee_name, t1.gender, t1.birthdate, t1.phone_number,
 * t1.address, t1.email, t1.hire_date, t1.position_id, t1.department_id, t2.salary_amount
 * FROM employee t1
 * JOIN salary t2 ON t1.employee_id = t2.employee_id and t2.salary_category = "Monthly";
 */
public class EmployeeView {
    private Integer employee_id;
    private String employee_name;
    private String gender;
    private Date birthdate;
    private String phone_number;
    private String address;
    private String email;
    private Date hire_date;
    private Integer position_id;
    private Integer department_id;
    private BigDecimal salary_amount;
    
    public EmployeeView() {
    }
    
    public EmployeeView(Integer employee_id, String employee_name, String gender, Date birthdate, String phone_number
            , String address, String email, Date hire_date, Integer position_id, Integer department_id,
                        BigDecimal salary_amount) {
        this.employee_id = employee_id;
        this.employee_name = employee_name;
        this.gender = gender;
        this.birthdate = birthdate;
        this.phone_number = phone_number;
        this.address = address;
        this.email = email;
        this.hire_date = hire_date;
        this.position_id = position_id;
        this.department_id = department_id;
        this.salary_amount = salary_amount;
    }
    
    @Override
    public String toString() {
        return "EmployeeView{" +
                "employee_id=" + employee_id +
                ", employee_name='" + employee_name + '\'' +
                ", gender='" + gender + '\'' +
                ", birthdate=" + birthdate +
                ", phone_number='" + phone_number + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", hire_date=" + hire_date +
                ", position_id=" + position_id +
                ", department_id=" + department_id +
                ", salary_amount=" + salary_amount +
                '}';
    }
    
    public Integer getEmployee_id() {
        return employee_id;
    }
    
    public void setEmployee_id(Integer employee_id) {
        this.employee_id = employee_id;
    }
    
    public String getEmployee_name() {
        return employee_name;
    }
    
    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }
    
    public String getGender() {
        return gender;
    }
    
    public void setGender(String gender) {
        this.gender = gender;
    }
    
    public Date getBirthdate() {
        return birthdate;
    }
    
    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }
    
    public String getPhone_number() {
        return phone_number;
    }
    
    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public Date getHire_date() {
        return hire_date;
    }
    
    public void setHire_date(Date hire_date) {
        this.hire_date = hire_date;
    }
    
    public Integer getPosition_id() {
        return position_id;
    }
    
    public void setPosition_id(Integer position_id) {
        this.position_id = position_id;
    }
    
    public Integer getDepartment_id() {
        return department_id;
    }
    
    public void setDepartment_id(Integer department_id) {
        this.department_id = department_id;
    }
    
    public BigDecimal getSalary_amount() {
        return salary_amount;
    }
    
    public void setSalary_amount(BigDecimal salary_amount) {
        this.salary_amount = salary_amount;
    }
}
