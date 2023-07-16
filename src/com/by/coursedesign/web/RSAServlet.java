package com.by.coursedesign.web;

import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.*;
import java.util.Base64;
import java.util.HashMap;

/**
 * @Author Baiyu
 * @Time 2023/6/25 15:17
 * @StudentNumber 2018217662
 * @Description
 */
public class RSAServlet extends BasicServlet{
    
    
    /**
     * 将公钥发送给客户端
     * @param request
     * @param response
     */
    public void getKey(HttpServletRequest request, HttpServletResponse response) {
        String pemFilePath = "D:\\OTHERS\\GItHubWorkSpace\\Database_Course_Design\\web\\WEB-INF\\publicKey.pem";
        
        try (BufferedReader reader = new BufferedReader(new FileReader(pemFilePath))) {
            StringBuilder pemContent = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                pemContent.append(line);
            }
            HashMap<String, StringBuilder> keyMap = new HashMap<>();
            keyMap.put("public_key", pemContent);
            Gson gson = new Gson();
            response.getWriter().write(gson.toJson(keyMap));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
