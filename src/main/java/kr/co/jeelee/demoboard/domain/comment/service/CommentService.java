package kr.co.jeelee.demoboard.domain.comment.service;

import kr.co.jeelee.demoboard.domain.comment.dto.request.CommentCreateRequest;
import kr.co.jeelee.demoboard.domain.comment.dto.response.CommentResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface CommentService {

    List<CommentResponse> findAllByPostId(UUID postId, Pageable pageable);

    CommentResponse createByPostId(UUID postId, CommentCreateRequest request);

    void deleteCommentById(UUID postId, UUID id);

}
