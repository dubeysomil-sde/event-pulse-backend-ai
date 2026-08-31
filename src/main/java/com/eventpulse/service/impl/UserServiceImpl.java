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
        User user = null;
        try{

            user = userRepository.findById(userId).orElseThrow(()->new RuntimeException("User not found"));
        }catch(RuntimeException e){
            e.printStackTrace();
        }
        return user;
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
            if(e.getClass().getName().equals("PSQLException")){
                throw new RuntimeException("User already exists");
            }
            throw new RuntimeException("Error while creating user");
        }
    }

    public User updateUser(User user, Integer userId){
        try{
            user.setId(userId);
            userRepository.save(user);
            return user;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public void deleteUser(Integer userId){
        try{
            userRepository.deleteById(userId);
        }catch (Exception e){
            throw  new RuntimeException("User not found");
        }
    }
}
