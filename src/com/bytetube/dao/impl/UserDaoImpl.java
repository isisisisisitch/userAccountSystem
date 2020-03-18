package com.bytetube.dao.impl;

import com.bytetube.dao.UserDao;
import com.bytetube.domain.User;
import com.bytetube.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UserDaoImpl implements UserDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public List<User> findAll() {
        //1.编写sql
        String sql = "SELECT * FROM user";
        return  template.query(sql, new BeanPropertyRowMapper<User>(User.class));

    }

    @Override
    public User findUserByUsernameAndPassword(String username, String password) {
        String sql = "SELECT *  FROM `user` WHERE username=? AND PASSWORD = ?";
        User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username, password);
        return user;
    }

    @Override
    public void add(User user) {
        String sql= "INSERT INTO user VALUES(null,?,?,?,?,?,?,null,null)";
        template.update(sql,user.getName(),user.getGender(),user.getAge(),user.getAddress(),user.getQq(),user.getEmail());
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM user WHERE id=?";
        template.update(sql,id);
    }

    @Override
    public User findUserById(int id) {
        String sql = "select * FROM user WHERE id=?";
        User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), id);
        return user;
    }

    @Override
    public void update(User user) {
        String sql="UPDATE user SET NAME=?, gender=?,age=?,address=?,qq=?,email=? WHERE id= ?";
        template.update(sql,user.getName(),user.getGender(),user.getAge(),user.getAddress(),user.getQq(),user.getEmail(),user.getId());
    }

    @Override
    public int findTotalCount(Map<String, String[]> condition) {
        String sql = "SELECT COUNT(*) FROM user where 1=1 ";
        StringBuilder builder = new StringBuilder(sql);

        //遍历Map
        Set<String> keySet = condition.keySet();
        //定义一个参数的集合
        List<Object> params = new ArrayList<>();
        for (String key:keySet) {

            //过滤分页参数
            if ("currentPage".equals(key)||"rows".equals(key)) {
                    continue;
            }
            //获取值
            String value = condition.get(key)[0];
            if (value != null &&!"".equals(value)) {
                //有值
                builder.append(" and "+key+" like ? ");
                params.add("%"+value+"%");//? 条件值
            }
        }

        return template.queryForObject(builder.toString(),Integer.class,params.toArray());
    }

    @Override
    public List<User> findByPage(int start, int rows, Map<String, String[]> condition) {
        String sql = "SELECT * FROM user where 1=1 ";
        StringBuilder builder = new StringBuilder(sql);
        //遍历Map
        Set<String> keySet = condition.keySet();
        //定义一个参数的集合
        List<Object> params = new ArrayList<>();
        for (String key:keySet) {

            //过滤分页参数
            if ("currentPage".equals(key)||"rows".equals(key)) {
                continue;
            }
            //获取值
            String value = condition.get(key)[0];
            if (value != null &&!"".equals(value)) {
                //有值
                builder.append(" and "+key+" like ? ");
                params.add("%"+value+"%");//? 条件值
            }
        }
        builder.append(" limit ?,?");
        //添加分页查询的参数值
        params.add(start);
        params.add(rows);
        return template.query(builder.toString(),new BeanPropertyRowMapper<User>(User.class),params.toArray());
    }


}
