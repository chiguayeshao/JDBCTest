package com.jdbc.test01;

import com.jdbc.util.JDBCUtils;

import java.sql.*;
import java.util.Scanner;

/**
 * PreparedStatement
 * 通过键盘录入用户名和密码
 * 判断用户是否登录成功
 *
 * @author yeshao
 * @date 2019/6/23 - 13:35
 */
public class JDBCDemo9 {
    public static void main(String[] args) {
        //1.键盘录入，接收用户名和密码
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入用户名：");
        String username = sc.nextLine();
        System.out.println("请输入密码：");
        String password = sc.nextLine();
        //2.调用方法
        boolean flag = new JDBCDemo9().login(username, password);
        //3.判断结果，输出不同语句
        if (flag){
            //登录成功
            System.out.println("登录成功");
        }else {
            System.out.println("用户名或密码错误");
        }
    }

    /**
     * 登陆方法，使用PreparedStatement实现
     */

    public boolean login(String username, String password) {
        if (username == null || password == null) {
            return false;
        }
        //连接数据库判断是否登录成功
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        //1.获取连接
        try {
            conn = JDBCUtils.getConnection();
            //2.定义sql
            String sql = "select * from user where username = ? and password = ?";
            //3.获取执行sql的对象
            ps = conn.prepareStatement(sql);
            //给?赋值
            ps.setString(1,username);
            ps.setString(2,password);
            //4.执行查询,不需要传递sql
            rs = ps.executeQuery();
            //5.判断
            return rs.next();//如果有下一行，则返回true

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(rs, ps, conn);
        }

        return false;
    }
}
