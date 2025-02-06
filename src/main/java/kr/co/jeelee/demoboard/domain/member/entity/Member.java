package kr.co.jeelee.demoboard.domain.member.entity;

import jakarta.persistence.*;
import kr.co.jeelee.demoboard.global.entity.BaseTimeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "member")
@NoArgsConstructor
@Getter
public class Member extends BaseTimeEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String password;

    @Column
    private String email;

    private Member(String name, String nickname, String email, String password) {
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
    }

    public static Member of(String name, String nickname, String email, String password) {
        return new Member(name, nickname, email, password);
    }
}
