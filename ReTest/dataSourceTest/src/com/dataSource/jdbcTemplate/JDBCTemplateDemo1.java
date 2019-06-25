package com.dataSource.jdbcTemplate;

import com.dataSource.utils.JDBCUtils;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * JDBCTemplate的入门
 *
 * @author yeshao
 * @date 2019/6/23 - 17:25
 */
public class JDBCTemplateDemo1 {
    public static void main(String[] args) {
        //1.导入jar包
        //2.创建JDBCTemplate对象
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        //3.调用方法
        String sql="update account set balance = 5000 where id = ?";
        template.update(sql,3);

    }
}
