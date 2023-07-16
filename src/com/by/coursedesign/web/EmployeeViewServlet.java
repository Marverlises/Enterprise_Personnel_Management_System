package com.by.coursedesign.web;

import com.by.coursedesign.entity.EmployeeView;
import com.by.coursedesign.entity.Job;
import com.by.coursedesign.entity.User;
import com.by.coursedesign.service.EmployeeViewService;
import com.by.coursedesign.service.impl.EmployeeViewServiceImpl;
import com.by.coursedesign.utils.DataUtils;
import com.by.coursedesign.utils.ResponseInfo;
import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author Baiyu
 * @Time 2023/6/25 10:15
 * @StudentNumber 2018217662
 * @Description
 */
public class EmployeeViewServlet extends BasicServlet {
    private EmployeeViewService employeeViewService = new EmployeeViewServiceImpl();
    
    protected void queryAllData(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        Integer employee_id = user.getEmployee_id();
        System.out.println("该用户为" + employee_id);
        EmployeeView employeeView = employeeViewService.queryAllDataById(employee_id);
        System.out.println(employeeView);
        try {
            response.setContentType("text/html;charset=utf-8");
            //转化成JSON对象返回
            Gson gson = new Gson();
            response.getWriter().write(gson.toJson(employeeView));
        } catch (IOException e) {
            response.setStatus(403);
            e.printStackTrace();
        }
    }
    
    
    protected void startWork(HttpServletRequest request, HttpServletResponse response) {
        utilsMethod(request, response, true);
    }
    
    protected void endWork(HttpServletRequest request, HttpServletResponse response) {
        utilsMethod(request, response, false);
    }
    
    private void utilsMethod(HttpServletRequest request, HttpServletResponse response, boolean flag) {
        int employeeId = DataUtils.parseInt(request.getParameter("employeeId"), -1);
        if (!checkEmpExist(employeeId)) {
            setErrorResponse(response, "数据格式有误！", 401);
        }
        System.out.println(employeeId);
        boolean b;
        if (flag) {
            b = employeeViewService.startWork(employeeId);
        } else {
            b = employeeViewService.endWork(employeeId);
        }
        if (b) {
            response.setContentType("text/html;charset=utf-8");
            ResponseInfo<Job> responseInfo = new ResponseInfo<>();
            //layui表格显示需要的返回信息
            responseInfo.setCode(0);
            responseInfo.setMsg("success");
            //转化成JSON对象返回
            Gson gson = new Gson();
            try {
                response.getWriter().write(gson.toJson(responseInfo));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            ResponseInfo<Job> responseInfo = new ResponseInfo<>();
            //layui表格显示需要的返回信息
            responseInfo.setCode(403);
            responseInfo.setMsg("error");
            response.setStatus(403);
            //转化成JSON对象返回
            Gson gson = new Gson();
            try {
                response.getWriter().write(gson.toJson(responseInfo));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
