package com.finalproject.travelagency.service;



import com.finalproject.travelagency.exception.UserNotFoundException;
import com.finalproject.travelagency.model.User;
import com.finalproject.travelagency.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser(User user){
        return userRepository.save(user);
    }


    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User updateUser(User user, Long id){
        User newUser = userRepository.findUserById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id=" + id + "was not found."));
        newUser.setId(newUser.getId());
        newUser.setCity(user.getCity());
        newUser.setAddress(user.getAddress());
        newUser.setEmail(newUser.getEmail());
        newUser.setFirstname(user.getFirstname());
        newUser.setLastname(user.getLastname());
        newUser.setDateOfBirth(user.getDateOfBirth());
        newUser.setPesel(user.getPesel());
        newUser.setPhoneNumber(user.getPhoneNumber());
        newUser.setPassword(newUser.getPassword());
        return userRepository.save(newUser);
    }

    public User findUserById(Long id){
        return userRepository.findUserById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id=" + id + "was not found."));
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
}
