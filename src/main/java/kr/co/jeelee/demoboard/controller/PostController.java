package kr.co.jeelee.demoboard.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.jeelee.demoboard.entity.PostEntity;
import kr.co.jeelee.demoboard.request.PostRequest;
import kr.co.jeelee.demoboard.service.PostService;

@RestController
@RequestMapping("/posts")
public class PostController {

	@Autowired
	private PostService postService;

	@GetMapping
	public List<PostEntity> getPosts() {
		return postService.findAll();
	}

	@GetMapping("/{postId}")
	public ResponseEntity<PostEntity> getPostById(@PathVariable Long postId) {
		Optional<PostEntity> post = postService.findById(postId);
		return post.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping
	public PostEntity createPost(@Validated @RequestBody PostRequest post) {
		PostEntity postEntity = new PostEntity();
		postEntity.setTitle(post.getTitle());
		postEntity.setContent(post.getContent());
		postEntity = postService.save(postEntity);
		return postService.save(postEntity);
	}

	@PutMapping("/{postId}")
	public ResponseEntity<PostEntity> updatePost(@PathVariable Long postId, @Validated @RequestBody PostRequest newPost) {
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
