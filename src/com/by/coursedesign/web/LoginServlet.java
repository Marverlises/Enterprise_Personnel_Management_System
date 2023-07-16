package com.by.coursedesign.web;

import com.by.coursedesign.entity.Employee;
import com.by.coursedesign.entity.User;
import com.by.coursedesign.service.impl.UserServiceImpl;
import com.by.coursedesign.utils.DataUtils;
import com.by.coursedesign.utils.RSAUtils;
import com.by.coursedesign.utils.ResponseInfo;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @Author Baiyu
 * @Time 2023/6/13 14:40
 * @StudentNumber 2018217662
 * @Description
 */
public class LoginServlet extends BasicServlet {
    
    //相应的service属性
    private UserServiceImpl userService = new UserServiceImpl();
    
    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        String encodedUserName = request.getParameter("username");
        String encodedPassword = request.getParameter("password");
        String usertype = request.getParameter("usertype");
        
        //进行解密操作
        String username = RSAUtils.getDecryptData(encodedUserName);
        String password = RSAUtils.getDecryptData(encodedPassword);
        
        
        int type = Integer.parseInt(usertype);
        User user = new User(null, username, password, null, type, null);
        DataUtils.user = user;
        User loginUser = userService.login(user);
        
        request.getSession().setAttribute("user", loginUser);
        if (loginUser == null) {
            //把登录错误信息,放入到request域
            request.setAttribute("msg", "用户名或者密码错误!");
            request.setAttribute("username", username);
            //页面转发
            request.getRequestDispatcher("/views/user/login.jsp").forward(request, response);
        } else {
            System.out.println("登陆成功！");
            //如果是管理员登录
            if (type == 1) {
                request.getRequestDispatcher("/views/manage/manage_index.jsp").forward(request, response);
                return;
            }
            //如果是普通员工
            if (type == 0) {
                request.getRequestDispatcher("/views/employee/index.jsp").forward(request, response);
                return;
            }
            
        }
    }
    
    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //销毁当前用户的session
        req.getSession().invalidate();
        //重定向到网站首页-> 刷新首页
        
        PrintWriter writer = resp.getWriter();
        writer.println("<html>");
        writer.println("<script>");
        writer.println("window.open ('" + req.getContextPath() + "','_top')");
        writer.println("</script>");
        writer.println("</html>");
    }
    
    
    protected void queryAllUser(HttpServletRequest request, HttpServletResponse response) {
        List<User> users = userService.queryAllUser();
        
        try {
            response.setContentType("text/html;charset=utf-8");
            ResponseInfo<User> responseInfo = new ResponseInfo<>();
            //layui表格显示需要的返回信息
            responseInfo.setCode(0);
            responseInfo.setMsg("success");
            responseInfo.setCount(users.size());
            responseInfo.setData(users);
            //转化成JSON对象返回
            Gson gson = new Gson();
            response.getWriter().write(gson.toJson(responseInfo));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    protected void insertUser(HttpServletRequest request, HttpServletResponse response) {
        User user = getUser(request, response, false);
        if (user == null) {
            return;
        }
        boolean b = userService.insertUser(user);
        setResponse(response, b);
    }
    
    protected void deleteById(HttpServletRequest request, HttpServletResponse response) {
        String userId = request.getParameter("user_id");
        int id = DataUtils.parseInt(userId, -1);
        if (id == -1) {
            setErrorResponse(response, "数据格式有误！", 401);
            return;
        }
        boolean b = userService.deleteById(id);
        setResponse(response, b);
    }
    
    protected void updateUser(HttpServletRequest request, HttpServletResponse response) {
        User user = getUser(request, response, true);
        if (user == null) {
            return;
        }
        boolean b = userService.updateUser(user);
        setResponse(response, b);
    }
    
    protected User getUser(HttpServletRequest request, HttpServletResponse response, boolean flag) {
        String userName = request.getParameter("user_name");
        String userPassword = request.getParameter("user_password");
        int permission = DataUtils.parseInt(request.getParameter("permission"), -1);
        int empId = DataUtils.parseInt(request.getParameter("emp_id"), -1);
        Integer userId = null;
        User user = new User();
        
        if (flag) {
            userId = DataUtils.parseInt(request.getParameter("user_id"), -1);
            if (userId == -1) {
                setErrorResponse(response, "数据格式有误！", 401);
                return null;
            }
            user.setUser_id(userId);
        }
        if (permission == -1 || !checkEmpExist(empId)) {
            setErrorResponse(response, "数据格式有误！", 401);
            return null;
        }
        user.setEmployee_id(empId);
        user.setPermission_level(permission);
        user.setUsername(userName);
        user.setUser_password(userPassword);
        return user;
    }
}
