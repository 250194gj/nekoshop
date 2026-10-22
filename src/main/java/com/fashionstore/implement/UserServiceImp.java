package com.fashionstore.implement;

import com.fashionstore.model.User;
import com.fashionstore.repository.UserRepository;
import com.fashionstore.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;

    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User save(User user) {
         return  userRepository.save(user);
    }


}
