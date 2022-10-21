package com.example.vcnb.repository;

import com.example.vcnb.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface IUserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

}
