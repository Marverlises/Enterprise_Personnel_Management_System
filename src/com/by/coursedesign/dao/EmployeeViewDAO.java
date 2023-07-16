package com.by.coursedesign.dao;

import com.by.coursedesign.entity.EmployeeView;

import java.util.List;

/**
 * @Author Baiyu
 * @Time 2023/6/25 10:07
 * @StudentNumber 2018217662
 * @Description
 */
public interface EmployeeViewDAO {
    public  EmployeeView queryAllDataById(int id);
    
    public boolean startWork(int id);
    
    public boolean endWork(int id);
    
    
}
