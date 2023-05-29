package com.example.service;

import com.example.model.Post;
import com.example.model.User;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTest {

  @Autowired
  private UserService userService;

  @Test
  void testGetUsers() {
    List<User> users = userService.getUsers();
    for (User u : users) {
      System.out.println(u);
    }
  }

}
