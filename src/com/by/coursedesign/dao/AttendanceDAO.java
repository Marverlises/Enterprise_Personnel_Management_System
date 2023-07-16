package com.by.coursedesign.dao;

import com.by.coursedesign.entity.Attendance;

import java.util.List;

/**
 * @Author Baiyu
 * @Time 2023/6/20 16:10
 * @StudentNumber 2018217662
 * @Description
 */
public interface AttendanceDAO {
    public List<Attendance> queryAllLog();
    
    public boolean deleteById(int id);
    
    public boolean deleteByWeek();
    
    public List<Attendance> queryLatestWeekLog();
}
