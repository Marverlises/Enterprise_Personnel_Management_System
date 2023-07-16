package com.by.coursedesign.web;

import com.by.coursedesign.entity.Training;
import com.by.coursedesign.service.TrainingService;
import com.by.coursedesign.service.impl.TrainingServiceImpl;
import com.by.coursedesign.utils.DataUtils;
import com.by.coursedesign.utils.ResponseInfo;
import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Author Baiyu
 * @Time 2023/6/24 8:51
 * @StudentNumber 2018217662
 * @Description
 */
public class TrainingServlet extends BasicServlet {
    private TrainingService trainingService = new TrainingServiceImpl();
    
    protected void queryAllItems(HttpServletRequest request, HttpServletResponse response) {
        List<Training> trainings = trainingService.queryAllItems();
        ResponseInfo<Training> trainingResponseInfo = new ResponseInfo<>();
        trainingResponseInfo.setCode(0);
        trainingResponseInfo.setMsg("success");
        trainingResponseInfo.setCount(trainings.size());
        trainingResponseInfo.setData(trainings);
        response.setContentType("text/html;charset=utf-8");
        
        Gson gson = new Gson();
        try {
            response.getWriter().write(gson.toJson(trainingResponseInfo));
        } catch (IOException e) {
            response.setStatus(403);
            e.printStackTrace();
        }
    }
    
    protected void addTraining(HttpServletRequest request, HttpServletResponse response) {
        Training training = getTraining(request, response);
        if (training == null){
            return;
        }
        System.out.println(training);
        boolean b = trainingService.addTraining(training);
        setResponse(response, b);
    }
    
    protected void updateTraining(HttpServletRequest request, HttpServletResponse response) {
        Training training = getTraining(request, response);
        if (training == null){
            return;
        }
        boolean b = trainingService.updateTraining(training);
        setResponse(response, b);
    }
    
    protected void deleteItem(HttpServletRequest request, HttpServletResponse response){
        String trainingId = request.getParameter("training_id");
        boolean b = trainingService.deleteItem(trainingId);
        setResponse(response, b);
    }
    
    protected Training getTraining(HttpServletRequest request, HttpServletResponse response) {
        String trainingName = request.getParameter("training_name");
        String trainingDescription = request.getParameter("training_description");
        String startTime = request.getParameter("start_time");
        String endTime = request.getParameter("end_time");
        String trainingId = null;
        Integer totalParticipants = null;
        if (request.getParameter("action").equalsIgnoreCase("updateTraining")) {
            trainingId = request.getParameter("training_id");
            totalParticipants = DataUtils.parseInt(request.getParameter("total_participants"), -1);
            if (totalParticipants == -1){
                return null;
            }
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = null;
        Date endDate = null;
        try {
            startDate = format.parse(startTime);
            endDate = format.parse(endTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (endDate.before(startDate)) {
            setErrorResponse(response, "数据格式有误!", 401);
            return null;
        }
        Training training = new Training();
        training.setTraining_name(trainingName);
        training.setTraining_description(trainingDescription);
        training.setStart_date(startDate);
        training.setEnd_date(endDate);
        if (request.getParameter("action").equalsIgnoreCase("updateTraining")) {
            training.setTraining_id(trainingId);
            training.setTotal_participants(totalParticipants);
        }
        return training;
    }
}
