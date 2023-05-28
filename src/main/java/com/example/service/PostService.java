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

  // 按发布时间顺序，升序
  public List<Post> getPostsOrderByPublishTime() {
    return postRepository.findPostsOrderByPublishTime();
  }

  // 按发布时间顺序，降序
  public List<Post> getPostsOrderByPublishTimeDesc() {
    return postRepository.findPostsOrderByPublishTimeDesc();
  }

  // 按物品名称匹配程序搜索排序
  public List<Post> getFindPostsOrderByItemName(String itemName) {
    return postRepository.findPostsOrderByItemName(itemName);
  }

  // 按物品描述匹配程度搜索排序
  public List<Post> getFindPostsOrderByDescription(String keyword) {
    return postRepository.findPostsOrderByDescription(keyword);
  }

  // 按照 给定地点 选择符合条件的物品
  public List<Post> getFindPostsByExactPlace(String place) {
    return postRepository.findPostsByExactPlace(place);
  }

  // 按照 给定物品类型 选择符合条件的物品
  public List<Post> getFindPostsByExactType(String type) {
    return postRepository.findPostsByExactType(type);
  }
}
