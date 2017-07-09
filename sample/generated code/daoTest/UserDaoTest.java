package com.example.demo.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;

import com.example.demo.model.User;
import com.example.demo.dao.UserDao;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest{
    @Autowired
    private UserDao userDao;

    @Test
    @Rollback(false)
    public void testCreate() {
        User user = new User();
        user.setId(1L);
        user.setName("name");
        user.setAge(1);
        user.setMobile("mobile");
        user.setEmail("email");
        user.setCreatedAt(new Date());
        user.setUpdatedAt(new Date());
        userDao.create(user);
    }


    @Test
    @Rollback(false)
    public void testCreates() {
        User user2 = new User();
        user2.setId(1L);
        user2.setName("name");
        user2.setAge(1);
        user2.setMobile("mobile");
        user2.setEmail("email");
        user2.setCreatedAt(new Date());
        user2.setUpdatedAt(new Date());

        User user3 = new User();
        user3.setId(1L);
        user3.setName("name");
        user3.setAge(1);
        user3.setMobile("mobile");
        user3.setEmail("email");
        user3.setCreatedAt(new Date());
        user3.setUpdatedAt(new Date());

        List<User> users = new ArrayList<>();
        users.add(user2);
        users.add(user3);
        userDao.creates(users);
    }

    @Test
    @Rollback(false)
    public void testLoad() {
        System.out.println(userDao.load(1L));
    }


    @Test
    @Rollback(false)
    public void testLoads() {
        List<Long> ids = new ArrayList<>();
        ids.add(2L);
        ids.add(3L);
        userDao.loads(ids).forEach(e->System.out.println(e));
    }

    @Test
    @Rollback(false)
    public void testDelete() {
        userDao.delete(4L);
    }

    @Test
    @Rollback(false)
    public void testDeletes() {
        List<Long> ids = new ArrayList<>();
        ids.add(2L);
        ids.add(3L);
        userDao.deletes(ids);
    }

    @Test
    @Rollback(false)
    public void testUpdate() {
        User user = new User();
        user.setId(1L);
        user.setName("name");
        user.setAge(1);
        user.setMobile("mobile");
        user.setEmail("email");
        user.setCreatedAt(new Date());
        user.setUpdatedAt(new Date());
        userDao.update(user);
    }

    @Test
    @Rollback(false)
    public void testPaging() {
        Map<String,Object> criteria = new HashMap<>();
        criteria.put("???",3L);
        Paging<User> paging = userDao.paging(0, 5, criteria);
        paging.getData().forEach(e->System.out.println(e));
    }

    @Test
    @Rollback(false)
    public void testCount() {
        Map<String,Object> criteria = new HashMap<>();
        criteria.put("???",233L);
        Long count = userDao.count(criteria);
        System.out.println(count);
    }

}
