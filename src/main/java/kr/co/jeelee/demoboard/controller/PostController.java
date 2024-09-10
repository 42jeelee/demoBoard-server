package kr.co.jeelee.demoboard.controller;

import java.util.List;
import java.util.Optional;

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
		Optional<PostEntity> post = postService.findById(postId);
		return post.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping
	public PostEntity createPost(@Valid @RequestBody PostCreateRequest post) {
		PostEntity postEntity = PostEntity.create(post.getTitle(), post.getAuthor(), post.getPassword(), post.getContent());
		return postService.save(postEntity);
	}

	@PatchMapping("/{postId}")
	public ResponseEntity<PostEntity> updatePost(@Min(1) @PathVariable Long postId, @Valid @RequestBody PostUpdateRequest newPost) {
		return postService.findById(postId)
			.map(existingPost -> {
				existingPost.setTitle(newPost.getTitle());
				existingPost.setContent(newPost.getContent());
				return ResponseEntity.ok(postService.save(existingPost));
			})
			.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{postId}")
	public ResponseEntity<Void> deletePost(@PathVariable Long postId) {
		return postService.findById(postId)
			.map(post -> {
				postService.deleteById(postId);
				return ResponseEntity.ok().<Void>build();
			})
			.orElseGet(() -> ResponseEntity.notFound().build());
	}
}
