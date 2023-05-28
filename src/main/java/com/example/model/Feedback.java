package com.example.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table
public class Feedback {
    @Id
    @GeneratedValue
    private Long id;
    private Long reporterId;
    private Long time;
    private String body;
}
