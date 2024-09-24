package kr.co.jeelee.demoboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.jeelee.demoboard.entity.PostEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PostRepository extends JpaRepository<PostEntity, Long> {
    @Query(value = "SELECT p.password FROM POST p WHERE p.postId = :postId")
    Optional<String> findPasswordById(@Param("postId") Long postId);
}
