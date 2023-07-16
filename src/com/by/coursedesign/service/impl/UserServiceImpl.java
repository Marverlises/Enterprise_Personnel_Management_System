package com.by.coursedesign.service.impl;

import com.by.coursedesign.dao.UserDAO;
import com.by.coursedesign.dao.impl.UserDAOImpl;
import com.by.coursedesign.entity.User;
import com.by.coursedesign.service.UserService;

import java.util.List;

/**
 * @Author Baiyu
 * @Time 2023/6/13 9:39
 * @StudentNumber 2018217662
 * @Description
 */
public class UserServiceImpl implements UserService {
    private UserDAO userDAO = new UserDAOImpl();
    
    //如果用户不存在返回空
    @Override
    public User login(User user) {
        return userDAO.queryUserByUsernameAndPasswordAndType(
                user.getUsername(), user.getUser_password(), user.getPermission_level());
    }
    
    @Override
    public List<User> queryAllUser() {
        return userDAO.queryAllUser();
    }
    
    @Override
    public boolean insertUser(User user) {
        return userDAO.insertUser(user);
    }
    
    @Override
    public boolean deleteById(int id) {
        return userDAO.deleteById(id);
    }
    
    @Override
    public boolean updateUser(User user) {
        return userDAO.updateUser(user);
    }
}
