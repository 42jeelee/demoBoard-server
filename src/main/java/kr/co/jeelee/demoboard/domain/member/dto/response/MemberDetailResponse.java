package kr.co.jeelee.demoboard.domain.member.dto.response;

import kr.co.jeelee.demoboard.domain.member.entity.Member;

import java.time.LocalDateTime;
import java.util.UUID;

public record MemberDetailResponse(
        UUID id, String name, String nickname, String email,
        LocalDateTime updatedAt, LocalDateTime createdAt
) {
    public static MemberDetailResponse from(Member member) {
        return new MemberDetailResponse(
                member.getId(),
                member.getName(),
                member.getNickname(),
                member.getEmail(),
                member.getUpdatedAt(),
                member.getCreatedAt()
        );
    }
}
