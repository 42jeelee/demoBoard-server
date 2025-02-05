package kr.co.jeelee.demoboard.domain.post.service;

import kr.co.jeelee.demoboard.domain.post.dto.request.PostCreateRequest;
import kr.co.jeelee.demoboard.domain.post.dto.request.PostUpdateRequest;
import kr.co.jeelee.demoboard.domain.post.dto.response.PostDetailResponse;
import kr.co.jeelee.demoboard.domain.post.dto.response.PostSummaryResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostService {

    List<PostSummaryResponse> findAll(Pageable pageable);

    PostDetailResponse findById(Long id);

    PostDetailResponse create(PostCreateRequest request);

    PostDetailResponse updateById(Long postId, PostUpdateRequest request);

    Long countPostByCategoryId(Long categoryId);

    void deleteById(Long postId, String password);

    void increaseViewsById(Long postId);

}
