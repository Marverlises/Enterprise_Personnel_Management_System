package com.by.coursedesign.dao.impl;

import com.by.coursedesign.dao.BasicDAO;
import com.by.coursedesign.dao.SalaryDAO;
import com.by.coursedesign.entity.Salary;

import java.util.List;
import java.util.UUID;

/**
 * @Author Baiyu
 * @Time 2023/6/23 17:00
 * @StudentNumber 2018217662
 * @Description
 */
public class SalaryDAOImpl extends BasicDAO<Salary> implements SalaryDAO {
    public List<Salary> queryAllSalary() {
        String sql = "SELECT * FROM salary";
        return queryMulti(sql, Salary.class);
    }
    
    
    @Override
    public boolean deleteBySalaryEmployeeId(String salaryId, int employeeId) {
        String sql = "DELETE FROM salary WHERE salary_id = ? and employee_id = ?\n";
        int update = update(sql, salaryId, employeeId);
        return done(update);
    }
    
    @Override
    public boolean insertItem(Salary salary) {
        // 生成随机的 UUID
        UUID uuid = UUID.randomUUID();
        String randomString = uuid.toString();
        String salaryId = randomString.substring(0, 18);
        String sql = "INSERT INTO salary (salary_id, employee_id, salary_amount, salary_category, payment_date)\n" +
                "VALUES (?, ?, ?, ?, ?);\n";
        int update = update(sql, salaryId, salary.getEmployee_id(), salary.getSalary_amount(),
                salary.getSalary_category(), salary.getPayment_date());
        return done(update);
    }
    
    @Override
    public boolean updateItem(Salary salary) {
        String sql = "UPDATE salary SET employee_id = ?, salary_amount = ?, salary_category = ?, payment_date" +
                " = ? WHERE salary_id = ?";
        int update = update(sql, salary.getEmployee_id(), salary.getSalary_amount(), salary.getSalary_category(),
                salary.getPayment_date(), salary.getSalary_id());
        return done(update);
    }
}
