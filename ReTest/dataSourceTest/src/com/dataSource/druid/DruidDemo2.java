package com.dataSource.druid;

import com.dataSource.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 使用新的工具类
 *
 * @author yeshao
 * @date 2019/6/23 - 16:44
 */
public class DruidDemo2 {
    public static void main(String[] args) {
        /*
        完成添加操作，给account表添加一条记录
         */
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            //1.获取连接
            conn = JDBCUtils.getConnection();
            //2.定义sql
            String sql = "insert into account values(null,?,?)";
            //3.获取ps对象
            ps = conn.prepareStatement(sql);
            //4.给?赋值
            ps.setString(1, "王五");
            ps.setInt(2, 2000);
            //5.执行sql
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //6.释放资源
            JDBCUtils.close(ps, conn);
        }
    }
}
