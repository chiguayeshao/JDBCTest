package com.jdbc.test01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 添加一条记录 insert语句
 *
 * @author yeshao
 * @date 2019/6/21 - 15:09
 */
public class JDBCDemo2 {

    public static void main(String[] args) {
        Connection conn=null;
        Statement stmt=null;
        try {
            //1.注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            //2.定义sql语句
            String sql="insert into exer values(null,'王五',3000)";
            //3.获取Connection对象
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","980915");
            //4.获取执行sql的对象
            stmt = conn.createStatement();
            //5.执行sql
            int count = stmt.executeUpdate(sql);
            //6.处理结果
            System.out.println(count);
            if (count>0){
                System.out.println("添加成功！");
            }else {
                System.out.println("添加失败！");
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
