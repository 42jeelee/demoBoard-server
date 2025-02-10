package kr.co.jeelee.demoboard.domain.relationship.postCategory.controller;

import kr.co.jeelee.demoboard.domain.relationship.postCategory.service.PostCategoryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class PostCategoryController {

    private final PostCategoryServiceImpl postCategoryService;

    @PostMapping("/posts/{postId}/category")
    public boolean joinCategory(
            @PathVariable UUID postId,
            @RequestBody UUID categoryId
    ) {
        return postCategoryService.create(postId, categoryId);
    }

    @DeleteMapping("/posts/{postId}/category/{categoryId}")
    public void deleteCategory(
            @PathVariable UUID postId,
            @PathVariable UUID categoryId
    ) {
        postCategoryService.deleteById(postId, categoryId);
    }

}
