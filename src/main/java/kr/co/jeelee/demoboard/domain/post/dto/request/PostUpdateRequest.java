package kr.co.jeelee.demoboard.domain.post.dto.request;

import java.util.List;
import java.util.UUID;

public record PostUpdateRequest(
        String title,
        List<UUID> categories,
        String content
) {}
