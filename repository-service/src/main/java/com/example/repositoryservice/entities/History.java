package com.example.repositoryservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class History {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    private String most_frequent_word;
    private String avg_paragraph_size;
    private String avg_paragraph_processing_time;
    private String total_processing_time;

    private Timestamp timestamp;

}
