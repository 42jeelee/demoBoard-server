package kr.co.jeelee.demoboard.domain.post.service;

import kr.co.jeelee.demoboard.domain.post.dto.request.PostCreateRequest;
import kr.co.jeelee.demoboard.domain.post.dto.request.PostUpdateRequest;
import kr.co.jeelee.demoboard.domain.post.dto.response.PostDetailResponse;
import kr.co.jeelee.demoboard.domain.post.dto.response.PostSummaryResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface PostService {

    List<PostSummaryResponse> findAll(Pageable pageable);

    PostDetailResponse findById(UUID id);

    PostDetailResponse create(PostCreateRequest request);

    PostDetailResponse updateById(UUID id, PostUpdateRequest request);

    void deleteById(UUID id);

    void increaseViewsById(UUID id);

}
