package com.eventpulse.service.impl;

import com.eventpulse.entity.User;
import com.eventpulse.repository.UserRepository;
import com.eventpulse.service.UserService;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser(Integer userId) {
        return userRepository.findById(userId).get();
    }

    public List<User> getAllUsers(){
        return userRepository.findAll().stream().distinct().toList();
    }

    public User createUser(User user){
        try{
            if(user.getName().isEmpty() || user.getEmail().isEmpty()){
                throw new Exception("Username or email address is empty");
            }else{
                userRepository.save(user);
                return user;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public User updateUser(User user, Integer userId){
        Optional<User> oldUser = userRepository.findById(userId);
        if(oldUser.isPresent()){
            oldUser.get().setName(user.getName());
            oldUser.get().setEmail(user.getEmail());
            oldUser.get().setCreatedAt(user.getCreatedAt());
        }
        userRepository.save(oldUser.get());
        return oldUser.get();
    }

    public void deleteUser(Integer userId){
        userRepository.deleteById(userId);
    }
}
