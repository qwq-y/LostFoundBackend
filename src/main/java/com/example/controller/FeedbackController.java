package com.example.controller;

import com.example.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feedbacks")
public class FeedbackController {

  private final FeedbackService feedbackService;

  @Autowired
  public FeedbackController(FeedbackService feedbackService) {
    this.feedbackService = feedbackService;
  }

  @PostMapping
  public ResponseEntity<String> createUser(
      @RequestParam Long reporterId,
      @RequestParam Long time,
      @RequestParam String body
  ) {
    return feedbackService.createFeedback(reporterId, time, body);
  }

}
