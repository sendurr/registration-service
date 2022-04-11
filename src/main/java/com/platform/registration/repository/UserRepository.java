package com.platform.registration.repository;

import com.platform.registration.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findByUsernameOrEmailid(String username, String emailid);
    User findByUsernameAndPassword(String username, String password);
    List<User> findByEmailid(String emailId);
}