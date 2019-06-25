package com.jdbc.test01;

import java.sql.*;

/**
 * 删除记录
 *
 * @author yeshao
 * @date 2019/6/21 - 15:42
 */
public class JDBCDemo6 {

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "980915");

            String sql = "select * from exer";

            stmt = conn.createStatement();

            rs = stmt.executeQuery(sql);

            //让游标向下移动一行
            while (rs.next()){
                //循环判断是否有数据
                //获取数据
                int id = rs.getInt(1);
                String name = rs.getString("name");
                int balance = rs.getInt(3);

                System.out.println(id + " " + name + " " + balance);
            }



        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
