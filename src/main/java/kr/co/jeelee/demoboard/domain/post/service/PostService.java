package kr.co.jeelee.demoboard.domain.post.service;

import kr.co.jeelee.demoboard.domain.post.dto.request.PostCreateRequest;
import kr.co.jeelee.demoboard.domain.post.dto.request.PostUpdateRequest;
import kr.co.jeelee.demoboard.domain.post.dto.response.PostDetailResponse;
import kr.co.jeelee.demoboard.domain.post.dto.response.PostSummaryResponse;
import kr.co.jeelee.demoboard.global.service.EntityService;

import java.util.UUID;

public interface PostService
        extends EntityService<
            PostCreateRequest, PostUpdateRequest,
            PostSummaryResponse, PostDetailResponse
        > {
    void increaseViewsById(UUID id);
}
