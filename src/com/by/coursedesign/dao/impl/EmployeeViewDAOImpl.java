package com.by.coursedesign.dao.impl;

import com.by.coursedesign.dao.BasicDAO;
import com.by.coursedesign.dao.EmployeeViewDAO;
import com.by.coursedesign.entity.EmployeeView;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author Baiyu
 * @Time 2023/6/25 10:10
 * @StudentNumber 2018217662
 * @Description
 */
public class EmployeeViewDAOImpl extends BasicDAO<EmployeeView> implements EmployeeViewDAO {
    @Override
    public EmployeeView queryAllDataById(int id) {
        String sql = "SELECT * FROM employee_view WHERE employee_id = ?;";
        return querySingle(sql, EmployeeView.class, id);
    }
    
    @Override
    public boolean startWork(int id) {
        String sql = "INSERT INTO attendance (employee_id, log_time, log_type, late_type) " +
                "VALUES (?, ?, 0, NULL);";
        int update = update(sql, id, LocalDateTime.now());
        return done(update);
    }
    
    @Override
    public boolean endWork(int id) {
        String sql = "INSERT INTO attendance (employee_id, log_time, log_type, late_type) " +
                "VALUES (?, ?, 1, NULL);";
        int update = update(sql, id, LocalDateTime.now());
        return done(update);
    }
}
