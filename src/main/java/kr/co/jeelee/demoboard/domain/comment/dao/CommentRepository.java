package kr.co.jeelee.demoboard.domain.comment.dao;

import kr.co.jeelee.demoboard.domain.post.entity.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.jeelee.demoboard.domain.comment.entity.Comment;

import java.util.List;
import java.util.UUID;

public interface CommentRepository extends JpaRepository<Comment, UUID> {

    List<Comment> findByPost(Post post, Pageable pageable);
}
