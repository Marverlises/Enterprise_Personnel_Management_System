package com.by.coursedesign.service.impl;

import com.by.coursedesign.dao.DepartmentDAO;
import com.by.coursedesign.dao.impl.DepartmentDAOImpl;
import com.by.coursedesign.entity.Department;
import com.by.coursedesign.entity.Employee;
import com.by.coursedesign.service.DepartmentService;

import java.util.List;

/**
 * @Author Baiyu
 * @Time 2023/6/19 10:33
 * @StudentNumber 2018217662
 * @Description
 */
public class DepartmentServiceImpl implements DepartmentService {
    private DepartmentDAO departmentDAO = new DepartmentDAOImpl();
    
    /**
     * 查询所有部门信息
     *
     * @return
     */
    @Override
    public List<Department> queryAllDepartment() {
        List<Department> departments = departmentDAO.queryAllDepartments();
        return departments;
    }
    
    @Override
    public boolean deleteDepartmentById(int id) {
        return departmentDAO.deleteDepartmentById(id);
    }
    
    @Override
    public boolean updateDepartment(Department department) {
        return departmentDAO.updateDepartment(department);
    }
    
    @Override
    public boolean addDepartment(Department department) {
        return departmentDAO.addDepartment(department);
    }
    
    @Override
    public List<Employee> queryDepartAllEmp(int departId) {
        return departmentDAO.queryDepartAllEmp(departId);
    }
    
    
}
