package com.example.processingservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProcessingResponseDTO {
    private String most_frequent_word;
    private String avg_paragraph_size;
    private String avg_paragraph_processing_time;
    private String total_processing_time;
}
