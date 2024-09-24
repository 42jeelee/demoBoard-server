package kr.co.jeelee.demoboard.controller;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import kr.co.jeelee.demoboard.request.PostUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import kr.co.jeelee.demoboard.entity.PostEntity;
import kr.co.jeelee.demoboard.request.PostCreateRequest;
import kr.co.jeelee.demoboard.service.PostService;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
@Validated
public class PostController {

	private final PostService postService;

	@GetMapping
	public List<PostEntity> getPosts() {
		return postService.findAll();
	}

	@GetMapping("/{postId}")
	public ResponseEntity<PostEntity> getPostById(@Min(1) @PathVariable Long postId) {
		PostEntity postEntity = postService.findById(postId);
		return ResponseEntity.ok(postEntity);
	}

	@PostMapping
	public ResponseEntity<PostEntity> createPost(@Valid @RequestBody PostCreateRequest request) {
		PostEntity postEntity = postService.create(request);
		return ResponseEntity.ok(postEntity);
	}

	@PutMapping("/{postId}")
	public ResponseEntity<PostEntity> updatePost(@Min(1) @PathVariable Long postId, @Valid @RequestBody PostUpdateRequest request) {
		PostEntity postEntity = postService.updateById(postId, request);
		return ResponseEntity.ok(postEntity);
	}

	@DeleteMapping("/{postId}")
	public ResponseEntity<Void> deletePost(@PathVariable Long postId, @RequestBody String password) {
		postService.deleteById(postId, password);
		return ResponseEntity.noContent().build();
	}
}
