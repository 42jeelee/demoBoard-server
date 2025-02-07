package kr.co.jeelee.demoboard.domain.comment.dto.response;

import kr.co.jeelee.demoboard.domain.comment.entity.Comment;
import kr.co.jeelee.demoboard.domain.member.dto.response.MemberSimpleResponse;

import java.time.LocalDateTime;
import java.util.UUID;

public record CommentResponse (UUID id, String content, MemberSimpleResponse author, LocalDateTime createdAt) {
    public static CommentResponse from(Comment comment) {
        return new CommentResponse(
                comment.getId(),
                comment.getContent(),
                MemberSimpleResponse.from(comment.getAuthor()),
                comment.getCreatedAt()
        );
    }
}
