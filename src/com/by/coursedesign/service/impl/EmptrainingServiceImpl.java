package com.by.coursedesign.service.impl;

import com.by.coursedesign.dao.EmptrainingDAO;
import com.by.coursedesign.dao.impl.EmptrainingDAPImpl;
import com.by.coursedesign.entity.Employee;
import com.by.coursedesign.entity.Emptraining;
import com.by.coursedesign.service.EmptrainingService;

import java.util.List;

/**
 * @Author Baiyu
 * @Time 2023/6/24 14:51
 * @StudentNumber 2018217662
 * @Description
 */
public class EmptrainingServiceImpl implements EmptrainingService {
    private EmptrainingDAO emptrainingDAO = new EmptrainingDAPImpl();
    
    @Override
    public boolean startTrain(Emptraining emptraining) {
        return emptrainingDAO.startTrain(emptraining);
    }
    
    @Override
    public List<Employee> queryTrainEmpById(String trainId) {
        return emptrainingDAO.queryTrainEmpById(trainId);
    }
}
