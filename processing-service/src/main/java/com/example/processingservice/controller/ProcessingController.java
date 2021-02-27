package com.example.processingservice.controller;

import com.example.processingservice.client.RandomTextClient;
import com.example.processingservice.dto.ProcessingResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProcessingController {
    @Autowired
    RandomTextClient randomTextClient;

    @GetMapping("/betvictor/text")
    public ProcessingResponseDTO getText(@RequestParam(value = "p_start") Integer p_start,
                                         @RequestParam(value = "p_end") Integer p_end,
                                         @RequestParam(value = "w_count_min") Integer w_count_min,
                                         @RequestParam(value = "w_count_max") Integer w_count_max){


        return randomTextClient.processRandomText(p_start,p_end,w_count_min,w_count_max);
    }
}
