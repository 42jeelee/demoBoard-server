package kr.co.jeelee.demoboard.domain.post.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.jeelee.demoboard.domain.post.entity.Post;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query(value = "SELECT p.password FROM Post p WHERE p.id = :postId")
    Optional<String> findPasswordById(@Param("postId") Long postId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Post p SET p.views = p.views + 1 WHERE p.id = :postId")
    void incrementViews(@Param("postId") Long postId);

    Long countPostByCategoryId(Long categoryId);

}
