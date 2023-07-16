package com.by.coursedesign.service.impl;

import com.by.coursedesign.dao.EmployeeDAO;
import com.by.coursedesign.dao.impl.EmployeeDAOImpl;
import com.by.coursedesign.entity.Employee;
import com.by.coursedesign.service.EmployeeService;

import java.util.List;

/**
 * @Author Baiyu
 * @Time 2023/6/16 9:22
 * @StudentNumber 2018217662
 * @Description
 */
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeDAO employeeDAO = new EmployeeDAOImpl();
    
    @Override
    public List<Employee> showAllEmployees() {
        List<Employee> employees = employeeDAO.queryAllEmployees();
        return employees;
    }
    
    @Override
    public boolean updateEmployee(Employee employee) {
        return employeeDAO.updateEmployee(employee);
    }
    
    @Override
    public boolean deleteEmployeeByID(Integer id) {
        return employeeDAO.deleteEmployeeByID(id);
    }
    
    @Override
    public boolean addEmployee(Employee employee) {
        return employeeDAO.addEmployee(employee);
    }
    
    @Override
    public List<Employee> queryFilterEmployees(int age, int gender, int qualification) {
        return employeeDAO.queryFilterEmployees(age, gender, qualification);
    }
    
}
