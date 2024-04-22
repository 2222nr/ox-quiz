package com.example.oxquiz.repository;

import com.example.oxquiz.dto.QuizDto;
import com.example.oxquiz.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
    @Query(value = "select * from quiz order by id", nativeQuery = true)
    List<Quiz> searchQuery();

    @Query(value = "SELECT * FROM quiz WHERE id ORDER BY RAND() LIMIT 1;", nativeQuery = true)
    Quiz randomOne();

}
