package com.by.coursedesign.dao.impl;

import com.by.coursedesign.dao.BasicDAO;
import com.by.coursedesign.dao.UserDAO;
import com.by.coursedesign.entity.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author Baiyu
 * @Time 2023/6/13 9:18
 * @StudentNumber 2018217662
 * @Description
 */
public class UserDAOImpl extends BasicDAO<User> implements UserDAO {
    
    @Override
    public User queryUserByUsernameAndPasswordAndType(String userName, String password, Integer userType) {
        String sql = "SELECT * FROM `user_info` " +
                " WHERE `username`=? and `user_password`=md5(?) and `permission_level`=?";
        return querySingle(sql, User.class, userName, password, userType);
    }
    
    @Override
    public List<User> queryAllUser() {
        String sql = "SELECT * FROM user_info;";
        return queryMulti(sql, User.class);
    }
    
    
    //添加一个新用户，所以login_time为null
    @Override
    public boolean insertUser(User user) {
        String sql = "INSERT INTO `user_info` (`user_id`, `username`, `user_password`, " +
                "`login_time`, `permission_level`, `employee_id`) \n" +
                "VALUES (NULL, ?, MD5(?), ?, ?, ?);";
        int update = update(sql, user.getUsername(), user.getUser_password(),
                LocalDateTime.now(), user.getPermission_level(), user.getEmployee_id());
        return done(update);
    }
    
    @Override
    public boolean deleteById(int id) {
        String sql = "DELETE FROM user_info WHERE user_id = ?";
        int update = update(sql, id);
        return done(update);
    }
    
    @Override
    public boolean updateUser(User user) {
        String sql = "UPDATE user_info\n" +
                "SET username =  ?,\n" +
                "    user_password = MD5(?),\n" +
                "    login_time = ?,\n" +
                "    permission_level = ?,\n" +
                "    employee_id = ?\n" +
                "WHERE user_id = ?;\n";
        int update = update(sql, user.getUsername(), user.getUser_password(), LocalDate.now(),
                user.getPermission_level(), user.getEmployee_id(), user.getUser_id());
        return done(update);
    }
}
