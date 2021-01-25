package com.jdbc.jdbc;

import Util.JdbcUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.*;
import org.junit.Test;
import org.springframework.jdbc.support.JdbcUtils;
import pojo.Branch;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author Hasee
 * @Data 2021/1/25 17:43
 */
public class QueryRunnerDemo {
    @Test
    public void testUpdate() throws SQLException {
        QueryRunner qyeryRunner = new QueryRunner();
        Connection connection = null;
        connection = JdbcUtil.getConnection();
        String sql = "insert into tb_branch values(?,?)";

        qyeryRunner.update(connection,sql,"4","长清店");
        connection.close();
    }
    @Test
    public void fun1() {
        //获取数据源
        DataSource ds = null;
        ds = JdbcUtil.getDataSource();
        //创建QueryRunner
        QueryRunner qr = new QueryRunner(ds);
        //sql
        String sql = "select * from tb_branch where id=?";
        Map<String,Object> map = null;
        try {
            map = qr.query(sql, new MapHandler(), "111");
        } catch (SQLException throwables) {
            System.out.println("查询失败");
        }
        System.out.println(map);
    }
    /**
     * 多行多列
     * MapListHandler()
     * 结果集中的每一行数据封装到map，存放到list
     */
    @Test
    public void fun2() {
        DataSource ds = JdbcUtil.getDataSource();
        QueryRunner qr = new QueryRunner(ds);
        String sql = "select * from tb_branch";
        List<Map<String, Object>> list = null;
        try {
            list = qr.query(sql, new MapListHandler());
        } catch (SQLException throwables) {
            System.out.println("查询失败");
        }
        for (Map<String, Object> map : list) {
            System.out.println(map);
        }
    }

    /**
     *
     *  查询到的数据封装到对象中
     */

    @Test
    public void fun3() throws SQLException {
        DataSource ds = JdbcUtil.getDataSource();
        QueryRunner qr = new QueryRunner(ds);
        String sql = "select * from tb_branch where id=?";
//        try {
        Branch branch = qr.query(sql, new BeanHandler<Branch>(Branch.class), "111");
//        } catch (SQLException throwables) {
//            System.out.println("查询失败");
//        }
        System.out.println(branch.getId());
        System.out.println(branch.getName());

    }

    /**
     * 多行多列
     * 所有查到的数据封装到多个对象
     */
    @Test
    public void fun4() {
        DataSource ds = JdbcUtil.getDataSource();
        QueryRunner qr = new QueryRunner(ds);
        String sql = "select * from tb_branch";
        List<Branch> list = null;
        try {
            list = qr.query(sql, new BeanListHandler<Branch>(Branch.class));
        } catch (SQLException throwables) {
            System.out.println("查询失败");
        }
        for(Branch branch : list) {
            System.out.println(branch);
        }
    }

    /**
     *多行单列
     *查询name所有内容
     */
    @Test
    public void fun5() throws SQLException {
        DataSource ds = JdbcUtil.getDataSource();
        QueryRunner qr = new QueryRunner(ds);
        String sql = "select * from tb_Branch";
        List<Object> list = qr.query(sql, new ColumnListHandler<>("branch_name"));
        for(Object s : list) {
            System.out.println(s);
        }
    }

    /**
     *ScalarHandler：单行单列处理器！把结果集转换成 Object。一般用于聚集查询
     *
     */
    @Test
    public void fun6() {
        DataSource ds = JdbcUtil.getDataSource();
        QueryRunner qr = new QueryRunner(ds);
        String sql = "select count(*) from tb_branch";
        Number number = null;
        try {
            number = (Number)qr.query(sql, new ScalarHandler());
        } catch (SQLException throwables) {
            System.out.println("统计失败");
        }
        int cnt = number.intValue();
        System.out.println(cnt);
    }

    /**
     * 批处理
     *
     */
    @Test
    public void fun10()  {
        DataSource ds = JdbcUtil.getDataSource();
        QueryRunner qr = new QueryRunner(ds);
        String sql = "insert into tb_branch values(?,?)";
        Object[][] params = new Object[10][];
        //表示 要插入10行记录
         for(int i = 0; i < params.length; i++) {
            params[i] = new Object[]{"S_400" + i, "namea" + i,};
        }
        try {
            qr.batch(sql, params);
        } catch (SQLException throwables) {
            System.out.println("插入失败");
        }
    }
}
