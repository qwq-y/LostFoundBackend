package com.example.respository;

import com.example.model.User;
import com.example.repository.UserRepository;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserRepositoryTest {

  @Autowired
  private UserRepository userRepository;

  @Test
  void testFindAll() {
    List<User> users = userRepository.findAll();
    for (User u : users) {
      System.out.println(u);
    }
  }
}
