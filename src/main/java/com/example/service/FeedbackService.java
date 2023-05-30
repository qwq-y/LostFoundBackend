package com.example.service;

import com.example.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class FeedbackService {

  private final FeedbackRepository feedbackRepository;

  @Autowired
  public FeedbackService(FeedbackRepository feedbackRepository) {
    this.feedbackRepository = feedbackRepository;
  }

//  public ResponseEntity<String> createFeedback() {
//
//  }

}
