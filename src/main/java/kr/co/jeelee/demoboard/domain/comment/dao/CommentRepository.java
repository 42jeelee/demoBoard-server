package kr.co.jeelee.demoboard.domain.comment.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.jeelee.demoboard.domain.comment.entity.CommentEntity;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
}
