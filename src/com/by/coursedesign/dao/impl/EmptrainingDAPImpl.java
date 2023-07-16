package com.by.coursedesign.dao.impl;

import com.by.coursedesign.dao.BasicDAO;
import com.by.coursedesign.dao.EmptrainingDAO;
import com.by.coursedesign.entity.Employee;
import com.by.coursedesign.entity.Emptraining;

import java.util.List;

/**
 * @Author Baiyu
 * @Time 2023/6/24 14:44
 * @StudentNumber 2018217662
 * @Description
 */
public class EmptrainingDAPImpl extends BasicDAO implements EmptrainingDAO {
    @Override
    public boolean startTrain(Emptraining emptraining) {
        String sql = "INSERT INTO employee_training (employee_id, training_id, done_training)\n" +
                "VALUES (?, ?, NULL);";
        int update = update(sql, emptraining.getEmployee_id(), emptraining.getTraining_id());
        return done(update);
    }
    
    @Override
    public List<Employee> queryTrainEmpById(String trainId) {
        String sql = "SELECT * FROM employee WHERE employee_id IN (SELECT employee_id \n" +
                "FROM employee_training WHERE training_id = '" + trainId + "')";
        return queryMulti(sql, Employee.class);
    }
}
