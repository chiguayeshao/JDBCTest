package com.jdbc.test01;

import com.jdbc.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 事务的操作
 *
 * @author yeshao
 * @date 2019/6/23 - 14:37
 */
public class JDBCDemo10 {

    public static void main(String[] args) {

        Connection conn = null;
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;

        try {
            //获取连接
            conn = JDBCUtils.getConnection();
            //开启事务
            conn.setAutoCommit(false);
            //定义sql
            //张三-500
            String sql1 = "update account set balance = balance - ? where id = ?";
            //李四+500
            String sql2 = "update account set balance = balance + ? where id = ?";
            //获取执行sql对象
            ps1 = conn.prepareStatement(sql1);
            ps2 = conn.prepareStatement(sql2);
            //设置参数
            ps1.setInt(1, 500);
            ps1.setInt(2, 1);

            ps2.setInt(1, 500);
            ps2.setInt(2, 2);

            //执行sql
            ps1.executeUpdate();

            //手动制造异常，测试事务
            int i = 3/0;

            ps2.executeUpdate();
            //提交事务
            conn.commit();
        } catch (Exception e) {
            //事务的回滚
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            JDBCUtils.close(ps1, conn);
            JDBCUtils.close(ps2, null);
        }
    }
}
