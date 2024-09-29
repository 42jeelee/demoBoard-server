package kr.co.jeelee.demoboard.domain.post.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.jeelee.demoboard.domain.post.entity.PostEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PostRepository extends JpaRepository<PostEntity, Long> {
    @Query(value = "SELECT p.password FROM POST p WHERE p.postId = :postId")
    Optional<String> findPasswordById(@Param("postId") Long postId);
}
