package com.wiki.service;

import com.wiki.model.User;
import com.wiki.repository.CommentRepository;
import com.wiki.repository.UserRepository;
import com.wiki.repository.WikiRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class UserService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final WikiRepository wikiRepository;

    public List<User> findAllUser() {
        return userRepository.findAll();
    }

    public User saveUser(User user) {
        userRepository.save(user);
        return user;
    }

    public User findUserByAppMail(String appMail) {
        for (User element : userRepository.findAll()) {
            if (element.getAppMail().equals(appMail)) {
                return element;
            }
        }
        return null;

    }

    public User updateUser(User user) {
       userRepository.findUserByAppMail(user.getAppMail()).setPassword(user.getPassword());
       userRepository.findUserByAppMail(user.getAppMail()).setName(user.getName());
       return user;
    }

    public void deleteUser(String appMail) {
        var user = findUserByAppMail(appMail);
        if (user != null) {
            userRepository.delete(user);
        }
    }
}
