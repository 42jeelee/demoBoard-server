package kr.co.jeelee.demoboard.domain.member.controller;

import jakarta.validation.Valid;
import kr.co.jeelee.demoboard.domain.member.dto.request.MemberCreateRequest;
import kr.co.jeelee.demoboard.domain.member.dto.response.MemberDetailResponse;
import kr.co.jeelee.demoboard.domain.member.dto.response.MemberSimpleResponse;
import kr.co.jeelee.demoboard.domain.member.service.MemberService;
import kr.co.jeelee.demoboard.global.annotation.AllowedSortFields;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
@Validated
public class MemberController {

    private final MemberService memberService;

    @GetMapping
    @AllowedSortFields({"createdAt", "updatedAt"})
    public List<MemberSimpleResponse> getMembers(
            @PageableDefault(size = 5) Pageable pageable
    ) {
        return memberService.findAll(pageable);
    }

    @GetMapping(value = "/{id}")
    public MemberDetailResponse getMemberDetail(@PathVariable UUID id) {
        return memberService.findById(id);
    }

    @PostMapping
    public MemberDetailResponse createMember(@Valid @RequestBody MemberCreateRequest request) {
        return memberService.create(request);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteMember(@PathVariable UUID id) {
        memberService.delete(id);
    }

}
