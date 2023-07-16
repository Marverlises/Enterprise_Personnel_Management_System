package com.by.coursedesign.service.impl;

import com.by.coursedesign.dao.EmployeeViewDAO;
import com.by.coursedesign.dao.impl.EmployeeViewDAOImpl;
import com.by.coursedesign.entity.EmployeeView;
import com.by.coursedesign.service.EmployeeViewService;

import java.util.List;

/**
 * @Author Baiyu
 * @Time 2023/6/25 10:13
 * @StudentNumber 2018217662
 * @Description
 */
public class EmployeeViewServiceImpl implements EmployeeViewService {
    private EmployeeViewDAO employeeViewDAO = new EmployeeViewDAOImpl();
    
    @Override
    public EmployeeView queryAllDataById(int id) {
        return employeeViewDAO.queryAllDataById(id);
    }
    
    @Override
    public boolean startWork(int id) {
        return employeeViewDAO.startWork(id);
    }
    
    @Override
    public boolean endWork(int id) {
        return employeeViewDAO.endWork(id);
    }
}
