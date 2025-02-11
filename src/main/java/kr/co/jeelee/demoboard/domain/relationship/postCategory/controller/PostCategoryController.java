package kr.co.jeelee.demoboard.domain.relationship.postCategory.controller;

import jakarta.validation.constraints.Size;
import kr.co.jeelee.demoboard.domain.category.dto.response.CategoryDetailResponse;
import kr.co.jeelee.demoboard.domain.category.dto.response.CategoryResponse;
import kr.co.jeelee.demoboard.domain.post.dto.response.PostDetailResponse;
import kr.co.jeelee.demoboard.domain.post.dto.response.PostSummaryResponse;
import kr.co.jeelee.demoboard.domain.relationship.postCategory.service.PostCategoryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class PostCategoryController {

    private final PostCategoryServiceImpl postCategoryService;

    @GetMapping(value = "/posts/{postId}/categories")
    public List<CategoryResponse> getCategoriesByPostId(
            @PathVariable UUID postId
    ) {
        return postCategoryService.getBsByAId(postId);
    }

    @PostMapping(value = "/posts/{postId}/categories")
    public PostDetailResponse joinCategories(
            @PathVariable UUID postId,
            @RequestBody @Size(min = 1) List<UUID> categoryId
    ) {
        return postCategoryService.create(postId, categoryId);
    }

    @DeleteMapping(value = "/posts/{postId}/category/{categoryId}")
    public void deleteCategory(
            @PathVariable UUID postId,
            @PathVariable UUID categoryId
    ) {
        postCategoryService.deleteById(postId, categoryId);
    }

    @GetMapping(value = "/categories/{categoryId}/posts")
    public List<PostSummaryResponse> getPostsByCategoryId(
            @PathVariable UUID categoryId
    ) {
        return postCategoryService.getAsByBId(categoryId);
    }

    @PostMapping(value = "/categories/{categoryId}/posts")
    public CategoryDetailResponse joinPosts(
            @PathVariable UUID categoryId,
            @RequestBody @Size(min = 1) List<UUID> postId
    ) {
        return postCategoryService.create(postId, categoryId);
    }

    @DeleteMapping(value = "/categories/{categoryId}/posts/{postId}")
    public void deletePost(
            @PathVariable UUID categoryId,
            @PathVariable UUID postId
    ) {
        postCategoryService.deleteById(postId, categoryId);
    }

}
