package com.example.repositoryservice.controller;


import com.example.processingservice.dto.ProcessingResponseDTO;
import com.example.repositoryservice.entities.History;
import com.example.repositoryservice.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RepositoryController {

    @Autowired
    HistoryRepository historyRepository;

    @GetMapping("/betvictor/history")
    public List<History> getHistory(){

        return historyRepository.findFirst10ByOrderByTimestampDesc();
    }
}
