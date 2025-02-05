package kr.co.jeelee.demoboard.domain.member.dto.response;

import kr.co.jeelee.demoboard.domain.member.entity.Member;

import java.util.UUID;

public record MemberSimpleResponse(
        UUID id, String name, String nickname
) {
    public static MemberSimpleResponse from(Member member) {
        return new MemberSimpleResponse(
                member.getId(),
                member.getName(),
                member.getNickname()
        );
    }
}
