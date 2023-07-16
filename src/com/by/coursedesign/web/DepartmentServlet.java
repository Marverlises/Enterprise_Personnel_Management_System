package com.by.coursedesign.web;

import com.by.coursedesign.entity.Department;
import com.by.coursedesign.entity.Employee;
import com.by.coursedesign.service.DepartmentService;
import com.by.coursedesign.service.EmployeeService;
import com.by.coursedesign.service.impl.DepartmentServiceImpl;
import com.by.coursedesign.service.impl.EmployeeServiceImpl;
import com.by.coursedesign.utils.DataUtils;
import com.by.coursedesign.utils.ResponseInfo;
import com.google.gson.Gson;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author Baiyu
 * @Time 2023/6/19 10:45
 * @StudentNumber 2018217662
 * @Description
 */
public class DepartmentServlet extends BasicServlet {
    private DepartmentService departmentService = new DepartmentServiceImpl();
    private EmployeeService employeeService = new EmployeeServiceImpl();
    
    protected void queryAllDepartments(HttpServletRequest request, HttpServletResponse response) {
        List<Department> departmentsList = departmentService.queryAllDepartment();

        
        try {
            response.setContentType("text/html;charset=utf-8");
            ResponseInfo<Department> responseInfo = new ResponseInfo<>();
            //layui表格显示需要的返回信息
            responseInfo.setCode(0);
            responseInfo.setMsg("success");
            responseInfo.setCount(departmentsList.size());
            responseInfo.setData(departmentsList);
            //转化成JSON对象返回
            Gson gson = new Gson();
            response.getWriter().write(gson.toJson(responseInfo));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    protected void deleteDepartment(HttpServletRequest request, HttpServletResponse response) {
        Integer department_id = Integer.parseInt(request.getParameter("department_id"));
        boolean b = departmentService.deleteDepartmentById(department_id);
        setResponse(response, b);
    }
    
    protected void updateDepartment(HttpServletRequest request, HttpServletResponse response) {
        Department department = new Department();
        verifyEmployee(request, response, department);
        boolean b = departmentService.updateDepartment(department);
        setResponse(response, b);
    }
    
    protected void addDepartment(HttpServletRequest request, HttpServletResponse response) {
        Department department = new Department();
        verifyEmployee(request, response, department);
        boolean b = departmentService.addDepartment(department);
        setResponse(response, b);
    }
    
    //查询是否存在该工号且姓名的员工,如果数据都正确设置Department对象
    protected void verifyEmployee(HttpServletRequest request, HttpServletResponse response, Department department) {
        Integer departmentId = DataUtils.parseInt(request.getParameter("department_id"), 0);
        String departmentName = request.getParameter("department_name");
        String departmentManager = request.getParameter("department_manager");
        Integer managerId = DataUtils.parseInt(request.getParameter("mangager_id"), 0);
        
        if (departmentId == 0 || managerId == 0) {
            setErrorResponse(response, "数据格式有误！", 401);
            return;
        }
        boolean flag = false;
        List<Employee> employees = employeeService.showAllEmployees();
        for (Employee employee : employees) {
            if (managerId == employee.getEmployee_id() && departmentManager.equalsIgnoreCase(employee.getEmployee_name())) {
                flag = true;
                break;
            }
        }
        if (!flag) {
            setErrorResponse(response, "员工信息有误！", 402);
            return;
        }
        department.setDepartment_id(departmentId);
        department.setDepartment_name(departmentName);
        department.setDepartment_manager(departmentManager);
        department.setEmployee_id(managerId);
    }
    
    protected void queryDepartmentAllEmp(HttpServletRequest request , HttpServletResponse response){
        String department_id = request.getParameter("department_id");
        int departmentID = DataUtils.parseInt(department_id, -1);
        if (departmentID == -1){
            setErrorResponse(response, "数据格式有误！", 401);
            return;
        }
        List<Employee> departmentsList = departmentService.queryDepartAllEmp(departmentID);
    
        try {
            response.setContentType("text/html;charset=utf-8");
            ResponseInfo<Employee> responseInfo = new ResponseInfo<>();
            //layui表格显示需要的返回信息
            responseInfo.setCode(0);
            responseInfo.setMsg("success");
            responseInfo.setCount(departmentsList.size());
            responseInfo.setData(departmentsList);
            //转化成JSON对象返回
            Gson gson = new Gson();
            response.getWriter().write(gson.toJson(responseInfo));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
