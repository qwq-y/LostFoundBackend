package com.example.service;

import com.example.model.Feedback;
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

  public ResponseEntity<String> createFeedback(Long reporterId, Long time, String body) {
    try {
      Feedback feedback = new Feedback();
      feedback.setReporterId(reporterId);
      feedback.setTime(time);
      feedback.setBody(body);
      feedbackRepository.save(feedback);
      return ResponseEntity.ok("successfully inserted feedback");
    } catch (Exception e) {
      return ResponseEntity.badRequest().body("insert feedback exception");
    }
  }

}
