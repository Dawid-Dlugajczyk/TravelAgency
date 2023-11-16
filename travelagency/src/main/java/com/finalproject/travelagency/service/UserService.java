package com.finalproject.travelagency.service;



import com.finalproject.travelagency.exception.UserNotFoundException;
import com.finalproject.travelagency.model.User;
import com.finalproject.travelagency.repository.UserRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser(User user){
        return userRepository.save(user);
    }


    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User updateUser(User user){
        return userRepository.save(user);
    }

    public User findUserById(Long id){
        return userRepository.findUserById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id=" + id + "was not found."));
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
}
