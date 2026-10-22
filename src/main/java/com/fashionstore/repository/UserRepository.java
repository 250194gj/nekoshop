package com.fashionstore.repository;

import com.fashionstore.model.Product;
import com.fashionstore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByEmail(String Email);
    public User save(User user);
}
