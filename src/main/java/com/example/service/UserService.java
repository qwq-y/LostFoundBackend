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

  public ResponseEntity<String> updateNameByStudentId(Long studentId, String name) {
    User user = userRepository.findByStudentId(studentId);
    if (user != null) {
      user.setName(name);
      userRepository.save(user);
      return ResponseEntity.ok("successfully updated user name");
    } else {
      return ResponseEntity.badRequest().body("user not found");
    }
  }

  public ResponseEntity<String> createUser(Long studentId, String name, String password, String type) {
    try {
      User user = new User();
      user.setStudentId(studentId);
      user.setName(name);
      user.setPassword(password);
      user.setType(type);
      userRepository.save(user);
      return ResponseEntity.ok("successfully inserted user");
    } catch (Exception e) {
      return ResponseEntity.badRequest().body("insert user exception");
    }
  }

}
