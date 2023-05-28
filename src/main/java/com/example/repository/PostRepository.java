package com.example.repository;

import com.example.model.Post;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

  @Query(value = "select * from post order by post.publish_time", nativeQuery = true)
  List<Post> findPostsOrderByPublishTime();


}
