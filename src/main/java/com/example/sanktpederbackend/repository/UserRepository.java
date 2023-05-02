package com.example.sanktpederbackend.repository;

import com.example.sanktpederbackend.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
