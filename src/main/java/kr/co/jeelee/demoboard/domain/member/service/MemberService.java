package kr.co.jeelee.demoboard.domain.member.service;

import kr.co.jeelee.demoboard.domain.member.dto.request.MemberCreateRequest;
import kr.co.jeelee.demoboard.domain.member.dto.request.MemberUpdateRequest;
import kr.co.jeelee.demoboard.domain.member.dto.response.MemberDetailResponse;
import kr.co.jeelee.demoboard.domain.member.dto.response.MemberSimpleResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface MemberService {

    List<MemberSimpleResponse> findAll(Pageable pageable);

    MemberDetailResponse findById(UUID id);

    MemberDetailResponse create(MemberCreateRequest request);

    MemberDetailResponse updateById(UUID id, MemberUpdateRequest request);

    void delete(UUID id);

}
