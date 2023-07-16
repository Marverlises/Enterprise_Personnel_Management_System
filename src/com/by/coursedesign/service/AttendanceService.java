package com.by.coursedesign.service;

import com.by.coursedesign.entity.Attendance;
import com.by.coursedesign.entity.utils.AttendanceDataUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * @Author Baiyu
 * @Time 2023/6/20 16:19
 * @StudentNumber 2018217662
 * @Description
 */
public interface AttendanceService {
    public List<Attendance> queryAllLog();
    
    public boolean deleteById(int id);
    
    //暂时弃置
    @Deprecated
    public List<AttendanceDataUtils> queryAllLogAddLateType();
    
    public boolean deleteByWeek();
    
    public Map<LocalDate, List<Attendance>> queryLatestWeekLog();
    
}
