package com.by.coursedesign.service.impl;

import com.by.coursedesign.dao.AttendanceDAO;
import com.by.coursedesign.dao.impl.AttendanceDAOImpl;
import com.by.coursedesign.entity.Attendance;
import com.by.coursedesign.service.AttendanceService;
import com.by.coursedesign.entity.utils.AttendanceDataUtils;
import com.by.coursedesign.utils.DataUtils;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

/**
 * @Author Baiyu
 * @Time 2023/6/20 16:20
 * @StudentNumber 2018217662
 * @Description
 */
public class AttendanceServiceImpl implements AttendanceService {
    private AttendanceDAO attendanceDAO = new AttendanceDAOImpl();
    
    @Override
    public List<Attendance> queryAllLog() {
        List<Attendance> attendances = attendanceDAO.queryAllLog();
        return attendances;
    }
    
    @Override
    public boolean deleteById(int id) {
        return attendanceDAO.deleteById(id);
    }
    
    @Override
    public List<AttendanceDataUtils> queryAllLogAddLateType() {
        //定义标准上下班时间
        String[] standardTime = {"09:00:00", "18:00:00"};
        //判断缺勤类型
        int late_type;
        List<Attendance> attendances = queryAllLog();
        ArrayList<AttendanceDataUtils> attendanceUtilList = new ArrayList<>();
        for (Attendance attendance : attendances) {
            //将TimeStamp转化成String并处理
            Timestamp log_time = attendance.getLog_time();
            Date date = new Date(log_time.getTime());
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            String str = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(calendar.getTime());
            String[] s = str.split(" ");
            String morning = s[0] + " " + standardTime[0];
            String afternoon = s[0] + " " + standardTime[1];
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Timestamp morningTimestamp = null;
            Timestamp afternoonTimestamp = null;
            
            try {
                Date morningDate = sdf.parse(morning);
                Date afternoonDate = sdf.parse(afternoon);
                morningTimestamp = new Timestamp(morningDate.getTime());
                afternoonTimestamp = new Timestamp(afternoonDate.getTime());
                
            } catch (ParseException e) {
                e.printStackTrace();
            }
            //缺勤类型处理
            if (attendance.getLog_type() == 0 && attendance.getLog_time().after(morningTimestamp)) {
                late_type = 1;
            } else if (attendance.getLog_type() == 1 && attendance.getLog_time().before(afternoonTimestamp)) {
                late_type = 2;
            } else {
                late_type = 0;
            }
            //添加数据
            AttendanceDataUtils attendanceDataUtils = new AttendanceDataUtils();
            attendanceDataUtils.setAttendance_id(attendance.getAttendance_id());
            attendanceDataUtils.setEmployee_id(attendance.getEmployee_id());
            attendanceDataUtils.setLate_type(late_type);
            attendanceDataUtils.setLog_time(attendance.getLog_time());
            attendanceDataUtils.setLog_type(attendance.getLog_type());
            attendanceUtilList.add(attendanceDataUtils);
        }
        return attendanceUtilList;
    }
    
    @Override
    public boolean deleteByWeek() {
        return attendanceDAO.deleteByWeek();
    }
    
    @Override
    public Map<LocalDate, List<Attendance>> queryLatestWeekLog() {
        List<Attendance> attendances = attendanceDAO.queryLatestWeekLog();
        Map<LocalDate, List<Attendance>> groupedAttendances = new HashMap<>();
    
        // 获取当前日期
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
    
        // 遍历近七天的日期范围
        for (int i = 0; i < 7; i++) {
            // 计算当前日期的偏移量
            int offset = -i;
        
            // 获取当前日期的偏移量
            Calendar dateCalendar = Calendar.getInstance();
            dateCalendar.setTime(calendar.getTime());
            dateCalendar.add(Calendar.DAY_OF_MONTH, offset);
            Date date = dateCalendar.getTime();
        
            // 检查该日期是否存在记录
            boolean hasAttendance = false;
            for (Attendance attendance : attendances) {
                if (DataUtils.isSameDay(attendance.getLog_time(), date)) {
                    hasAttendance = true;
                    break;
                }
            }
        
            // 如果该日期不存在记录，则添加一个空的 Attendance 对象
            if (!hasAttendance) {
                attendances.add(new Attendance(-1, new Timestamp(date.getTime()))); // 假设 Attendance 构造函数接受日期和记录作为参数
            }
        }
        for (Attendance attendance : attendances) {
            LocalDate localDate = attendance.getLog_time().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            groupedAttendances.computeIfAbsent(localDate, k -> new ArrayList<>()).add(attendance);
        }
        // 按照天分组的结果
        for (Map.Entry<LocalDate, List<Attendance>> entry : groupedAttendances.entrySet()) {
            LocalDate day = entry.getKey();
            List<Attendance> dayAttendances = entry.getValue();
            if (dayAttendances.get(0).getAttendance_id() == -1){
                dayAttendances.remove(0);
            }
        }
        return groupedAttendances;
    }
}
