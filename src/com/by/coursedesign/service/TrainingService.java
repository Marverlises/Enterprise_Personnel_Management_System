package com.by.coursedesign.service;

import com.by.coursedesign.entity.Training;

import java.util.List;

/**
 * @Author Baiyu
 * @Time 2023/6/24 8:50
 * @StudentNumber 2018217662
 * @Description
 */
public interface TrainingService {
    public List<Training> queryAllItems();
    
    public boolean addTraining(Training training);
    
    public boolean updateTraining(Training training);
    
    public boolean deleteItem(String id);
}
