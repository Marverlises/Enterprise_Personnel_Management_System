package com.by.coursedesign.web;

import com.by.coursedesign.entity.Salary;
import com.by.coursedesign.service.SalaryService;
import com.by.coursedesign.service.impl.SalaryServiceImpl;
import com.by.coursedesign.utils.DataUtils;
import com.by.coursedesign.utils.ResponseInfo;
import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @Author Baiyu
 * @Time 2023/6/23 17:06
 * @StudentNumber 2018217662
 * @Description
 */
public class SalaryServlet extends BasicServlet {
    private SalaryService salaryService = new SalaryServiceImpl();
    
    protected void queryAllSalary(HttpServletRequest request, HttpServletResponse response) {
        List<Salary> salaries = salaryService.queryAllSalary();
        Gson gson = new Gson();
        try {
            response.setContentType("text/html;charset=utf-8");
            ResponseInfo<Salary> responseInfo = new ResponseInfo<>();
            //layui表格显示需要的返回信息
            responseInfo.setCode(0);
            responseInfo.setMsg("success");
            responseInfo.setCount(salaries.size());
            responseInfo.setData(salaries);
            response.getWriter().write(gson.toJson(responseInfo));
        } catch (IOException e) {
            response.setStatus(403);
            e.printStackTrace();
        }
    }
    
    protected void deleteBySalaryEmployeeId(HttpServletRequest request, HttpServletResponse response) {
        int employeeId = DataUtils.parseInt(request.getParameter("employee_id"), 0);
        String salaryId = request.getParameter("salary_id");
        if (employeeId == 0) {
            setErrorResponse(response, "数据格式有误！", 401);
            return;
        }
        boolean b = salaryService.deleteBySalaryEmployeeId(salaryId, employeeId);
        setResponse(response, b);
    }
    
    protected void insertItem(HttpServletRequest request, HttpServletResponse response) {
        Salary salary = createSalary(request, response);
        if (salary == null){
            return;
        }
        boolean b = salaryService.insertItem(salary);
        setResponse(response, b);
    }
    
    protected void updateItem(HttpServletRequest request, HttpServletResponse response){
        Salary salary = createSalary(request, response);
        if (salary == null){
            return;
        }
        String salary_id = request.getParameter("salary_id");
        salary.setSalary_id(salary_id);
        System.out.println(salary);
        boolean b = salaryService.updateItem(salary);
        setResponse(response, b);
    }

    //创建一个salary对象
    protected Salary createSalary(HttpServletRequest request, HttpServletResponse response){
        int employee_id = DataUtils.parseInt(request.getParameter("employee_id"), 0);
        int salary_amount = DataUtils.parseInt(request.getParameter("salary_amount"), 0);
        String salary_category = request.getParameter("salary_category");
        String date = request.getParameter("date");
        if (employee_id == 0 || salary_amount == 0) {
            setErrorResponse(response, "数据格式有误！", 401);
            return null;
        }
    
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date salaryDate = null;
        try {
            salaryDate = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Salary salary = new Salary();
        salary.setEmployee_id(employee_id);
        salary.setPayment_date(salaryDate);
        salary.setSalary_amount(BigDecimal.valueOf(salary_amount));
        salary.setSalary_category(salary_category);
        return salary;
    }
}