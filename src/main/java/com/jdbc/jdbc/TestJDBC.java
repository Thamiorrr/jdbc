package com.jdbc.jdbc;

import org.junit.Test;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapters;
import java.sql.*;
import java.util.Collection;

/**
 * @author Hasee
 * @Data 2021/1/22 10:14
 */
public class TestJDBC {
    @Test
    public void test() throws Exception {
    //加载驱动
        //获取连接
        //生成发送器
        //执行
        //释放资源
        //加载驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        //准备参数
        String url = "jdbc:mysql://localhost:3306/dproject?serverTimezone=GMT%2B8&characterEncoding=utf-8&useSSL=FALSE";
        String username = "root";
        String password = "root";
        //得到连接
        Connection connection = DriverManager.getConnection(url,username,password);
        System.out.println(connection);
        Statement statement = connection.createStatement();
        String sql = "insert into tb_branch VALUES ('666','fff')";
        int i = statement.executeUpdate(sql);
        System.out.println(i);
    }
    @Test
    public void test1() throws Exception {
        //准备参数
        String url = "jdbc:mysql://localhost:3306/dproject?serverTimezone=GMT%2B8&characterEncoding=utf-8&useSSL=FALSE";
        String username = "root";
        String password = "root";
        //加载驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        //得到连接
        Connection connection = DriverManager.getConnection(url,username,password);
        //创建statement
        Statement statement = connection.createStatement();
        //使用statement发送sql
        String sql = "select * from tb_branch";

        ResultSet resultSet = statement.executeQuery(sql);

        //解析ResultSet
        while (resultSet.next()){//把光标向下移动一行，并判断下一行是否存在！
            int branchId = resultSet.getInt(1);//通过列编号来获取该列的值
            String branchName = resultSet.getString("branch_name");//通过列名称来获取该列的值
            System.out.println(branchId +  ", " + branchName);
        }
        //倒序关闭资源
        resultSet.close();
        statement.close();
        connection.close();

    }
}
