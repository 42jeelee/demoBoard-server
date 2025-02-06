package kr.co.jeelee.demoboard.domain.post.dto.request;

import java.util.List;

public record PostUpdateRequest(
        String title,
        List<String> categories,
        String content
) {}
