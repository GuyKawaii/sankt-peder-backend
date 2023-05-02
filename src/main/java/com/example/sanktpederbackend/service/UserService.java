package com.example.sanktpederbackend.service;

import com.example.sanktpederbackend.model.Menu;
import com.example.sanktpederbackend.model.user.User;
import com.example.sanktpederbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> getUsers() {
        return userRepository.findAll();
    }
    public Optional<User> getUsersById(Integer id) {
        return userRepository.findById(id);
    }
    public ResponseEntity<User> putUser(User user, Integer userId) {
        if (doesUserExist(userId)) {
            user.setId(userId);
            User updatedUser = userRepository.save(user);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<User> deleteUser(Integer userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (doesUserExist(userId)) {
            User deleteUser = optionalUser.get();
            userRepository.deleteById(userId);
            return new ResponseEntity<>(deleteUser, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<User> putUsers(List<User> users) {
        for (User user : users) {
            if (doesUserExist(user)) userRepository.save(user);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<User> postUser(User user) {
        if (doesUserExist(user)) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        User postedUser = userRepository.save(user);
        return new ResponseEntity<>(postedUser, HttpStatus.OK);
    }

    private boolean doesUserExist(User user) {
        Integer userId = user.getId();
        return doesUserExist(userId);
    }

    private boolean doesUserExist(Integer id) {
        boolean isIdSet = id != null;
        return isIdSet && userRepository.existsById(id);
    }
}
