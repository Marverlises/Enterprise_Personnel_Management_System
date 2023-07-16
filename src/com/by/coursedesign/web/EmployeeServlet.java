package com.by.coursedesign.web;

import com.by.coursedesign.entity.Employee;
import com.by.coursedesign.service.EmployeeService;
import com.by.coursedesign.service.impl.EmployeeServiceImpl;
import com.by.coursedesign.utils.DataUtils;
import com.by.coursedesign.utils.ResponseInfo;
import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


/**
 * @Author Baiyu
 * @Time 2023/6/16 9:24
 * @StudentNumber 2018217662
 * @Description
 */
public class EmployeeServlet extends BasicServlet {
    private EmployeeService employeeService = new EmployeeServiceImpl();
    
    protected void showEmployees(HttpServletRequest request, HttpServletResponse response) {
        List<Employee> employeesList = employeeService.showAllEmployees();
        
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
    
    //更新一个员工信息
    protected void updateEmployee(HttpServletRequest request, HttpServletResponse response) {
        Employee employee = getEmployee(request, response);
        if (employee == null) {
            setErrorResponse(response, "更新失败！", 403);
            return;
        }
        boolean b = employeeService.updateEmployee(employee);
        setResponse(response, b);
    }
    
    protected void deleteEmployee(HttpServletRequest request, HttpServletResponse response) {
        int employee_id = Integer.parseInt(request.getParameter("employee_id"));
        boolean b = employeeService.deleteEmployeeByID(employee_id);
        setResponse(response, b);
    }
    
    protected void addEmployee(HttpServletRequest request, HttpServletResponse response) {
        Employee employee = getEmployee(request, response);
        System.out.println("添加的wei" + employee);
        if (employee == null) {
            setErrorResponse(response, "插入失败！", 403);
            return;
        }
        boolean b = employeeService.addEmployee(employee);
        setResponse(response, b);
    }
    
    //根据下拉框内容进行筛选需要的数据
    protected void queryFilter(HttpServletRequest request, HttpServletResponse response) {
        int age = DataUtils.parseInt(request.getParameter("age"), -1);
        int gender = DataUtils.parseInt(request.getParameter("gender"), -1);
        int qualification = DataUtils.parseInt(request.getParameter("qualification"), -1);
        boolean b = checkFilterRequestData(age, gender, qualification);
        if (!b) {
            setErrorResponse(response, "数据格式有误！", 401);
            return;
        }
        //数据检查通过
        List<Employee> employeesList = employeeService.queryFilterEmployees(age, gender, qualification);
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
    
    /**
     * 根据request请求获得其中的参数并构建Employee对象
     *
     * @param request
     * @return
     */
    protected Employee getEmployee(HttpServletRequest request, HttpServletResponse response) {
        Integer employee_id = Integer.parseInt(request.getParameter("employee_id"));
        String employee_name = request.getParameter("employee_name");
        String gender = request.getParameter("gender");
        String birthdate = request.getParameter("birthdate");
        String phone_number = request.getParameter("phone_number");
        String address = request.getParameter("address");
        String qualification = request.getParameter("qualification");
        String email = request.getParameter("email");
        String hire_date = request.getParameter("hire_date");
        String position_id = request.getParameter("position_id");
        String department_id = request.getParameter("department_id");
        
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date empBirthday = null;
        Date hireDate = null;
        try {
            empBirthday = format.parse(birthdate);
            hireDate = format.parse(hire_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Integer positionId = DataUtils.parseInt(position_id, -1);
        Integer departmentId = DataUtils.parseInt(department_id, -1);
        if (positionId == -1 || departmentId == -1) {
            setErrorResponse(response, "数据格式有误！", 401);
            return null;
        }
        //------------------------------------
        Employee employee = new Employee(employee_id, employee_name, gender, empBirthday, phone_number, address, email,
                hireDate, positionId, departmentId, qualification);
        return employee;
    }
    
    protected boolean checkFilterRequestData(int age, int gender, int qualification) {
        if (age == -1 || gender == -1 || qualification == -1) {
            return false;
        }
        Integer[] ages = {10, 20, 30, 40, 50};
        Integer[] genders = {1, 2};
        Integer[] qualifications = {1, 2, 3, 4};
        ArrayList<Integer> checkAge = new ArrayList<Integer>(Arrays.asList(ages));
        ArrayList<Integer> checkGender = new ArrayList<Integer>(Arrays.asList(genders));
        ArrayList<Integer> checkQualification = new ArrayList<Integer>(Arrays.asList(qualifications));
        
        if (!checkAge.contains(age) || !checkGender.contains(gender) || !checkQualification.contains(qualification)) {
            return false;
        }
        return true;
    }
    
}