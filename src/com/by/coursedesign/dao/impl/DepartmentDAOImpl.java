package com.by.coursedesign.dao.impl;

import com.by.coursedesign.dao.BasicDAO;
import com.by.coursedesign.dao.DepartmentDAO;
import com.by.coursedesign.entity.Department;
import com.by.coursedesign.entity.Employee;

import java.util.List;

/**
 * @Author Baiyu
 * @Time 2023/6/19 10:22
 * @StudentNumber 2018217662
 * @Description
 */
public class DepartmentDAOImpl extends BasicDAO implements DepartmentDAO {
    @Override
    public List<Department> queryAllDepartments() {
        String sql = "SELECT * FROM department;";
        return queryMulti(sql, Department.class);
    }
    
    @Override
    public boolean deleteDepartmentById(int id) {
        String sql = "DELETE FROM department WHERE department_id = ?;";
        System.out.println("删除的ID为" + id);
        int update = update(sql, id);
        return done(update);
    }
    
    @Override
    public boolean updateDepartment(Department department) {
        String sql = "UPDATE department SET " +
                "department_id = ?, department_name = ?, " +
                "department_manager = ?, employee_id = ? " +
                "WHERE department_id = ?;";
        int update = update(sql, department.getDepartment_id(), department.getDepartment_name()
                , department.getDepartment_manager(), department.getEmployee_id(), department.getDepartment_id());
        return done(update);
    }
    
    @Override
    public boolean addDepartment(Department department) {
        String sql = "INSERT INTO department (department_id, department_name, department_manager, employee_id) " +
                "VALUES (?, ?, ?, ?);";
        int update = update(sql, department.getDepartment_id(), department.getDepartment_name(),
                department.getDepartment_manager(), department.getEmployee_id());
        return done(update);
    }
    
    @Override
    public List<Employee> queryDepartAllEmp(int departId) {
        String sql = "SELECT * FROM employee WHERE department_id = ?";
        return queryMulti(sql, Employee.class, departId);
    }
}
