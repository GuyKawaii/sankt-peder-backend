package com.example.sanktpederbackend.repository;

import com.example.sanktpederbackend.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Integer> {
}
