package kr.co.jeelee.demoboard.domain.member.service;

import kr.co.jeelee.demoboard.domain.member.dao.MemberRepository;
import kr.co.jeelee.demoboard.domain.member.dto.request.MemberCreateRequest;
import kr.co.jeelee.demoboard.domain.member.dto.request.MemberUpdateRequest;
import kr.co.jeelee.demoboard.domain.member.dto.response.MemberDetailResponse;
import kr.co.jeelee.demoboard.domain.member.dto.response.MemberSimpleResponse;
import kr.co.jeelee.demoboard.domain.member.entity.Member;
import kr.co.jeelee.demoboard.global.exception.custom.CustomException;
import kr.co.jeelee.demoboard.global.exception.custom.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<MemberSimpleResponse> findAll(Pageable pageable) {
        return memberRepository.findAll(pageable).stream()
                .map(MemberSimpleResponse::from)
                .toList();
    }

    @Override
    public List<MemberSimpleResponse> search(String query, Pageable pageable) {
        return memberRepository.searchMembersByNicknameOrName(query, query, pageable).stream()
                .map(MemberSimpleResponse::from)
                .toList();
    }

    @Override
    public MemberDetailResponse findById(UUID id) {
        return memberRepository.findById(id)
                .map(MemberDetailResponse::from)
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
    }

    @Override
    @Transactional
    public MemberDetailResponse create(MemberCreateRequest request) {
        String encodedPassword = passwordEncoder.encode(request.password());

        Member member = Member.of(
                request.name(),
                request.nickname(),
                request.email(),
                encodedPassword
        );

        return MemberDetailResponse.from(memberRepository.save(member));
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        memberRepository.deleteById(id);
    }

    @Override
    @Transactional
    public MemberDetailResponse update(UUID id, MemberUpdateRequest memberUpdateRequest) {
        return null;
    }
}
