package com.bytetube.service;

import com.bytetube.domain.PageBean;
import com.bytetube.domain.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    public List<User> findAll();

    public User login(String username, String password);

    public void addUser(User user);

    public void deleteUser(int id);

    public User findUserById(int id);

    public void updateUser(User user);


    public PageBean<User> findUserByPage(String start, String rows, Map<String, String[]> condition);

    public void delSelectedUser(String[] ids);
}
