package com.by.coursedesign.service.impl;

import com.by.coursedesign.dao.JobDAO;
import com.by.coursedesign.dao.impl.JobDAOImpl;
import com.by.coursedesign.entity.Job;
import com.by.coursedesign.service.JobService;

import java.util.List;

/**
 * @Author Baiyu
 * @Time 2023/6/20 10:02
 * @StudentNumber 2018217662
 * @Description
 */
public class JobServiceImpl implements JobService {
    private JobDAO jobDAO = new JobDAOImpl();
    
    @Override
    public List<Job> queryAllJob() {
        return jobDAO.queryAllJob();
    }
    
    @Override
    public boolean updateJob(Job job) {
        return jobDAO.updateJob(job);
    }
    
    @Override
    public boolean deleteJobById(int id) {
        return jobDAO.deleteJobById(id);
    }
    
    @Override
    public boolean addJob(Job job) {
        return jobDAO.addJob(job);
    }
    
    
}
