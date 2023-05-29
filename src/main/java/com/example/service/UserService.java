package com.example.service;

import com.example.model.Post;
import com.example.model.User;
import com.example.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final UserRepository userRepository;

  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public List<User> getUsers() {
    return userRepository.findAll();
  }

  public User createUser(User user) {
    return userRepository.save(user);
  }

  public ResponseEntity<String> updateNameByStudentId(Long studentId, String name) {
    User user = userRepository.findByStudentId(studentId);
    if (user != null) {
      user.setName(name);
      userRepository.save(user);
      return ResponseEntity.ok("successful");
    } else {
      return ResponseEntity.badRequest().body("user not found");
    }
  }


}
