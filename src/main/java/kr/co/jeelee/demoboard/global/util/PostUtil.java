package kr.co.jeelee.demoboard.global.util;

import kr.co.jeelee.demoboard.domain.post.dao.PostRepository;
import kr.co.jeelee.demoboard.domain.post.entity.Post;
import kr.co.jeelee.demoboard.global.exception.custom.CustomException;
import kr.co.jeelee.demoboard.global.exception.custom.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PostUtil implements EntityUtil<Post> {

    private final PostRepository postRepository;

    @Override
    public Post getById(UUID id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));
    }

    @Override
    public boolean hasById(UUID id) {
        return postRepository.existsById(id);
    }

    @Override
    public List<Post> getAllByIds(List<UUID> ids) {
        List<Post> posts = postRepository.findAllById(ids);

        ids.stream()
                .filter(id -> posts.stream().noneMatch(post -> post.getId().equals(id)))
                .findFirst()
                .ifPresent(id -> {
                    throw new CustomException(ErrorCode.POST_NOT_FOUND);
                });

        return postRepository.findAllById(ids);
    }

}
