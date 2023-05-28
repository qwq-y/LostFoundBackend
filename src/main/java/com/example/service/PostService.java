package com.example.service;

import com.example.model.Post;
import com.example.repository.PostRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {

  private final PostRepository postRepository;

  @Autowired
  public PostService(PostRepository postRepository) {
    this.postRepository = postRepository;
  }

  public List<Post> getPosts() {
    return postRepository.findAll();
  }

  public List<Post> getPostsOrderByPublishTime() {
    return postRepository.findPostsOrderByPublishTime();
  }

}
