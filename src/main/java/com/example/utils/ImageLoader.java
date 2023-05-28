package com.example.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;

public class ImageLoader {

  public static byte[] loadByte(String imagePath, String extension) {
    byte[] imageData = null;
    try {
      if (extension != null && getImageExtension(extension) != null) {
        // 读取图片文件数据
        File imageFile = new File(imagePath);
        FileInputStream fileInputStream = new FileInputStream(imageFile);
        imageData = StreamUtils.copyToByteArray(fileInputStream);
        fileInputStream.close();
      } else {
        System.out.println("不支持的图片格式");
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
    return imageData;
  }

  public static MediaType getImageExtension(String extension) {
    if (extension.equalsIgnoreCase("jpg")) {
      return MediaType.IMAGE_JPEG;
    } else if (extension.equalsIgnoreCase("jpeg")) {
      return MediaType.IMAGE_JPEG;
    } else if (extension.equalsIgnoreCase("png")) {
      return MediaType.IMAGE_PNG;
    } else if (extension.equalsIgnoreCase("gif")) {
      return MediaType.IMAGE_GIF;
    } else {
      return null;
    }
  }

}

