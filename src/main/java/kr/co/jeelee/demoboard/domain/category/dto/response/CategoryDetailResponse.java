package kr.co.jeelee.demoboard.domain.category.dto.response;

import kr.co.jeelee.demoboard.domain.category.entity.Category;
import kr.co.jeelee.demoboard.domain.post.dto.response.PostSummaryResponse;

import java.util.List;
import java.util.UUID;

public record CategoryDetailResponse(
        UUID id, String name,
        List<PostSummaryResponse> posts, int postCount
) {
    public static CategoryDetailResponse from(Category category) {
        List<PostSummaryResponse> posts = category.getPostCategories().stream()
                .map(postCategory -> PostSummaryResponse.from(postCategory.getA()))
                .toList();

        return new CategoryDetailResponse(
                category.getId(),
                category.getName(),
                posts,
                posts.size()
        );
    }
}
