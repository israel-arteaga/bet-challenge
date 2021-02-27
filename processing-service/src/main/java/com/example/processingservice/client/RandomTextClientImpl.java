package com.example.processingservice.client;

import com.example.processingservice.dto.ProcessingResponseDTO;
import com.example.processingservice.dto.RandomTextResponseDTO;
import com.example.processingservice.kafka.MessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RandomTextClientImpl implements RandomTextClient{
    @Autowired
    MessageProducer messageProducer;

    final static String RANDOM_TEXT_API = "http://www.randomtext.me/api/giberish/";
    final static long NANO = 1000000000;

    @Override
    public ProcessingResponseDTO processRandomText(Integer p_start, Integer p_end, Integer w_count_min, Integer w_count_max) {

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent","curl/7.54.0");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        List<String> paragraphs = new ArrayList<>();
        List<Integer> paragraphSizes = new ArrayList<>();
        List<Integer> processingTime = new ArrayList<>();

        for (int i = p_start; i <= p_end; i++) {

            LocalDateTime startTime = LocalDateTime.now();

            ResponseEntity<RandomTextResponseDTO> response = restTemplate.
                    exchange(RANDOM_TEXT_API+ "/p-" + i + "/" + w_count_min + "-" + w_count_max,
                    HttpMethod.GET,entity,RandomTextResponseDTO.class);

            paragraphs.add(response.getBody().getText_out());
            paragraphSizes.add(Integer.parseInt(response.getBody().getAmount()));

            LocalDateTime finishTime = LocalDateTime.now();

            Duration duration = Duration.between(startTime, finishTime);
            processingTime.add(duration.getNano()); //in nano secs

        }

        ProcessingResponseDTO processingResponseDTO = new ProcessingResponseDTO();
        processingResponseDTO.setMost_frequent_word(getMostFrequentWord(paragraphs));
        processingResponseDTO.setAvg_paragraph_size(getAverageParagraphSize(paragraphSizes).toString());
        processingResponseDTO.setAvg_paragraph_processing_time(String.valueOf((double)getProcessingTimeStats(processingTime).getAverage()/NANO)); //secs
        processingResponseDTO.setTotal_processing_time(String.valueOf((long)getProcessingTimeStats(processingTime).getSum()/NANO)); //secs

        messageProducer.sendWordsMessage(processingResponseDTO);

        return processingResponseDTO;
    }

    private String getMostFrequentWord(List<String> paragraphs){
        List<String> replaced = paragraphs.stream()
                .map(p -> p.replaceAll("<p>", "")
                        .replaceAll(".</p>\r",""))
                .collect(Collectors.toList());

        List<String> allWords = new ArrayList<>();
        for (String s: replaced) {
            allWords.addAll(Arrays.asList(s.split(" ")));
        }

        String mostFrequentWord
                = allWords.stream()
                .collect(Collectors.groupingBy(w -> w, Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .get()
                .getKey();

        return mostFrequentWord;
    }

    private Double getAverageParagraphSize(List<Integer> paragraphSizes ){
        IntSummaryStatistics stats = paragraphSizes.stream()
                .mapToInt((i) -> i)
                .summaryStatistics();
        return stats.getAverage();
    }

    private IntSummaryStatistics getProcessingTimeStats(List<Integer> timeProcessing){
        IntSummaryStatistics stats = timeProcessing.stream()
                .mapToInt((i) -> i)
                .summaryStatistics();
        return stats;
    }


}
