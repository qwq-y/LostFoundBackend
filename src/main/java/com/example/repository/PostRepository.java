package com.example.repository;

import com.example.model.Post;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

  // 按照发布时间排序升序
  @Query(value = "select * from post order by post.publish_time ASC", nativeQuery = true)
  List<Post> findPostsOrderByPublishTimeAsc();

  // 按照发布时间排序降序
  @Query(value = "select * from post order by post.publish_time DESC", nativeQuery = true)
  List<Post> findPostsOrderByPublishTimeDesc();

  // 按照给定的物品名称进行匹配，按匹配程度排序
  @Query(value = "SELECT * FROM post\n" +
      "WHERE post.item_name LIKE CONCAT('%', :name, '%')\n" +
      "ORDER BY CASE\n" +
      "    WHEN post.item_name = :name THEN 1\n" +
      "    WHEN post.item_name LIKE CONCAT(:name, '%') THEN 2\n" +
      "    WHEN post.item_name LIKE CONCAT('%', :name) THEN 3\n" +
      "    ELSE 4\n" +
      "END;\n", nativeQuery = true)
  List<Post> findPostsLikeItemName(@Param("name") String itemName);

  // 按照 物品描述 进行匹配，按匹配程度排序
  @Query(value = "SELECT * FROM post\n" +
      "WHERE post.item_description LIKE CONCAT('%', :keyword, '%')\n" +
      "ORDER BY LENGTH(post.item_description) - LENGTH(REPLACE(post.item_description, :keyword, '')) DESC"
      , nativeQuery = true)
  List<Post> findPostsLikeDescription(@Param("keyword") String keyword);

  // 按照 给定地点 选择符合条件的物品
  @Query(value = "SELECT * FROM post\n" +
      "WHERE post.rough_place = :place", nativeQuery = true)
  List<Post> findPostsByExactPlace(@Param("place") String place);

  // 按照 给定物品类型 选择符合条件的物品
  @Query(value = "SELECT * FROM post\n" +
      "WHERE post.item_type = :type", nativeQuery = true)
  List<Post> findPostsByExactType(@Param("type") String type);

  // 更新 物品被认领状态 无返回值
  @Modifying
  @Query(value = "UPDATE post SET claimant_id = :claimantId WHERE post.id = :Id"
      , nativeQuery = true)
  void claimPostsById(@Param("Id") Long Id, @Param("claimantId") Long claimantId);

  //    @Modifying
//    @Query(value = "UPDATE post SET item_description = :itemdes WHERE item_name = :itemname"
//        , nativeQuery = true)
//    void a(@Param("itemname") String itemName, @Param("itemdes") String itemDescription);
  @Query(value = "SELECT * FROM post\n" +
      "WHERE post.id = :id", nativeQuery = true)
  Post findByIdNew(Long id);

  Post findByItemName(String itemName);

}
