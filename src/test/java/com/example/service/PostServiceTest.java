package com.example.service;

import com.example.model.Post;
import java.util.List;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PostServiceTest {

  @Autowired
  private PostService postService;

  @Test
  void testGetPosts() {
    List<Post> posts = postService.getPosts();
    for (Post post : posts) {
      System.out.println(post);
    }
  }

  @Test
  void testCreatePost() {
    Post post = postService.createPost("a", "食物", "b", "其他", "c", 11111111L);
    System.out.println(post);
  }
}
