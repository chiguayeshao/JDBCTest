package com.dataSource.jdbcTemplate;

import com.dataSource.domain.Exe;
import com.dataSource.utils.JDBCUtils;
import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author yeshao
 * @date 2019/6/23 - 17:35
 */
public class JDBCTemplateDemo2 {

    //1.获取jdbctemplate对象
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    /*
    将1号balance修改为2000
     */
    @Test
    public void test1(){
//        //1.获取jdbctemplate对象
//        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        //2.定义sql
        String sql="update exer set balance = 2000 where id = ?";
        //3.执行sql
        template.update(sql,1);
    }

    /*
    添加一条记录
     */
    @Test
    public void test2(){
        String sql="insert into exer values(null,?,?)";
        template.update(sql,"wangwu",3000);
    }

    /*
    删除一条记录
     */
    @Test
    public void test3(){
        String sql="delete from exer where id = ?";
        template.update(sql,2);
    }

    /*
    查询id为1的记录，将其封装为Map集合，将列名作为key，将结果作为value，将这条记录封装为一个Map集合
    注意：这个方法查询的结果长度只能为1
     */
    @Test
    public void test4(){
        String sql="select * from exer where id = ?";
        Map<String, Object> map = template.queryForMap(sql, 1);
        System.out.println(map);//{id=1, name=zhangsan, balance=2000}
    }

    /*
    查询所有记录，将其封装为list结婚
    注意：将每一条记录封装为Map集合，再将Map集合装载到List集合中
     */
    @Test
    public void test5(){
        String sql="select * from exer";
        List<Map<String, Object>> list = template.queryForList(sql);

        //iter
        for (Map<String, Object> stringObjectMap : list) {
            System.out.println(stringObjectMap);
        }
    }

    /*
    查询所有记录，将其封装为Exe对象的list集合
     */
    @Test
    public void test6_1(){
        String sql="select * from exer";
        List<Exe> list = template.query(sql, new RowMapper<Exe>() {
            @Override
            public Exe mapRow(ResultSet rs, int i) throws SQLException {
                Exe exe=new Exe();
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int balance = rs.getInt("balance");

                exe.setId(id);
                exe.setName(name);
                exe.setBaklance(balance);

                return exe;
            }
        });

        for (Exe exe : list) {
            System.out.println(exe);
        }
    }

    /*
    test6_1正确用法
     */
    @Test
    public void test6_2(){
        String sql="select * from exer";
        List<Exe> list = template.query(sql, new BeanPropertyRowMapper<Exe>(Exe.class));
        for (Exe exe : list) {
            System.out.println(exe);
        }
    }

    /*
    查询总记录数
     */
    @Test
    public void test7(){
        String sql="select count(id) from exer";
        Long total = template.queryForObject(sql, Long.class);
        System.out.println(total);
    }
}
