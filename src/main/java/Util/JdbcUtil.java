package Util;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author Hasee
 * @Data 2021/1/22 15:14
 */
public class JdbcUtil {
    @Test
    public static String readProperties(String key){
        //创建properties对象
        Properties properties= new Properties();
        String vaule = null;
//        InputStream js = JdbcUtil.class.getClassesLoader()
        try {
            properties = PropertiesLoaderUtils.loadAllProperties("jdbcconfig.properties");
            //根据入参查找
            vaule = properties.getProperty(key);
        } catch (IOException e) {
            System.out.println("加载配置文件失败!!!");
        } catch (NullPointerException npe) {
            System.out.println("文件路径有误");
        }
        //返回值
        return vaule;
    }
    private static DataSource dataSource = new ComboPooledDataSource();
    @Test
    public static Connection getConnection() throws SQLException {
        ComboPooledDataSource ds = new ComboPooledDataSource();
        ds.setAcquireIncrement(5);
        ds.setInitialPoolSize(20);
        ds.setMinPoolSize(2);
        ds.setMaxPoolSize(50);
        Connection con = ds.getConnection();
        System.out.println(con);
        return con;
    }
    @Test
    public static DataSource getDataSource() {
        return dataSource;
    }
}
