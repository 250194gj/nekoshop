package com.fashionstore.service;

import com.fashionstore.model.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findByEmail(String email);

    User save(User user);
}
