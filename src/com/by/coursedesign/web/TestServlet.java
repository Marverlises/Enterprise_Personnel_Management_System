package com.by.coursedesign.web;

import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @Author Baiyu
 * @Time 2023/6/20 11:59
 * @StudentNumber 2018217662
 * @Description
 */

// 创建数据结构类
class ChartData {
    private int code;
    private Data data;
    private String msg;
    
    // 构造方法、getter和setter省略
    
    // 内部数据结构类
    static class Data {
        private Sale sale;
        private List<Total> total;
        
        // 构造方法、getter和setter省略
    
        public Sale getSale() {
            return sale;
        }
    
        public void setSale(Sale sale) {
            this.sale = sale;
        }
    
        public List<Total> getTotal() {
            return total;
        }
    
        public void setTotal(List<Total> total) {
            this.total = total;
        }
    
        static class Sale {
            private List<String> week;
            private List<String> fee;
            
            // 构造方法、getter和setter省略
    
            public List<String> getWeek() {
                return week;
            }
    
            public void setWeek(List<String> week) {
                this.week = week;
            }
    
            public List<String> getFee() {
                return fee;
            }
    
            public void setFee(List<String> fee) {
                this.fee = fee;
            }
        }
        
        static class Total {
            private String name;
            private String value;
            
            // 构造方法、getter和setter省略
    
            public String getName() {
                return name;
            }
    
            public void setName(String name) {
                this.name = name;
            }
    
            public String getValue() {
                return value;
            }
    
            public void setValue(String value) {
                this.value = value;
            }
        }
    }
    
    public int getCode() {
        return code;
    }
    
    public void setCode(int code) {
        this.code = code;
    }
    
    public Data getData() {
        return data;
    }
    
    public void setData(Data data) {
        this.data = data;
    }
    
    public String getMsg() {
        return msg;
    }
    
    public void setMsg(String msg) {
        this.msg = msg;
    }
}
public class TestServlet extends BasicServlet {
    protected void testAPI(HttpServletRequest request, HttpServletResponse response){
        // 创建数据并转化为JSON格式
        ChartData chartData = new ChartData();
        chartData.setCode(0);
        chartData.setMsg("成功获取到图表数据！");
    
        ChartData.Data.Sale sale = new ChartData.Data.Sale();
        sale.setWeek(Arrays.asList("第1周", "第2周", "第3周", "第4周"));
        sale.setFee(Arrays.asList("1000", "700", "800", "1200"));
        chartData.getData().setSale(sale);
    
        ChartData.Data.Total total1 = new ChartData.Data.Total();
        total1.setName("销售总额");
        total1.setValue("3700");
    
        ChartData.Data.Total total2 = new ChartData.Data.Total();
        total2.setName("宣传费用");
        total2.setValue("500");
    
        chartData.getData().setTotal(Arrays.asList(total1, total2));
    
        Gson gson = new Gson();
        String jsonData = gson.toJson(chartData);
        System.out.println(jsonData);
        try {
            response.getWriter().write(gson.toJson(jsonData));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
