package com.by.coursedesign.service;

import com.by.coursedesign.entity.User;

import java.util.List;

/**
 * @Author Baiyu
 * @Time 2023/6/13 9:37
 * @StudentNumber 2018217662
 * @Description
 */
public interface UserService {
    /**
     * 根据登录传入的user信息，返回对应的在DB中的user对象
     *
     * @param user 是根据用户登录构建一个user
     * @return 返回的是对应的db中的member对象，如果不存在,返回null
     */
    public User login(User user);
    
    public List<User> queryAllUser();
    
    public boolean insertUser(User user);
    
    public boolean deleteById(int id);
    
    public boolean updateUser(User user);
}