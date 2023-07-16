package com.by.coursedesign.dao;

import com.by.coursedesign.entity.Employee;

import java.util.List;

/**
 * @Author Baiyu
 * @Time 2023/6/16 9:12
 * @StudentNumber 2018217662
 * @Description
 */
public interface EmployeeDAO {
    public List<Employee> queryAllEmployees();
    
    public boolean deleteEmployeeByID(int id);
    
    public boolean updateEmployee(Employee employee);
    
    public boolean addEmployee(Employee employee);
    
    public List<Employee> queryFilterEmployees(int age, int gender, int qualification);
}
