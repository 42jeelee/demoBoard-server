package kr.co.jeelee.demoboard.domain.auth.dto;

import kr.co.jeelee.demoboard.domain.member.entity.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

public record CustomUserDetails(
        Member member,
        Collection<? extends GrantedAuthority> authorities
) implements UserDetails {
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return member().getPassword();
    }

    @Override
    public String getUsername() {
        return member().getId().toString();
    }

    public static CustomUserDetails from(Member member) {
        return new CustomUserDetails(
                member,
                Arrays.stream(member.getRole().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .toList()
        );
    }
}
