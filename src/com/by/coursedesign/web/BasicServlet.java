package com.by.coursedesign.web;

import com.by.coursedesign.entity.Employee;
import com.by.coursedesign.entity.Training;
import com.by.coursedesign.service.EmployeeService;
import com.by.coursedesign.service.TrainingService;
import com.by.coursedesign.service.impl.EmployeeServiceImpl;
import com.by.coursedesign.service.impl.TrainingServiceImpl;
import com.by.coursedesign.utils.ResponseInfo;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @Author Baiyu
 * @Time 2023/6/13 14:33
 * @StudentNumber 2018217662
 * @Description
 */
public class BasicServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        //采用模板设计模式，其它servlet继承自该类实现反射调用相应方法
        String action = req.getParameter("action");
        try {
            Method declaredMethod = this.getClass().getDeclaredMethod(action, HttpServletRequest.class,
                    HttpServletResponse.class);
            declaredMethod.invoke(this, req, resp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * 设置返回的相应信息
     *
     * @param response
     * @param done
     */
    protected void setResponse(HttpServletResponse response, boolean done) {
        response.setContentType("text/html;charset=utf-8");
        ResponseInfo responseInfo = new ResponseInfo();
        if (done) {
            //layui表格显示需要的返回信息
            response.setStatus(200);
            responseInfo.setMsg("success");
            //转化成JSON对象返回
            Gson gson = new Gson();
            try {
                response.getWriter().write(gson.toJson(responseInfo));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            response.setStatus(403);
            responseInfo.setMsg("error");
        }
    }
    
    
    protected void setErrorResponse(HttpServletResponse response, String msg, int code){
        response.setStatus(code);
        response.setContentType("text/html;charset=utf-8");
        ResponseInfo responseInfo = new ResponseInfo();
        responseInfo.setCode(code);
        responseInfo.setMsg(msg);
        //转化成JSON对象返回
        Gson gson = new Gson();
        try {
            response.getWriter().write(gson.toJson(responseInfo));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return;
    }
    
    protected boolean checkEmpExist(int id){
        EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
        List<Employee> employees = employeeService.showAllEmployees();
        for (Employee employee : employees) {
            if (employee.getEmployee_id() == id){
                return true;
            }
        }
        return false;
    }
    
    protected boolean checkTrainingExist(String id){
        TrainingServiceImpl trainingService = new TrainingServiceImpl();
        List<Training> trainings = trainingService.queryAllItems();
        for (Training training : trainings) {
            if(training.getTraining_id().equalsIgnoreCase(id)){
                return true;
            }
        }
        return false;
    }
}
