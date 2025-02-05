package kr.co.jeelee.demoboard.domain.member.dto.request;

import jakarta.validation.constraints.NotBlank;

public record MemberCreateRequest(
        @NotBlank(message = "name can't be Blank.") String name,
        @NotBlank(message = "nickname can't be Blank.") String nickname,
        String email,
        @NotBlank(message = "password can't be Blank.") String password
) {}
