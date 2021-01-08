package com.devel.skeleton.services;

import com.devel.skeleton.domain.User;
import com.devel.skeleton.respositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepo;

    @Override
    public User login(String email, String password) {
        return userRepo.login(email, password);
    }

    @Override
    public String addUser(String firstName, String lastName, String email, String password) {
        return userRepo.create(firstName, lastName, email, password);
    }

    @Override
    public User getUser(String email) {
        return userRepo.get(email);
    }
}
