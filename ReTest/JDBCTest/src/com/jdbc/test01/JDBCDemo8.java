package com.jdbc.test01;

import com.jdbc.util.JDBCUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * 通过键盘录入用户名和密码
 * 判断用户是否登录成功
 *
 * @author yeshao
 * @date 2019/6/23 - 13:35
 */
public class JDBCDemo8 {
    public static void main(String[] args) {
        //1.键盘录入，接收用户名和密码
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入用户名：");
        String username = sc.nextLine();
        System.out.println("请输入密码：");
        String password = sc.nextLine();
        //2.调用方法
        boolean flag = new JDBCDemo8().login(username, password);
        //3.判断结果，输出不同语句
        if (flag){
            //登录成功
            System.out.println("登录成功");
        }else {
            System.out.println("用户名或密码错误");
        }
    }

    /**
     * 登陆方法
     */

    public boolean login(String username, String password) {
        if (username == null || password == null) {
            return false;
        }
        //连接数据库判断是否登录成功
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        //1.获取连接
        try {
            conn = JDBCUtils.getConnection();
            //2.定义sql
            String sql = "select * from user where username = '" + username + "' and password = '" + password + "'";
            //3.获取执行sql的对象
            stmt = conn.createStatement();
            //4.执行查询
            rs = stmt.executeQuery(sql);
            //5.判断
//            if (rs.next()){//如果有下一行，则返回true
//                return true;
//            }else {
//                return false;
//            }
            return rs.next();//如果有下一行，则返回true

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(rs, stmt, conn);
        }

        return false;
    }
}
