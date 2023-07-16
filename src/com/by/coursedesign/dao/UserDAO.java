package com.by.coursedesign.dao;

import com.by.coursedesign.entity.User;

import java.util.List;

/**
 * @Author Baiyu
 * @Time 2023/6/13 9:14
 * @StudentNumber 2018217662
 * @Description
 */
public interface UserDAO {

    //登录判断用户是否存在
    public User queryUserByUsernameAndPasswordAndType(String userName, String password, Integer userType);
    
    public List<User> queryAllUser();
    
    public boolean insertUser(User user);
    
    public boolean deleteById(int id);
    
    public boolean updateUser(User user);
}
