package com.by.coursedesign.service;

import com.by.coursedesign.entity.Employee;
import com.by.coursedesign.entity.Emptraining;

import java.util.List;

/**
 * @Author Baiyu
 * @Time 2023/6/24 14:51
 * @StudentNumber 2018217662
 * @Description
 */
public interface EmptrainingService {
    public boolean startTrain(Emptraining emptraining);
    
    public List<Employee> queryTrainEmpById(String trainId);
}
