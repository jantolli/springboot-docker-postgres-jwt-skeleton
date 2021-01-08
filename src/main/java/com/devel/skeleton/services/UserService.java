package com.devel.skeleton.services;

import com.devel.skeleton.domain.User;

public interface UserService {

    User login(String email, String password);

    String addUser(String firstName, String lastName, String email, String password);

    User getUser(String email);
}
