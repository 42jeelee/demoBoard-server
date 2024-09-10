package kr.co.jeelee.demoboard.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import kr.co.jeelee.demoboard.entity.PostEntity;
import kr.co.jeelee.demoboard.repository.PostRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {

	private final PostRepository postRepository;

	public List<PostEntity> findAll() {
		return postRepository.findAll();
	}

	public Optional<PostEntity> findById(Long id) {
		return postRepository.findById(id);
	}

	public PostEntity save(PostEntity post) {
		return postRepository.save(post);
	}

	public void deleteById(Long id) {
		postRepository.deleteById(id);
	}
}
