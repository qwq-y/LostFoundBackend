package com.example.controller;

import com.example.model.Post;
import com.example.service.PostService;
import java.io.File;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

  @PostMapping("/pictures/uploadPost")
  public ResponseEntity<String> uploadPost(
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

  @PostMapping("/claim")
  public ResponseEntity<?> claimPost(@RequestParam("id") Long id, @RequestParam("claimantId") Long claimantId) {
    // 处理通过两个参数更新数据库的逻辑
    return postService.updateClaimPostsById(id, claimantId);
  }

  @PostMapping("/pictures/uploadPicture")
  public ResponseEntity<String> handleFileUpload(@RequestParam("image") MultipartFile file) {
    if (!file.isEmpty()) {
      try {
        // 获取上传文件的原始文件名
        String originalFilename = file.getOriginalFilename();

        // 获取文件扩展名
        String fileExtension = StringUtils.getFilenameExtension(originalFilename);

        // 生成唯一的文件名
        String uniqueFileName = UUID.randomUUID().toString() + "." + fileExtension;

        // 拼接保存路径
        String savePath = "C:\\Users\\86188\\Desktop\\CS304\\Pictures\\" + uniqueFileName;

        // 将文件保存到指定路径
        File saveFile = new File(savePath);
        FileCopyUtils.copy(file.getBytes(), saveFile);

        return ResponseEntity.ok("File uploaded successfully");
      } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("Failed to upload file");
      }
    } else {
      return ResponseEntity.badRequest().body("No file uploaded");
    }
  }


  @PutMapping("/test/{itemName}")
  public void updatePostDescriptionByItemName(
      @PathVariable String itemName,
      @RequestParam String itemDescription
  ) {
    postService.updatePostDescriptionByItemName(itemName, itemDescription);
  }


}

