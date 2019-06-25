package com.jdbc.test01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 删除记录
 *
 * @author yeshao
 * @date 2019/6/21 - 15:42
 */
public class JDBCDemo4 {

    public static void main(String[] args) {
        Connection conn=null;
        Statement stmt=null;

        try {
            Class.forName("com.mysql.jdbc.Driver");

            conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","980915");

            stmt=conn.createStatement();

            String sql="delete from exer where id = 3";

            int count = stmt.executeUpdate(sql);

            System.out.println(count);

            if (count>0){
                System.out.println("删除成功！");
            }else {
                System.out.println("删除失败");
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
