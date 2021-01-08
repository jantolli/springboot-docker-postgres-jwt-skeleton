package com.devel.skeleton.respositories;

import com.devel.skeleton.domain.User;
import com.devel.skeleton.exceptions.UnauthorizedException;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface UserRepository {

    public User login(String email, String password);

    public String create(String firstName, String lastName, String email, String password) throws UnauthorizedException;

    public User get(String email);

}
