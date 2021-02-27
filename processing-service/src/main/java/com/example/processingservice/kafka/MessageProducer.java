package com.example.processingservice.kafka;

import com.example.processingservice.dto.ProcessingResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageProducer {

    @Autowired
    private KafkaTemplate<String, ProcessingResponseDTO> wordsKafkaTemplate;

    @Value(value = "${words.topic.name}")
    private String wordsTopicName;


    public void sendWordsMessage(ProcessingResponseDTO wordsProcessingResponseDTO) {
        System.out.println("Sending message: " + wordsProcessingResponseDTO);
        wordsKafkaTemplate.send(wordsTopicName, wordsProcessingResponseDTO);
    }

}
