package com.by.coursedesign.dao;

import com.by.coursedesign.entity.Employee;
import com.by.coursedesign.entity.Emptraining;

import java.util.List;

/**
 * @Author Baiyu
 * @Time 2023/6/24 14:43
 * @StudentNumber 2018217662
 * @Description
 */
public interface EmptrainingDAO {
    public boolean startTrain(Emptraining emptraining);
    
    public List<Employee> queryTrainEmpById(String trainId);
    
}
