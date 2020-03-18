package com.bytetube.test;

import com.bytetube.dao.UserDao;
import com.bytetube.dao.impl.UserDaoImpl;
import com.bytetube.domain.User;
import org.junit.Test;

import java.util.List;

public class UserDaoTest {

    @Test
    public void testFindAll(){
        UserDao dao = new UserDaoImpl();
        List<User> users = dao.findAll();
        for (User u: users) {
            System.out.println(u);
        }
    }

    @Test
    public void testFindUserByUsernameAndPassword(){
        User user = new User();
        user.setUsername("dal");
        user.setPassword("111111");
        UserDao dao = new UserDaoImpl();
        User res = dao.findUserByUsernameAndPassword(user.getUsername(), user.getPassword());
        System.out.println(res);
    }


    @Test
    public void testAdd(){
        User user = new User();
        user.setName("tony");
        user.setGender("M");
        user.setAge(29);
        user.setAddress("trt");
        user.setQq("123456");
        user.setEmail("tony@gmail.com");
        UserDao dao = new UserDaoImpl();
        dao.add(user);
    }

    @Test
    public void testDel(){
        int id = 12;
        UserDao dao = new UserDaoImpl();
        dao.delete(id);
    }

    @Test
    public void testFindUserById(){
        int id = 1;
        UserDao dao = new UserDaoImpl();
        User user = dao.findUserById(id);
        System.out.println(user);
    }

    @Test
    public void testUpdate(){
        User user = new User();
        user.setName("tony");
        user.setGender("M");
        user.setAge(29);
        user.setAddress("trt");
        user.setQq("123456");
        user.setEmail("tony@gmail.com");
        user.setId(10);
        UserDao dao = new UserDaoImpl();
        dao.update(user);
    }


}
