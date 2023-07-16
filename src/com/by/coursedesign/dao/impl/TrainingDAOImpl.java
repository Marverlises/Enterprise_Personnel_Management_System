package com.by.coursedesign.dao.impl;

import com.by.coursedesign.dao.BasicDAO;
import com.by.coursedesign.dao.TrainingDAO;
import com.by.coursedesign.entity.Employee;
import com.by.coursedesign.entity.Training;

import java.util.List;
import java.util.UUID;

/**
 * @Author Baiyu
 * @Time 2023/6/24 8:49
 * @StudentNumber 2018217662
 * @Description
 */
public class TrainingDAOImpl extends BasicDAO<Training> implements TrainingDAO {
    @Override
    public List<Training> queryAllItems() {
        String sql = "SELECT * FROM training;";
        return queryMulti(sql, Training.class);
    }
    
    @Override
    public boolean addTraining(Training training) {
        UUID uuid = UUID.randomUUID();
        String randomString = uuid.toString();
        String trainingId = randomString.substring(0, 8);
        //当插入新的培训时培训人数默认为0
        String sql = "INSERT INTO training (training_id, training_name, training_description, start_date, end_date, " +
                "total_participants)\n" +
                "VALUES (?, ?, ?, ?, ?, 0);";
        int update = update(sql, trainingId, training.getTraining_name(), training.getTraining_description(),
                training.getStart_date(), training.getEnd_date());
        return done(update);
    }
    
    @Override
    public boolean updateTraining(Training training) {
        String sql = "\n" +
                "UPDATE training SET training_name = ?, training_description= ?, " +
                "start_date= ?, end_date= ?, total_participants = ? WHERE training_id = " + "?;";
        int update = update(sql, training.getTraining_name(), training.getTraining_description(),
                training.getStart_date(),
                training.getEnd_date(), training.getTotal_participants(), training.getTraining_id());
        return done(update);
    }
    
    @Override
    public boolean deleteItem(String id) {
        String sql = "DELETE FROM training WHERE training_id = ?";
        int update = update(sql, id);
        return done(update);
    }
    

}
