package com.by.coursedesign.web;

import com.by.coursedesign.entity.Attendance;
import com.by.coursedesign.entity.utils.AttendanceWeekDataUtils;
import com.by.coursedesign.service.AttendanceService;
import com.by.coursedesign.service.impl.AttendanceServiceImpl;
import com.by.coursedesign.utils.DataUtils;
import com.by.coursedesign.utils.ResponseInfo;
import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @Author Baiyu
 * @Time 2023/6/20 16:21
 * @StudentNumber 2018217662
 * @Description
 */
public class AttendanceServlet extends BasicServlet {
    private AttendanceService attendanceService = new AttendanceServiceImpl();
    private Integer[] logTypeArray = {0, 0, 0};
    
    protected void queryAllLog(HttpServletRequest request, HttpServletResponse response) {
        List<Attendance> attendances = attendanceService.queryAllLog();
        //汇总缺勤类型
        logTypeArray[0] = 0;
        logTypeArray[1] = 0;
        logTypeArray[2] = 0;
        for (Attendance attendance : attendances) {
            Integer late_type = attendance.getLate_type();
            if (late_type == 1) {
                logTypeArray[1]++;
            }else if (late_type == 2){
                logTypeArray[2]++;
            }else{
                logTypeArray[0]++;
            }
        }
        
        try {
            response.setContentType("text/html;charset=utf-8");
            ResponseInfo<Attendance> responseInfo = new ResponseInfo<>();
            //layui表格显示需要的返回信息
            responseInfo.setCode(0);
            responseInfo.setMsg("success");
            responseInfo.setCount(attendances.size());
            responseInfo.setData(attendances);
            //转化成JSON对象返回
            Gson gson = new Gson();
            response.getWriter().write(gson.toJson(responseInfo));
        } catch (IOException e) {
            response.setStatus(403);
            e.printStackTrace();
        }
    }
    
    protected void deleteLogById(HttpServletRequest request, HttpServletResponse response) {
        int attendanceId = DataUtils.parseInt(request.getParameter("attendance_id"), 0);
        if (attendanceId == 0) {
            response.setStatus(403);
            return;
        }
        boolean b = attendanceService.deleteById(attendanceId);
        setResponse(response, b);
    }
    
    protected void getLogTypeNumber(HttpServletRequest request, HttpServletResponse response) {
        Gson gson = new Gson();
        try {
            response.getWriter().write(gson.toJson(logTypeArray));
        } catch (IOException e) {
            response.setStatus(403);
            e.printStackTrace();
        }
    }
    
    protected void deleteByWeek(HttpServletRequest request, HttpServletResponse response){
        boolean b = attendanceService.deleteByWeek();
        setResponse(response, b);
    }
    
    
    protected void queryLatestWeekLog(HttpServletRequest request ,HttpServletResponse response){
        Map<LocalDate, List<Attendance>> localDateListMap = attendanceService.queryLatestWeekLog();
        AttendanceWeekDataUtils attendanceWeekDataUtils = new AttendanceWeekDataUtils();
        //封装返回数据对象
        ArrayList<Integer> dayLength = new ArrayList<>();
        ArrayList<Integer> dayNormalNum = new ArrayList<>();
        ArrayList<Integer> dayLateNum = new ArrayList<>();
        ArrayList<LocalDate> day = new ArrayList<>();
    
        for (Map.Entry<LocalDate, List<Attendance>> entry : localDateListMap.entrySet()) {
            LocalDate key = entry.getKey();
            List<Attendance> value = entry.getValue();
            int lateNum = 0;
            for (Attendance attendance : value) {
                if (attendance.getLate_type() != 0){
                    lateNum++;
                }
            }
            day.add(key);
            dayLateNum.add(lateNum);
            dayLength.add(value.size());
            dayNormalNum.add(value.size() - lateNum);
        }
        Collections.reverse(day);
        Collections.reverse(dayLength);
        Collections.reverse(dayNormalNum);
        Collections.reverse(dayLateNum);
        attendanceWeekDataUtils.setDay(day);
        attendanceWeekDataUtils.setDayLength(dayLength);
        attendanceWeekDataUtils.setDayNormalNum(dayNormalNum);
        attendanceWeekDataUtils.setDayLateNum(dayLateNum);
        
        Gson gson = new Gson();
        try {
            response.getWriter().write( gson.toJson(attendanceWeekDataUtils));
        } catch (IOException e) {
            response.setStatus(403);
            e.printStackTrace();
        }
    }
}
