package com.bytetube.dao;

import com.bytetube.domain.User;

import java.util.List;
import java.util.Map;

/**
 *接口中定义方法就是我们要实现的功能
 */
public interface UserDao {
    public List<User> findAll();

    public User findUserByUsernameAndPassword(String username, String password);

    public void add(User user);

    public void delete(int id);

    public User findUserById(int id);

    public void update(User user);

    public int findTotalCount(Map<String,String[]> condition);

    public List<User> findByPage(int start,int rows,Map<String,String[]> condition);
}
