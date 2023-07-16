package com.by.coursedesign.entity;

/**
 * @Author Baiyu
 * @Time 2023/6/19 9:21
 * @StudentNumber 2018217662
 * @Description
 * -- 部门信息表(department)
 * CREATE TABLE department (
 *   department_id INT PRIMARY KEY,
 *   department_name VARCHAR(50),
 *   department_manager VARCHAR(50),
 * 	 employee_id INT
 * );
 */
public class Department {
    private  Integer department_id;
    private  String department_name;
    private String department_manager;
    private Integer employee_id;
    
    public Department(Integer department_id, String department_name, String department_manager, Integer employee_id) {
        this.department_id = department_id;
        this.department_name = department_name;
        this.department_manager = department_manager;
        this.employee_id = employee_id;
    }
    
    public Department() {
    }
    
    public Integer getDepartment_id() {
        return department_id;
    }
    
    public void setDepartment_id(Integer department_id) {
        this.department_id = department_id;
    }
    
    public String getDepartment_name() {
        return department_name;
    }
    
    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }
    
    public String getDepartment_manager() {
        return department_manager;
    }
    
    public void setDepartment_manager(String department_manager) {
        this.department_manager = department_manager;
    }
    
    public Integer getEmployee_id() {
        return employee_id;
    }
    
    public void setEmployee_id(Integer employee_id) {
        this.employee_id = employee_id;
    }
    
    @Override
    public String toString() {
        return "Department{" +
                "department_id=" + department_id +
                ", department_name='" + department_name + '\'' +
                ", department_manager='" + department_manager + '\'' +
                ", employee_id=" + employee_id +
                '}';
    }
}
