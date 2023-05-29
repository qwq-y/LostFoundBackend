package com.example.service;

import com.example.model.Post;
import com.example.repository.PostRepository;
import com.example.utils.PictureLoader;
import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

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
  public List<Post> getPostsOrderByPublishTimeAsc() {
    return postRepository.findPostsOrderByPublishTimeAsc();
  }

  public Post createPost(Post post) {
    return postRepository.save(post);
  }

  public ResponseEntity<byte[]> getPicture(String picture) {
    String imagePath = "C:\\Users\\86188\\Desktop\\CS304\\Pictures\\" + picture;
    String extension = StringUtils.getFilenameExtension(imagePath);

    // 根据 extension 加载对应的图片数据
    byte[] imageData = PictureLoader.loadByte(imagePath, extension);

    // 设置响应头，指定图片的 MIME 类型
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(PictureLoader.getImageExtension(extension));

    // 返回图片数据和响应头
    return new ResponseEntity<>(imageData, headers, HttpStatus.OK);
  }

  public ResponseEntity<String> uploadPost(MultipartFile file, String itemName, String itemType,
      String itemDescription, String roughPlace, String detailedPlace, Long publisherId) {
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

        ResponseEntity<String> responseEntity =  ResponseEntity.ok("File uploaded successfully");

        if (responseEntity.equals(ResponseEntity.ok("File uploaded successfully"))) {
          Post post = new Post();
          post.setItemName(itemName);
          post.setItemType(itemType);
          post.setItemDescription(itemDescription);
          post.setPicture(uniqueFileName);
          post.setPublishTime(System.currentTimeMillis());
          post.setClaimTime(null);
          post.setRoughPlace(roughPlace);
          post.setDetailedPlace(detailedPlace);
          post.setPublisherId(publisherId);
          post.setClaimantId(null);
          post.setIsClaimed(false);
          post.setIsHidden(false);

          createPost(post);
        }

        return responseEntity;

      } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("Failed to upload file");
      }
    } else {
      return ResponseEntity.badRequest().body("No file uploaded");
    }
  }

  // 按发布时间顺序，降序
  public List<Post> getPostsOrderByPublishTimeDesc() {
    return postRepository.findPostsOrderByPublishTimeDesc();
  }

  // 按物品名称匹配程序搜索排序
  public List<Post> getPostsLikeItemName(String itemName) {
    return postRepository.findPostsLikeItemName(itemName);
  }

  // 按物品描述匹配程度搜索排序
  public List<Post> getPostsLikeDescription(String keyword) {
    return postRepository.findPostsLikeDescription(keyword);
  }

  // 按照 给定地点 选择符合条件的物品
  public List<Post> getPostsByExactPlace(String place) {
    return postRepository.findPostsByExactPlace(place);
  }

  // 按照 给定物品类型 选择符合条件的物品
  public List<Post> getPostsByExactType(String type) {
    return postRepository.findPostsByExactType(type);
  }
}
