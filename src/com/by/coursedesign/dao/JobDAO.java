package com.by.coursedesign.dao;

import com.by.coursedesign.entity.Job;

import java.util.List;

/**
 * @Author Baiyu
 * @Time 2023/6/20 9:51
 * @StudentNumber 2018217662
 * @Description
 */
public interface JobDAO {
    public List<Job> queryAllJob();
    
    public boolean updateJob(Job job);
    
    public boolean deleteJobById(int id);
    
    public boolean addJob(Job job);
}
