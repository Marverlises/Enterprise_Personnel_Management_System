package com.by.coursedesign.web;

import com.by.coursedesign.entity.Employee;
import com.by.coursedesign.entity.Emptraining;
import com.by.coursedesign.service.EmptrainingService;
import com.by.coursedesign.service.impl.EmptrainingServiceImpl;
import com.by.coursedesign.utils.DataUtils;
import com.by.coursedesign.utils.ResponseInfo;
import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Author Baiyu
 * @Time 2023/6/24 14:52
 * @StudentNumber 2018217662
 * @Description
 */
public class EmptrainingServlet extends BasicServlet {
    private EmptrainingService emptrainingService = new EmptrainingServiceImpl();
    
    protected void startTrain(HttpServletRequest request, HttpServletResponse response) {
        int employeeId = DataUtils.parseInt(request.getParameter("employee_id"), -1);
        String trainId = request.getParameter("train_id");
        if (!(checkEmpExist(employeeId) && checkTrainingExist(trainId))) {
            setErrorResponse(response, "请求信息有误！", 402);
            return;
        }
        Emptraining emptraining = new Emptraining();
        emptraining.setEmployee_id(employeeId);
        emptraining.setTraining_id(trainId);
        boolean b = emptrainingService.startTrain(emptraining);
        setResponse(response, b);
    }
    
    protected void queryTrainEmpById(HttpServletRequest request, HttpServletResponse response){
        String trainingId = request.getParameter("trainingId");
        if (!checkTrainingExist(trainingId)){
            setErrorResponse(response, "不存在该培训！", 401);
        }
        List<Employee> employeesList = emptrainingService.queryTrainEmpById(trainingId);
    
        try {
            response.setContentType("text/html;charset=utf-8");
            ResponseInfo<Employee> responseInfo = new ResponseInfo<>();
            //layui表格显示需要的返回信息
            responseInfo.setCode(0);
            responseInfo.setMsg("success");
            responseInfo.setCount(employeesList.size());
            responseInfo.setData(employeesList);
            //转化成JSON对象返回
            Gson gson = new Gson();
            response.getWriter().write(gson.toJson(responseInfo));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
