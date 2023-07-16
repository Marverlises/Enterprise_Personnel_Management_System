package com.by.coursedesign.entity;


import java.util.Date;

/**
 * @Author Baiyu
 * @Time 2023/6/16 9:07
 * @StudentNumber 2018217662
 * @Description
 *
 * -- 员工档案信息表(employee)
 * CREATE TABLE employee (
 *   employee_id INT PRIMARY KEY,
 *   employee_name VARCHAR(50) NOT NULL,
 *   gender ENUM('male', 'female') NOT NULL,
 *   birthdate DATE NOT NULL,
 *   phone_number VARCHAR(20),
 *   address VARCHAR(100),
 *   email VARCHAR(50),
 *   hire_date DATE,
 *   position_id INT REFERENCES job(position_id),
 *   department_id INT REFERENCES department(department_id)
 * );
 */
public class Employee {
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
    private String qualification;
    
    public Employee() {
    }
    
    public Employee(Integer employee_id, String employee_name, String gender, Date birthdate, String phone_number,
                    String address, String email, Date hire_date, Integer position_id, Integer department_id, String qualification) {
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
        this.qualification = qualification;
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
    
    public String getQualification() {
        return qualification;
    }
    
    public void setQualification(String qualification) {
        this.qualification = qualification;
    }
    
    @Override
    public String toString() {
        return "Employee{" +
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
                ", qualification='" + qualification + '\'' +
                '}';
    }
}
