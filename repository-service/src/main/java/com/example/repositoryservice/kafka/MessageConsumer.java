package com.example.repositoryservice.kafka;

import com.example.processingservice.dto.ProcessingResponseDTO;
import com.example.repositoryservice.entities.History;
import com.example.repositoryservice.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Component
public class MessageConsumer {
    @Autowired
    HistoryRepository historyRepository;

    @KafkaListener(topics = "words.processed", containerFactory = "wordsKafkaListenerContainerFactory")
    public void consumeMessage(ProcessingResponseDTO wordsProcessingResponseDTO) {
        System.out.println("Received message: " + wordsProcessingResponseDTO);

        saveMessage(wordsProcessingResponseDTO);
    }

    private void saveMessage(ProcessingResponseDTO wordsProcessingResponseDTO){
        History history = new History();
        history.setMost_frequent_word(wordsProcessingResponseDTO.getMost_frequent_word());
        history.setAvg_paragraph_size(wordsProcessingResponseDTO.getAvg_paragraph_size());
        history.setAvg_paragraph_processing_time(wordsProcessingResponseDTO.getAvg_paragraph_processing_time());
        history.setTotal_processing_time(wordsProcessingResponseDTO.getTotal_processing_time());
        history.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));
        historyRepository.save(history);
    }

}
