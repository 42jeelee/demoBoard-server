package kr.co.jeelee.demoboard.domain.post.controller;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import kr.co.jeelee.demoboard.domain.post.dto.request.PostUpdateRequest;
import kr.co.jeelee.demoboard.domain.post.dto.response.PostDetailResponse;
import kr.co.jeelee.demoboard.domain.post.dto.response.PostSummaryResponse;
import lombok.RequiredArgsConstructor;

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
	public List<PostSummaryResponse> getPosts() {
		return postService.findAll();
	}

	@GetMapping("/{postId}")
	public PostDetailResponse getPostById(@Min(1) @PathVariable Long postId) {
		return postService.findById(postId);
	}

	@PostMapping
	public PostDetailResponse createPost(@Valid @RequestBody PostCreateRequest request) {
		return postService.create(request);
	}

	@PutMapping("/{postId}")
	public PostDetailResponse updatePost(@Min(1) @PathVariable Long postId, @Valid @RequestBody PostUpdateRequest request) {
		return postService.updateById(postId, request);
	}

	@PutMapping("/{postId}/delete")
	public void deletePost(@PathVariable Long postId, @RequestBody String password) {
		postService.deleteById(postId, password);
	}
}
