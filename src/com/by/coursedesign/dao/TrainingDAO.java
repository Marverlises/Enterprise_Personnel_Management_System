package com.by.coursedesign.dao;

import com.by.coursedesign.entity.Employee;
import com.by.coursedesign.entity.Training;

import java.util.List;

/**
 * @Author Baiyu
 * @Time 2023/6/24 8:48
 * @StudentNumber 2018217662
 * @Description
 */
public interface TrainingDAO {
    public List<Training> queryAllItems();
    
    public boolean addTraining(Training training);
    
    public boolean updateTraining(Training training);
    
    public boolean deleteItem(String id);
}
