package com.example.demo.dao;

import org.springframework.stereotype.Repository;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

import com.example.demo.model.User;

@Repository
public interface UserDao{

    Integer create(User user);

    Integer creates(List<User> users);

    Integer delete(Long id);

    Integer deletes(List<Long> ids);

    Integer update(User user);

    User load(Long id);

    List<User> loads(List<Long> ids);

    List<User> paging(Map<String, Object> criteria);

    Long count(Map<String, Object> criteria);

}
