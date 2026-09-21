package com.example.demo.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.Journalist;
import com.example.demo.domain.JournalistStatus;

public interface JournalistRepository extends JpaRepository<Journalist, Long> {

    List<Journalist> findByStatus(JournalistStatus status);

    List<Journalist> findByPressId(Long pressId);

    boolean existsByEmail(String email);
}