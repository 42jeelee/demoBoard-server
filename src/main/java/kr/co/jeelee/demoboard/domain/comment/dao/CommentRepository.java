package kr.co.jeelee.demoboard.domain.comment.dao;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.jeelee.demoboard.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CommentRepository extends JpaRepository<Comment, UUID> {

    List<Comment> findByPost_Id(UUID postId, Pageable pageable);

    Optional<Comment> findByIdAndPost_Id(UUID id, UUID postId);

    @Query(value = "SELECT CASE WHEN c.post.id = :postId THEN true ELSE false END FROM Comment c WHERE c.id = :commentId")
    Boolean isInPost(UUID postId, UUID commentId);
}
