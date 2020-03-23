package com.bytetube.service.impl;

import com.bytetube.dao.UserDao;
import com.bytetube.dao.impl.UserDaoImpl;
import com.bytetube.domain.PageBean;
import com.bytetube.domain.User;
import com.bytetube.service.UserService;

import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {
    private UserDao dao = new UserDaoImpl();
    @Override
    public List<User> findAll() {
        List<User> all = dao.findAll();
        return all;
    }

    @Override
    public User login(String username, String password) {
        User user = dao.findUserByUsernameAndPassword(username, password);
        return user;
    }

    @Override
    public void addUser(User user) {
        dao.add(user);
    }

    @Override
    public void deleteUser(int id) {
        dao.delete(id);
    }

    @Override
    public User findUserById(int id) {
        return dao.findUserById(id);
    }

    @Override
    public void updateUser(User user) {
        dao.update(user);
    }

    @Override
    public PageBean<User> findUserByPage(String start, String _rows, Map<String, String[]> condition) {
       int currentPage = Integer.parseInt(start);
       int rows =  Integer.parseInt(_rows);
        if (currentPage <=0) {
            currentPage = 1;
        }

        PageBean<User> pb = new PageBean<>();
        //设置PageBean的参数
        pb.setCurrentPage(currentPage);
        pb.setRows(rows);
        int totalCount = dao.findTotalCount(condition);
        pb.setTotalCount(totalCount);
        int begin = (currentPage-1)*rows;
        List<User> list = dao.findByPage(begin, rows, condition);
        pb.setList(list);

        int totalPage = totalCount%rows==0?totalCount/rows:(totalCount/rows)+1;
        pb.setTotalPage(totalPage);

        return pb;
    }

    @Override
    public void delSelectedUser(String[] ids) {
        if(ids!=null && ids.length>0){
            for (String id:ids) {
                dao.delete(Integer.parseInt(id));
            }

        }
    }
}
