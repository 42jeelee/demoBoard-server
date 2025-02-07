package kr.co.jeelee.demoboard.domain.member.service;

import kr.co.jeelee.demoboard.domain.member.dto.request.MemberCreateRequest;
import kr.co.jeelee.demoboard.domain.member.dto.request.MemberUpdateRequest;
import kr.co.jeelee.demoboard.domain.member.dto.response.MemberDetailResponse;
import kr.co.jeelee.demoboard.domain.member.dto.response.MemberSimpleResponse;
import kr.co.jeelee.demoboard.global.service.EntityService;

public interface MemberService
        extends EntityService<
            MemberCreateRequest, MemberUpdateRequest,
            MemberSimpleResponse, MemberDetailResponse
        > {
}
