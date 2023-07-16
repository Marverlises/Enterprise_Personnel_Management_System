package com.by.coursedesign.dao.impl;

import com.by.coursedesign.dao.BasicDAO;
import com.by.coursedesign.dao.EmployeeDAO;
import com.by.coursedesign.entity.Employee;

import java.util.List;

/**
 * @Author Baiyu
 * @Time 2023/6/16 9:12
 * @StudentNumber 2018217662
 * @Description
 */
public class EmployeeDAOImpl extends BasicDAO implements EmployeeDAO {
    @Override
    public List<Employee> queryAllEmployees() {
        String sql = "SELECT * FROM employee";
        return queryMulti(sql, Employee.class);
    }
    
    @Override
    public boolean updateEmployee(Employee employee) {
        String sql = "UPDATE employee\n" +
                "SET \n" +
                "  employee_id = ?,\n" +
                "  employee_name = ?,\n" +
                "  gender = ?,\n" +
                "  birthdate = ?,\n" +
                "  phone_number = ?,\n" +
                "  address = ?,\n" +
                "  email = ?,\n" +
                "  hire_date = ?,\n" +
                "  position_id = ?,\n" +
                "  department_id = ?, \n" +
                "  qualification = ? \n" +
                "\tWHERE employee_id = ?";
        int update = update(sql, employee.getEmployee_id(), employee.getEmployee_name(), employee.getGender(),
                employee.getBirthdate(),
                employee.getPhone_number(), employee.getAddress(), employee.getEmail(),
                employee.getHire_date(), employee.getPosition_id(), employee.getDepartment_id(),
                employee.getQualification(), employee.getEmployee_id());
        return done(update);
    }
    
    @Override
    public boolean addEmployee(Employee employee) {
        String sql = "INSERT INTO employee (employee_id, employee_name, gender, birthdate, phone_number, address, " +
                "email, hire_date, position_id, department_id, qualification)\n" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int update = update(sql, employee.getEmployee_id(), employee.getEmployee_name(), employee.getGender(),
                employee.getBirthdate(),
                employee.getPhone_number(), employee.getAddress(), employee.getEmail(),
                employee.getHire_date(), employee.getPosition_id(), employee.getDepartment_id(),
                employee.getQualification());
        return done(update);
    }
    
    @Override
    public List<Employee> queryFilterEmployees(int age, int gender, int qualification) {
        String sql = null;
        String sex = null;
        String qua = null;
        if (gender == 1) {
            sex = "male";
        } else if (gender == 2) {
            sex = "female";
        }
        
        if (qualification == 1) {
            qua = "本科学历以下";
        } else if (qualification == 2) {
            qua = "本科文凭";
        } else if (qualification == 3) {
            qua = "硕士文凭";
        } else if (qualification == 4) {
            qua = "硕士以上学历";
        }
        if (age == 10) {
            sql = "SELECT * FROM employee\n" +
                    "WHERE gender = ?\n" +
                    "  AND qualification = ?\n" +
                    "  AND TIMESTAMPDIFF(YEAR, birthdate, CURDATE()) >= 50;\n";
            return queryMulti(sql, Employee.class, sex, qua);
        } else {
            sql = "SELECT * FROM employee\n" +
                    "WHERE gender = ?\n" +
                    "  AND qualification = ?\n" +
                    "  AND TIMESTAMPDIFF(YEAR, birthdate, CURDATE()) < ?;\n";
            return queryMulti(sql, Employee.class, sex, qua, age);
        }
    }
    
    @Override
    public boolean deleteEmployeeByID(int id) {
        String sql = "DELETE FROM `employee` WHERE `employee_id` = ?";
        int update = update(sql, id);
        return done(update);
    }
}
