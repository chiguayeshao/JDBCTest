package com.dataSource.c3p0;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * c3p0演示
 *
 * @author yeshao
 * @date 2019/6/23 - 15:34
 */
public class C3P0Demo2 {
    public static void main(String[] args) throws SQLException {
        //获取DataSource，使用默认配置
        DataSource ds = new ComboPooledDataSource();
        //获取连接
        for (int i = 1; i <= 11; i++) {
            Connection conn = ds.getConnection();
            System.out.println(i + ":" + conn);

            if (i == 5) {
                conn.close();//归还连接到连接池中
            }
        }

    }


    /*
    使用指定名称的配置
     */
    @Test
    public void testNamedConfig() throws SQLException {
        //获取DataSource，使用指定名称的配置
        DataSource ds = new ComboPooledDataSource("otherc3p0");
        //获取连接
        for (int i = 1; i <= 10; i++) {
            Connection conn=ds.getConnection();
            System.out.println(i + ":" + conn);
        }
    }
}
