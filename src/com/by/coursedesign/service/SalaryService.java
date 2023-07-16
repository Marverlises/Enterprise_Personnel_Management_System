package com.by.coursedesign.service;

import com.by.coursedesign.entity.Salary;

import java.util.List;

/**
 * @Author Baiyu
 * @Time 2023/6/23 17:05
 * @StudentNumber 2018217662
 * @Description
 */
public interface SalaryService {
    public List<Salary> queryAllSalary();
    
    public boolean deleteBySalaryEmployeeId(String salaryId, int employeeId);
    
    public boolean insertItem(Salary salary);
    
    public boolean updateItem(Salary salary);
}