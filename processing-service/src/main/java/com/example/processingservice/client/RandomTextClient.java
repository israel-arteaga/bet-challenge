package com.example.processingservice.client;

import com.example.processingservice.dto.ProcessingResponseDTO;


public interface RandomTextClient {

    ProcessingResponseDTO processRandomText(Integer p_start, Integer p_end, Integer w_count_min, Integer w_count_max);

}
