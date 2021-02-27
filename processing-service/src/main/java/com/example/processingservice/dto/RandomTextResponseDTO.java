package com.example.processingservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RandomTextResponseDTO {
    private String type;
    private String amount;
    private String number;
    private String number_max;
    private String format;
    private String time;
    private String text_out;
}
