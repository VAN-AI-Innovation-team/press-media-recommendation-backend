package com.example.demo.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.ContactHistory;

public interface ContactHistoryRepository extends JpaRepository<ContactHistory, Long> {
    List<ContactHistory> findByJournalistId(Long journalistId);
}