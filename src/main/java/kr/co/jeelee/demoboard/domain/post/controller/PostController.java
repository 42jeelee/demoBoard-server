package kr.co.jeelee.demoboard.domain.post.controller;

import java.util.List;
import java.util.UUID;

import jakarta.validation.Valid;
import kr.co.jeelee.demoboard.domain.post.dto.request.PostUpdateRequest;
import kr.co.jeelee.demoboard.domain.post.dto.response.PostDetailResponse;
import kr.co.jeelee.demoboard.domain.post.dto.response.PostSummaryResponse;
import kr.co.jeelee.demoboard.global.annotation.AllowedSortFields;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import kr.co.jeelee.demoboard.domain.post.dto.request.PostCreateRequest;
import kr.co.jeelee.demoboard.domain.post.service.PostService;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
@Validated
public class PostController {

	private final PostService postService;

	@GetMapping
	@AllowedSortFields({"createdAt", "updatedAt"})
	public List<PostSummaryResponse> getPosts(
			@PageableDefault(size = 5) Pageable pageable
	) {
		return postService.findAll(pageable);
	}

	@GetMapping("/{id}")
	public PostDetailResponse getPostById(
			@PathVariable UUID id
	) {
		return postService.findById(id);
	}

	@PostMapping
	public PostDetailResponse createPost(
			@Valid @RequestBody PostCreateRequest request
	) {
		return postService.create(request);
	}

	@PutMapping("/{id}")
	public PostDetailResponse updatePost(
			@PathVariable UUID id,
			@Valid @RequestBody PostUpdateRequest request
	) {
		return postService.update(id, request);
	}

	@DeleteMapping("/{id}/delete")
	public void deletePost(@PathVariable UUID id) {
		postService.delete(id);
	}
}
