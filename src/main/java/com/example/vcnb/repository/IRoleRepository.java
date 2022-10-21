package com.example.vcnb.repository;

import com.example.vcnb.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface IRoleRepository extends JpaRepository<Role,Long> {
    Role findByName(String name);
}
