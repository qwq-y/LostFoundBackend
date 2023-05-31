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

//  // 上传帖子（包含图片） 测试未通过！！！
//  @PostMapping("/pictures/uploadPost")
//  public ResponseEntity<String> uploadPost(
//      @RequestParam("picture") MultipartFile file,
//      @RequestParam("itemName") String itemName,
//      @RequestParam("itemType") String itemType,
//      @RequestParam("itemDescription") String itemDescription,
//      @RequestParam("roughPlace") String roughPlace,
//      @RequestParam("detailedPlace") String detailedPlace,
//      @RequestParam("publisherId") Long publisherId
//  ) {
//    return postService.uploadPost(file, itemName, itemType, itemDescription, roughPlace,
//        detailedPlace, publisherId);
//  }

  @PostMapping("/uploadPostWithoutPicture")
  public Post uploadPost(
      @RequestParam("itemName") String itemName,
      @RequestParam("itemType") String itemType,
      @RequestParam("itemDescription") String itemDescription,
      @RequestParam("roughPlace") String roughPlace,
      @RequestParam("detailedPlace") String detailedPlace,
      @RequestParam("publisherId") Long publisherId
  ) {
    return postService.createPost(itemName, itemType, itemDescription, roughPlace,
        detailedPlace, publisherId);
  }


  /**
   * 上传图片（参数为微信生成的临时路径），前端对应代码如下：
   *   uploadImage: function () {
   *     // 选择图片并上传
   *     wx.chooseImage({
   *       count: 1, // 最多可以选择的图片张数
   *       success: function (res) {
   *         // 获取选择的图片临时路径
   *         var tempFilePath = res.tempFilePaths[0];
   *
   *         // 发送图片给后端
   *         wx.uploadFile({
   *           url: 'http://10.25.6.55:80/posts/pictures/uploadPicture',  // 替换为你的后端接口地址
   *           filePath: tempFilePath,
   *           name: 'image',  // 后端接收图片的字段名
   *           success: function (res) {
   *             // 图片上传成功后的处理
   *             console.log(res.data);
   *           },
   *           fail: function (res) {
   *             // 图片上传失败的处理
   *             console.log(res.errMsg);
   *           }
   *         });
   *       }
   *     });
   *   },
   */
  @PostMapping("/uploadPicture")
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

        System.out.println("============>" + file);

        return ResponseEntity.ok("File uploaded successfully");
      } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("Failed to upload file");
      }
    } else {
      return ResponseEntity.badRequest().body("No file uploaded");
    }
  }

  @PutMapping("/claim/{id}")
  public void claimPost(
      @PathVariable("id") Long id,
      @RequestParam("claimantId") Long claimantId,
      @RequestParam("claimantTime") Long claimantTime
  ) {
    // 处理通过两个参数更新数据库的逻辑
    postService.updateClaimPostsById(id, claimantId, claimantTime);
  }

  // 测试用
  @PutMapping("/test/{itemName}")
  public void updatePostDescriptionByItemName(
      @PathVariable String itemName,
      @RequestParam String itemDescription
  ) {
    postService.updatePostDescriptionByItemName(itemName, itemDescription);
  }


}

