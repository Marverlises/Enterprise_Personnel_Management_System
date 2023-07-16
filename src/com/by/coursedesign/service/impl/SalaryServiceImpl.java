package com.by.coursedesign.service.impl;

import com.by.coursedesign.dao.SalaryDAO;
import com.by.coursedesign.dao.impl.SalaryDAOImpl;
import com.by.coursedesign.entity.Salary;
import com.by.coursedesign.service.SalaryService;

import java.util.List;

/**
 * @Author Baiyu
 * @Time 2023/6/23 17:05
 * @StudentNumber 2018217662
 * @Description
 */
public class SalaryServiceImpl implements SalaryService {
    private SalaryDAO salaryDAO = new SalaryDAOImpl();
    
    @Override
    public List<Salary> queryAllSalary() {
        return salaryDAO.queryAllSalary();
    }
    
    @Override
    public boolean deleteBySalaryEmployeeId(String salaryId, int employeeId) {
        return salaryDAO.deleteBySalaryEmployeeId(salaryId, employeeId);
    }
    
    @Override
    public boolean insertItem(Salary salary) {
        return salaryDAO.insertItem(salary);
    }
    
    @Override
    public boolean updateItem(Salary salary) {
        return salaryDAO.updateItem(salary);
    }
}
