package kr.co.jeelee.demoboard.domain.comment.dao;

import kr.co.jeelee.demoboard.domain.post.entity.PostEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.jeelee.demoboard.domain.comment.entity.CommentEntity;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

    List<CommentEntity> findByPost(PostEntity post, Pageable pageable);
}
