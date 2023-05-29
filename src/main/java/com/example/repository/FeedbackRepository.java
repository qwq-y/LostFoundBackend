package com.example.repository;

import com.example.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

  @Modifying
  @Query(value = "insert into Feedback(reporter_id,time,body)\n" +
      "values (:reporterId,:time,:body)", nativeQuery = true)
  Feedback insertNew(
      @Param("reporterId") Long reporterId,
      @Param("time") Long time,
      @Param("body") String body
  );
}
