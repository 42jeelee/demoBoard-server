package kr.co.jeelee.demoboard.domain.post.service;

import java.util.List;

import kr.co.jeelee.demoboard.domain.post.dto.request.PostCreateRequest;
import kr.co.jeelee.demoboard.domain.post.dto.request.PostUpdateRequest;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.co.jeelee.demoboard.domain.post.entity.PostEntity;
import kr.co.jeelee.demoboard.domain.post.dao.PostRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {

	private final PostRepository postRepository;
	private final PasswordEncoder passwordEncoder;

	private boolean checkPassword(String password, String dbPassword) {
		return passwordEncoder.matches(password, dbPassword);
	}

	public List<PostEntity> findAll() {
		return postRepository.findAll();
	}

	public PostEntity findById(Long id) {
		return postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Post not found"));
	}

	public PostEntity create(PostCreateRequest request) {
		String encodedPassword = passwordEncoder.encode(request.getPassword());
		PostEntity postEntity = PostEntity.create(request.getTitle(), request.getAuthor(), encodedPassword, request.getContent());
		return postRepository.save(postEntity);
	}

	public PostEntity updateById(Long postId, PostUpdateRequest request) {
		PostEntity postEntity = postRepository.findById(postId)
				.orElseThrow(() -> new IllegalArgumentException("Post not found"));

		if (!checkPassword(request.getPassword(), postEntity.getPassword())) {
			throw new IllegalArgumentException("Wrong password");
		}
		postEntity.update(request.getTitle(), request.getContent());
		return postRepository.save(postEntity);
	}

	public void deleteById(Long postId, String password) {
		postRepository.findPasswordById(postId)
				.ifPresentOrElse(
						dbPassword -> {
							if (!checkPassword(password, dbPassword)) {
								throw new IllegalArgumentException("Wrong password");
							}
							postRepository.deleteById(postId);
						},
						() -> { throw new IllegalArgumentException("Post not found"); }
				);
	}
}
