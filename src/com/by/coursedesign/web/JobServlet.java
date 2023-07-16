package com.by.coursedesign.web;

import com.by.coursedesign.entity.Employee;
import com.by.coursedesign.entity.Job;
import com.by.coursedesign.service.JobService;
import com.by.coursedesign.service.impl.JobServiceImpl;
import com.by.coursedesign.utils.DataUtils;
import com.by.coursedesign.utils.ResponseInfo;
import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Baiyu
 * @Time 2023/6/20 10:03
 * @StudentNumber 2018217662
 * @Description
 */
public class JobServlet extends BasicServlet {
    private JobService jobService = new JobServiceImpl();
    
    protected void queryAllJob(HttpServletRequest request, HttpServletResponse response) {
        List<Job> jobList = jobService.queryAllJob();
        try {
            response.setContentType("text/html;charset=utf-8");
            ResponseInfo<Job> responseInfo = new ResponseInfo<>();
            //layui表格显示需要的返回信息
            responseInfo.setCode(0);
            responseInfo.setMsg("success");
            responseInfo.setCount(jobList.size());
            responseInfo.setData(jobList);
            //转化成JSON对象返回
            Gson gson = new Gson();
            response.getWriter().write(gson.toJson(responseInfo));
        } catch (IOException e) {
            response.setStatus(403);
            e.printStackTrace();
        }
    }
    
    protected void updateJob(HttpServletRequest request, HttpServletResponse response) {
        Job job = new Job();
        verifyJob(request, response, job);
        boolean b = jobService.updateJob(job);
        setResponse(response, b);
    }
    
    protected void deleteJobById(HttpServletRequest request, HttpServletResponse response){
        int position_id = DataUtils.parseInt(request.getParameter("position_id"), 0);
        if (position_id == 0){
            setErrorResponse(response, "数据格式有误！", 401);
            return;
        }
        boolean b = jobService.deleteJobById(position_id);
        setResponse(response, b);
    }
    
    protected void addJob(HttpServletRequest request, HttpServletResponse response){
        Job job = new Job();
        verifyJob(request,response,job);
        System.out.println(job);
        boolean b = jobService.addJob(job);
        setResponse(response, b);
    }
    
    private void verifyJob(HttpServletRequest request, HttpServletResponse response, Job job) {
        Integer positionId = DataUtils.parseInt(request.getParameter("position_id"), 0);
        String jobName = request.getParameter("job_name");
        String jobResponsibility = request.getParameter("job_responsibility");
        if (positionId == 0) {
            setErrorResponse(response, "数据格式有误！", 401);
            return;
        }
        job.setPosition_id(positionId);
        job.setPosition_name(jobName);
        job.setResponsibilities(jobResponsibility);
    }
    
    
}
