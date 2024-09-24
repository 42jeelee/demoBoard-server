package kr.co.jeelee.demoboard.service;

import java.util.List;

import kr.co.jeelee.demoboard.request.PostCreateRequest;
import kr.co.jeelee.demoboard.request.PostUpdateRequest;
import org.springframework.stereotype.Service;

import kr.co.jeelee.demoboard.entity.PostEntity;
import kr.co.jeelee.demoboard.repository.PostRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {

	private final PostRepository postRepository;

	private boolean validatePassword(String dbPassword, String password) {
		return dbPassword.equals(password);
	}

	public List<PostEntity> findAll() {
		return postRepository.findAll();
	}

	public PostEntity findById(Long id) {
		return postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Post not found"));
	}

	public PostEntity create(PostCreateRequest request) {
		PostEntity postEntity = PostEntity.create(request.getTitle(), request.getAuthor(), request.getPassword(), request.getContent());
		return postRepository.save(postEntity);
	}

	public PostEntity updateById(Long postId, PostUpdateRequest request) {
		PostEntity postEntity = postRepository.findById(postId)
				.orElseThrow(() -> new IllegalArgumentException("Post not found"));

		if (!validatePassword(request.getPassword(), postEntity.getPassword())) {
			throw new IllegalArgumentException("Wrong password");
		}
		postEntity.update(request.getTitle(), request.getContent());
		return postRepository.save(postEntity);
	}

	public void deleteById(Long postId, String password) {
		postRepository.findPasswordById(postId)
				.ifPresentOrElse(
						dbPassword -> {
							if (!validatePassword(dbPassword, password)) {
								throw new IllegalArgumentException("Wrong password");
							}
							postRepository.deleteById(postId);
						},
						() -> { throw new IllegalArgumentException("Post not found"); }
				);
	}
}
