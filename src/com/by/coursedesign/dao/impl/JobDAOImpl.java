package com.by.coursedesign.dao.impl;

import com.by.coursedesign.dao.BasicDAO;
import com.by.coursedesign.dao.JobDAO;
import com.by.coursedesign.entity.Job;

import java.util.List;

/**
 * @Author Baiyu
 * @Time 2023/6/20 9:51
 * @StudentNumber 2018217662
 * @Description
 */
public class JobDAOImpl extends BasicDAO implements JobDAO {
    @Override
    public List<Job> queryAllJob() {
        String sql = "SELECT * FROM job";
        return queryMulti(sql, Job.class);
    }
    
    @Override
    public boolean updateJob(Job job) {
        String sql = "UPDATE job SET position_id = ?, position_name = ?, responsibilities = ? " +
                "WHERE position_id = ?;";
        int update = update(sql, job.getPosition_id(), job.getPosition_name(), job.getResponsibilities(),
                job.getPosition_id());
        return done(update);
    }
    
    @Override
    public boolean deleteJobById(int id) {
        String sql = "DELETE FROM job WHERE position_id = ?;";
        int update = update(sql, id);
        return done(update);
    }
    
    @Override
    public boolean addJob(Job job) {
        String sql = "INSERT INTO job (position_id, position_name, responsibilities)\n" +
                "VALUES (?, ?, ?);";
        int update = update(sql, job.getPosition_id(), job.getPosition_name(), job.getResponsibilities());
        return done(update);
    }
}
