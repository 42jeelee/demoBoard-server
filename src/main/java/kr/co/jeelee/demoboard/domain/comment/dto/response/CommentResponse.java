package kr.co.jeelee.demoboard.domain.comment.dto.response;

import kr.co.jeelee.demoboard.domain.comment.entity.Comment;

import java.time.LocalDateTime;

public record CommentResponse (Long id, String content, String author, LocalDateTime createdAt) {
    public static CommentResponse from(Comment comment) {
        return new CommentResponse(
                comment.getId(),
                comment.getContent(),
                comment.getAuthor(),
                comment.getCreatedAt()
        );
    }
}
