package com.jdbc.jdbc;

import Util.JdbcUtil;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.junit.Test;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

/**
 * @author Hasee
 * @Data 2021/1/25 14:48
 */
public class C3P0Demo {
    @Test
    public void method() throws PropertyVetoException, SQLException {
        ComboPooledDataSource ds = new ComboPooledDataSource();
        ds.setJdbcUrl("jdbc:mysql://localhost:3306/dproject?serverTimezone=GMT%2B8&characterEncoding=utf-8&useSSL=FALSE");
        ds.setUser("root");
        ds.setPassword("root");
        ds.setDriverClass("com.mysql.cj.jdbc.Driver");
        ds.setAcquireIncrement(5);
        ds.setInitialPoolSize(20);
        ds.setMinPoolSize(2);
        ds.setMaxPoolSize(50);
        Connection con = ds.getConnection();
        System.out.println(con);
        con.close();
    }
    public void method1() throws SQLException {
        ComboPooledDataSource ds = new ComboPooledDataSource();
        Connection con = ds.getConnection();
        System.out.println(con);
        con.close();
    }
    @Test
    public void testUpdate() throws SQLException {
        QueryRunner qyeryRunner = new QueryRunner();
        Connection connection = null;
        connection = JdbcUtil.getConnection();
        String sql = "insert into tb_branch values(?,?)";

        qyeryRunner.update(connection,sql,"4","长清店");
        connection.close();
    }
}