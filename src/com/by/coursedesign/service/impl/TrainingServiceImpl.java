package com.by.coursedesign.service.impl;

import com.by.coursedesign.dao.TrainingDAO;
import com.by.coursedesign.dao.impl.TrainingDAOImpl;
import com.by.coursedesign.entity.Training;
import com.by.coursedesign.service.TrainingService;

import java.util.List;

/**
 * @Author Baiyu
 * @Time 2023/6/24 8:51
 * @StudentNumber 2018217662
 * @Description
 */
public class TrainingServiceImpl implements TrainingService {
    private TrainingDAO trainingDAO = new TrainingDAOImpl();
    
    @Override
    public List<Training> queryAllItems() {
        return trainingDAO.queryAllItems();
    }
    
    @Override
    public boolean addTraining(Training training) {
        return trainingDAO.addTraining(training);
    }
    
    @Override
    public boolean updateTraining(Training training) {
        return trainingDAO.updateTraining(training);
    }
    
    @Override
    public boolean deleteItem(String id) {
        return trainingDAO.deleteItem(id);
    }
}
