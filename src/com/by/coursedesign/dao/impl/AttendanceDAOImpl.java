package com.by.coursedesign.dao.impl;

import com.by.coursedesign.dao.AttendanceDAO;
import com.by.coursedesign.dao.BasicDAO;
import com.by.coursedesign.entity.Attendance;

import java.util.List;

/**
 * @Author Baiyu
 * @Time 2023/6/20 16:10
 * @StudentNumber 2018217662
 * @Description
 */
public class AttendanceDAOImpl extends BasicDAO<Attendance> implements AttendanceDAO {
    @Override
    public List<Attendance> queryAllLog() {
        String sql = "SELECT * FROM attendance";
        return queryMulti(sql, Attendance.class);
    }
    
    @Override
    public boolean deleteById(int id) {
        String sql = "DELETE FROM attendance WHERE attendance_id = ?";
        int update = update(sql, id);
        return done(update);
    }
    
    @Override
    public boolean deleteByWeek() {
        String sql = "DELETE FROM attendance \n" +
                "WHERE attendance_id IN (\n" +
                "  SELECT a.attendance_id \n" +
                "  FROM (\n" +
                "    SELECT attendance_id \n" +
                "    FROM attendance \n" +
                "    WHERE log_time < (\n" +
                "      SELECT DATE_SUB((SELECT MAX(log_time) FROM attendance), \t\t\tINTERVAL 7 DAY) AS " +
                "one_week_before\n" +
                "    )\n" +
                "  ) a\n" +
                ");";
        int update = update(sql);
        return done(update);
    }
    
    @Override
    public List<Attendance> queryLatestWeekLog() {
        String sql = "SELECT *\n" +
                "FROM attendance\n" +
                "WHERE log_time >= CURDATE() - INTERVAL 6 DAY;";
        return queryMulti(sql, Attendance.class);
    }
}
