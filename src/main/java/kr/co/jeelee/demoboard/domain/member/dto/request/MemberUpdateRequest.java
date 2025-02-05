package kr.co.jeelee.demoboard.domain.member.dto.request;

public record MemberUpdateRequest(
        String name, String nickname,
        String email, String password
) {}
