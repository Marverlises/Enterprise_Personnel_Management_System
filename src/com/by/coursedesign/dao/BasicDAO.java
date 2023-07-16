package com.by.coursedesign.dao;

import com.by.coursedesign.utils.DataUtils;
import com.by.coursedesign.utils.JDBCUtilsByDruid;
import com.by.coursedesign.utils.JDBCUtilsByDruidClient;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @Author Baiyu
 * @Time 2023/6/11 10:37
 * @StudentNumber 2018217662
 * @Description
 */
public class BasicDAO<T> { //泛型指定具体类型
    
    private QueryRunner qr = new QueryRunner();
    
    //开发通用的dml方法, 针对任意的表
    public int update(String sql, Object... parameters) {
        Connection connection = null;
        try {
            //这里从数据库连接池获取connection
            if (DataUtils.user.getPermission_level() == 1) {
                connection = JDBCUtilsByDruid.getConnection();
            } else {
                connection = JDBCUtilsByDruidClient.getConnection();
            }
            int update = qr.update(connection, sql, parameters);
            if (DataUtils.user.getPermission_level() == 1) {
                JDBCUtilsByDruid.commit();
            } else {
                JDBCUtilsByDruidClient.commit();
            }
            return update;
        } catch (SQLException e) {
            throw new RuntimeException(e); //将编译异常->运行异常 ,抛出
        } finally {
            if (DataUtils.user.getPermission_level() == 1) {
                JDBCUtilsByDruid.close(null, null, connection);
            } else {
                JDBCUtilsByDruidClient.close(null, null, connection);
            }
        }
    }
    
    /**
     * 返回多个对象(即查询的结果是多行), 针对任意表
     *
     * @param sql        sql 语句，可以有 ?
     * @param clazz      传入一个类的Class对象 比如 Actor.class
     * @param parameters 传入 ? 的具体的值，可以是多个
     * @return 根据Actor.class 返回对应的 ArrayList 集合
     */
    public List<T> queryMulti(String sql, Class<T> clazz, Object... parameters) {
        Connection connection = null;
        try {
            if (DataUtils.user.getPermission_level() == 1) {
                connection = JDBCUtilsByDruid.getConnection();
            } else {
                connection = JDBCUtilsByDruidClient.getConnection();
            }
            List<T> query = qr.query(connection, sql, new BeanListHandler<T>(clazz), parameters);
            if (DataUtils.user.getPermission_level() == 1) {
                JDBCUtilsByDruid.commit();
            } else {
                JDBCUtilsByDruidClient.commit();
            }
            return query;
        } catch (SQLException e) {
            throw new RuntimeException(e); //将编译异常->运行异常 ,抛出
        } finally {
            if (DataUtils.user.getPermission_level() == 1) {
                JDBCUtilsByDruid.close(null, null, connection);
            } else {
                JDBCUtilsByDruidClient.close(null, null, connection);
            }
        }
        
    }
    
    //查询单行结果 的通用方法
    public T querySingle(String sql, Class<T> clazz, Object... parameters) {
        Connection connection = null;
        try {
            if (DataUtils.user.getPermission_level() == 1) {
                connection = JDBCUtilsByDruid.getConnection();
            } else {
                connection = JDBCUtilsByDruidClient.getConnection();
            }
            T query = qr.query(connection, sql, new BeanHandler<T>(clazz), parameters);
            if (DataUtils.user.getPermission_level() == 1) {
                JDBCUtilsByDruid.commit();
            } else {
                JDBCUtilsByDruidClient.commit();
            }
            return query;
        } catch (SQLException e) {
            throw new RuntimeException(e); //将编译异常->运行异常 ,抛出
        } finally {
            if (DataUtils.user.getPermission_level() == 1) {
                JDBCUtilsByDruid.close(null, null, connection);
            } else {
                JDBCUtilsByDruidClient.close(null, null, connection);
            }
        }
    }
    
    //查询单行单列的方法,即返回单值的方法
    public Object queryScalar(String sql, Object... parameters) {
        Connection connection = null;
        try {
            if (DataUtils.user.getPermission_level() == 1) {
                connection = JDBCUtilsByDruid.getConnection();
            } else {
                connection = JDBCUtilsByDruidClient.getConnection();
            }
            Object query = qr.query(connection, sql, new ScalarHandler(), parameters);
            if (DataUtils.user.getPermission_level() == 1) {
                JDBCUtilsByDruid.commit();
            } else {
                JDBCUtilsByDruidClient.commit();
            }
            return query;
        } catch (SQLException e) {
            throw new RuntimeException(e); //将编译异常->运行异常 ,抛出
        } finally {
            if (DataUtils.user.getPermission_level() == 1) {
                JDBCUtilsByDruid.close(null, null, connection);
            } else {
                JDBCUtilsByDruidClient.close(null, null, connection);
            }
        }
    }
    
    public boolean done(int update) {
        if (update == 0) {
            return false;
        } else {
            return true;
        }
        
    }
    
}

