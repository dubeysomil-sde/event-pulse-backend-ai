package com.eventpulse.service;

import com.eventpulse.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    User getUser(Integer userId);

    List<User> getAllUsers();

    User createUser(User user);

    User updateUser(User user, Integer userId);

    void deleteUser(Integer userId);
}
