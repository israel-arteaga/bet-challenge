package com.example.repositoryservice.repository;

import com.example.repositoryservice.entities.History;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoryRepository extends JpaRepository<History, Integer> {

    List<History> findFirst10ByOrderByTimestampDesc();
}
