package com.jdbc.test01;

import com.jdbc.domain.Exe;
import com.jdbc.util.JDBCUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 定义一个方法，查询exer表的数据，将其封装为一个对象，然后装载集合，返回。
 *
 * @author yeshao
 * @date 2019/6/23 - 12:29
 */
public class JDBCDemo7 {

    public static void main(String[] args) {
        List<Exe> all = new JDBCDemo7().findAll();
        System.out.println(all);
    }

//    /*
//    查询所有exe对象
//     */
//    public List<Exe> findAll() {
//
//        List<Exe> list=null;
//        Connection conn = null;
//        Statement stmt = null;
//        ResultSet rs = null;
//
//        //1.注册驱动
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            //2.获取连接
//            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "980915");
//            //3.定义sql
//            String sql = "select * from exer";
//            //4.获取执行sql的对象
//            stmt = conn.createStatement();
//            //5.执行sql
//            rs = stmt.executeQuery(sql);
//            //6.遍历结果集，封装对象，装载集合
//            Exe exe = null;
//            list = new ArrayList<Exe>();
//            while (rs.next()) {
//                //获取数据
//                int id = rs.getInt("id");
//                String name = rs.getString("name");
//                int balance = rs.getInt("balance");
//                //创建exe对象,并赋值
//                exe = new Exe();
//                exe.setId(id);
//                exe.setName(name);
//                exe.setBaklance(balance);
//                //装载集合
//                list.add(exe);
//            }
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            if (rs != null) {
//                try {
//                    rs.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//            if (stmt != null) {
//                try {
//                    stmt.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//            if (conn != null) {
//                try {
//                    conn.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//
//        return list;
//    }

    /*
       演示JDBC工具类
        */
    public List<Exe> findAll() {

        List<Exe> list = null;
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtils.getConnection();
            //3.定义sql
            String sql = "select * from exer";
            //4.获取执行sql的对象
            stmt = conn.createStatement();
            //5.执行sql
            rs = stmt.executeQuery(sql);
            //6.遍历结果集，封装对象，装载集合
            Exe exe = null;
            list = new ArrayList<Exe>();
            while (rs.next()) {
                //获取数据
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int balance = rs.getInt("balance");
                //创建exe对象,并赋值
                exe = new Exe();
                exe.setId(id);
                exe.setName(name);
                exe.setBaklance(balance);
                //装载集合
                list.add(exe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(rs, stmt, conn);
        }

        return list;
    }
}


