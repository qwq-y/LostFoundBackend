package com.example.controller;

import com.example.model.Post;
import com.example.service.PostService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/posts")
public class PostController {

  private final PostService postService;

  @Autowired
  public PostController(PostService postService) {
    this.postService = postService;
  }

  @GetMapping
  public List<Post> getPosts() {
    return postService.getPosts();
  }

  @GetMapping("/orderByPublishTime")
  public List<Post> getPostsOrderByPublishTime() {
    return postService.getPostsOrderByPublishTimeAsc();
  }

  @GetMapping("/orderByPublishTimeDesc")
  public List<Post> getPostsOrderByPublishTimeDesc() {
    return postService.getPostsOrderByPublishTimeDesc();
  }

  @GetMapping("/likeItemName/{itemName}")
  public List<Post> getFindPostsLikeItemName(@PathVariable String itemName) {
    return postService.getPostsLikeItemName(itemName);
  }

  @GetMapping("/likeDescription/{keyword}")
  public List<Post> getFindPostsLikeDescription(@PathVariable String keyword) {
    return postService.getPostsLikeDescription(keyword);
  }

  @GetMapping("/selectByPlace/{place}")
  public List<Post> getFindPostsByExactPlace(@PathVariable String place) {
    return postService.getPostsByExactPlace(place);
  }

  @GetMapping("/selectByType/{type}")
  public List<Post> getFindPostsByExactType(@PathVariable String type) {
    return postService.getPostsByExactType(type);
  }

  @GetMapping("/pictures/{picture}")
  public ResponseEntity<byte[]> getPicture(@PathVariable String picture) {
    return postService.getPicture(picture);
  }

  @PostMapping("/pictures/upload")
  public ResponseEntity<String> uploadPicture(
      @RequestParam("picture") MultipartFile file,
      @RequestParam("itemName") String itemName,
      @RequestParam("itemType") String itemType,
      @RequestParam("itemDescription") String itemDescription,
      @RequestParam("roughPlace") String roughPlace,
      @RequestParam("detailedPlace") String detailedPlace,
      @RequestParam("publisherId") Long publisherId
  ) {
    return postService.uploadPost(file, itemName, itemType, itemDescription, roughPlace,
        detailedPlace, publisherId);
  }

  @PostMapping("/claim/{id}/{claimantId}")
  public ResponseEntity<?> claimPost(@PathVariable("id") String id, @RequestParam("claimantId") String claimantId) {
    // 处理通过两个参数更新数据库的逻辑
    return postService.updateClaimPostsById(id, claimantId);
  }


}

