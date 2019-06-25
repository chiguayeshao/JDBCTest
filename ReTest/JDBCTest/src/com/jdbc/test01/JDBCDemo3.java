package com.jdbc.test01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 修改记录
 *
 * @author yeshao
 * @date 2019/6/21 - 15:31
 */
public class JDBCDemo3 {

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "980915");

            String sql = "update exer set balance = 1500 where id = 3";

            stmt = conn.createStatement();

            int count = stmt.executeUpdate(sql);

            System.out.println(count);

            if (count>0){
                System.out.println("修改成功！");
            }else {
                System.out.println("修改失败！");
            }


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (stmt!=null){
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (conn!=null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
