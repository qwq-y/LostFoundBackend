package com.example.repository;

import com.example.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

//  @Query(value = "SELECT * from user0 where user0.student_id = :studentId", nativeQuery = true)
//  User findByStudentIdNew(Long studentId);

    User findByStudentId(Long studentId);
}
