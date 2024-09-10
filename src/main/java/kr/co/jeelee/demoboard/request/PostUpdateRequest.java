package kr.co.jeelee.demoboard.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PostUpdateRequest {

    @NotBlank(message = "Title cannot be blank.")
    private final String title;

    @NotBlank(message = "Password cannot be blank.")
    private final String password;

    private final String content;
}
