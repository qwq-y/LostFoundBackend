package com.example.controller;

import com.example.model.Post;
import com.example.service.PostService;
import com.example.utils.ImageLoader;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

  @GetMapping("/images/{imageName}")
  public ResponseEntity<byte[]> getImage(@PathVariable String imageName) {
    String imagePath = "C:\\Users\\86188\\Desktop\\CS304\\Pictures\\" + imageName;
    String extension = StringUtils.getFilenameExtension(imagePath);

    // 根据 extension 加载对应的图片数据
    byte[] imageData = ImageLoader.loadByte(imagePath, extension);

    // 设置响应头，指定图片的 MIME 类型
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(ImageLoader.getImageExtension(extension));

    // 返回图片数据和响应头
    return new ResponseEntity<>(imageData, headers, HttpStatus.OK);
  }
}
