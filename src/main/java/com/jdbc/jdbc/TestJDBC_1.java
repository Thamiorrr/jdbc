package com.jdbc.jdbc;

import Util.JdbcUtil;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import sun.nio.ch.IOUtil;

import javax.sql.rowset.serial.SerialBlob;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Collection;

/**
 * @author Hasee
 * @Data 2021/1/22 13:47
 */

public class TestJDBC_1 {
    /**
     * 获取数据库连接
     * @return
     */
    public static Connection getConnection() {
//        String url = "jdbc:mysql://localhost:3306/dproject?serverTimezone=GMT%2B8&characterEncoding=utf-8&useSSL=FALSE";
//        String username = "root";
//        String password = "root";
        Connection connection = null;
        //加载驱动
        try {
//            Class.forName(JdbcUtil.readProperties("driver"));
            connection = DriverManager.getConnection(JdbcUtil.readProperties("url"),JdbcUtil.readProperties("user"),JdbcUtil.readProperties("password"));
        } catch (SQLException throwables) {
            System.out.println("连接异常");}
//        } catch (ClassNotFoundException throwables){
//            System.out.println("加载驱动失败");
//        }
        System.out.println(connection);
        if(connection != null){
            return connection;
        }
        return connection;
    }

    @Test
    public void test() {
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = getConnection();
        String sql = "select * from tb_branch";
        try {
            statement = connection.createStatement();
        }catch (SQLException throwables){
            System.out.println("创建语句发送器失败");
        }
        try {
            resultSet = statement.executeQuery(sql);
        }catch (SQLException throwables){
            System.out.println("sql语句执行失败");
        }

        //解析ResultSet
        try{
        while (resultSet.next()){//把光标向下移动一行，并判断下一行是否存在！
            int branchId = resultSet.getInt(1);//通过列编号来获取该列的值
            String branchName = resultSet.getString("branch_name");//通过列名称来获取该列的值
            System.out.println(branchId +  ", " + branchName);
        }}
        catch (SQLException throwables){
            System.out.println("查询结果解析失败");
        }
        //倒序关闭资源
        try {
            resultSet.close();
            statement.close();
            connection.close();
        }catch (SQLException a){
            System.out.println("资源释放失败");
        }
    }
    @Test
    public  void mp3() throws Exception {
        PreparedStatement preparedStatement = null;
        //建立连接
        Connection connection = getConnection();
        //语句发送器
        String sql = "insert into mp3 values(?,?,?)";
        preparedStatement = connection.prepareStatement(sql);
        //MP3转化为bytearray
        byte[] bytes = IOUtils.toByteArray(new FileInputStream("D:/demo/sound.mp3"));
        Blob blob = new SerialBlob(bytes);
        preparedStatement.setInt(1,1);
        preparedStatement.setString(2,"sound");
        preparedStatement.setBlob(3,blob);
        preparedStatement.executeUpdate();

    }
}
