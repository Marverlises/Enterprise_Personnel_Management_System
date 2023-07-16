package com.by.coursedesign.service;

import com.by.coursedesign.entity.Department;
import com.by.coursedesign.entity.Employee;

import java.util.List;

/**
 * @Author Baiyu
 * @Time 2023/6/19 10:29
 * @StudentNumber 2018217662
 * @Description
 */
public interface DepartmentService {
    public List<Department> queryAllDepartment();
    
    public boolean deleteDepartmentById(int id);
    
    public boolean updateDepartment(Department department);
    
    public boolean addDepartment(Department department);
    
    public List<Employee> queryDepartAllEmp(int departId);
}
