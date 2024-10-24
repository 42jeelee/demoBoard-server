package kr.co.jeelee.demoboard.domain.comment.service;

import kr.co.jeelee.demoboard.domain.comment.dto.request.CommentCreateRequest;
import kr.co.jeelee.demoboard.domain.comment.dto.response.CommentResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommentService {

    List<CommentResponse> findAllByPostId(Long postId, Pageable pageable);

    CommentResponse createByPostId(Long postId, CommentCreateRequest request);

    void deleteCommentById(Long postId, Long commentId);

}
