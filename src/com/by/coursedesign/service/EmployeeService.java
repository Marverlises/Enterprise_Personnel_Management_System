package com.by.coursedesign.service;

import com.by.coursedesign.entity.Employee;

import java.util.List;

/**
 * @Author Baiyu
 * @Time 2023/6/16 9:21
 * @StudentNumber 2018217662
 * @Description
 */
public interface EmployeeService {
    
    /**
     * 显示所有员工
     * @return
     */
    public List<Employee> showAllEmployees();
    
    /**
     * 更新一个员工信息
     * @param employee
     * @return
     */
    public boolean updateEmployee(Employee employee);
    
    /**
     * 根据员工ID删除一个员工信息
     * @param id
     * @return
     */
    public boolean deleteEmployeeByID(Integer id);
    
    /**
     * 添加一个员工信息
     * @param employee
     * @return
     */
    public boolean addEmployee(Employee employee);
    
    public List<Employee> queryFilterEmployees(int age, int gender, int qualification);
    
}
