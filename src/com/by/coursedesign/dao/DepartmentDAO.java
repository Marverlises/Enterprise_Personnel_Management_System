package com.by.coursedesign.dao;

import com.by.coursedesign.entity.Department;
import com.by.coursedesign.entity.Employee;

import java.util.List;

/**
 * @Author Baiyu
 * @Time 2023/6/19 10:21
 * @StudentNumber 2018217662
 * @Description
 */
public interface DepartmentDAO {
    public List<Department> queryAllDepartments();
    
    public boolean deleteDepartmentById(int id);
    
    public boolean updateDepartment(Department department);
    
    public boolean addDepartment(Department department);
    
    public List<Employee> queryDepartAllEmp(int departId);
}
