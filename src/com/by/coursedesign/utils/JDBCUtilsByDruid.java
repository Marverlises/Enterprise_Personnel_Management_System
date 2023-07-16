package com.by.coursedesign.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @Author Baiyu
 * @Time 2023/6/11 8:58
 * @StudentNumber 2018217662
 * @Description
 */
public class JDBCUtilsByDruid {

    private static DataSource ds;
    //定义属性ThreadLocal, 这里存放一个Connection
    private static ThreadLocal<Connection> threadLocalConn =
            new ThreadLocal<>();

    //在静态代码块完成 ds初始化
    static {
        Properties properties = new Properties();
        try {
            //因为我们是web项目，他的工作目录在out, 文件的加载，需要使用类加载器
            properties.load(JDBCUtilsByDruid.class.getClassLoader()
                    .getResourceAsStream("druid.properties"));
            //properties.load(new FileInputStream("src\\druid.properties"));
            ds = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 从ThreadLocal获取connection, 从而保证在同一个线程中，
     * 获取的是同一个Connection
     *
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() {

        Connection connection = threadLocalConn.get();
        if (connection == null) {//说明当前的threadLocalConn没有连接
            //就从数据库连接池中,取出一个连接放入, threadLocalConn
            try {
                connection = ds.getConnection();
                //将连接设置为手动提交, 即不要自动提交
                connection.setAutoCommit(false);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            threadLocalConn.set(connection);
        }
        return connection;
    }

    /**
     * 提交事务, java基础 mysql事务+线程+过滤器机制+ThreadLocal
     */
    public static void commit() {

        Connection connection = threadLocalConn.get();
        if (connection != null) {//确保该连接是有效

            try {
                connection.commit();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                try {
                    connection.close();//关闭连接
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }

        //老师说明
        //1. 当提交后，需要把connection从 threadLocalConn 清除掉
        //2. 不然，会造成 threadLocalConn 长时间持有该连接, 会影响效率
        //3. 也因为我们Tomcat底层使用的是线程池技术
        threadLocalConn.remove();

    }

    /**
     * 老师说明:  所谓回滚，是回滚/撤销和 connection管理的操作删掉，修改，添加
     */
    public static void rollback() {

        Connection connection = threadLocalConn.get();
        if (connection != null) {//保证当前的连接是有效

            try {
                connection.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }

        threadLocalConn.remove();
    }


    //关闭连接, 老师再次强调： 在数据库连接池技术中，close 不是真的断掉连接
    //而是把使用的Connection对象放回连接池
    public static void close(ResultSet resultSet, Statement statement, Connection connection) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
