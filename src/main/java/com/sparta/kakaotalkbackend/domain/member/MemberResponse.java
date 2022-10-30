package com.sparta.kakaotalkbackend.domain.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberResponse {

    private String username;

    private String nickname;

    private String image;

    private String status;

    public MemberResponse(Member member) {
        this.username = member.getUsername();
        this.nickname = member.getNickname();
        this.image = member.getImage();
        this.status = member.getStatus();
    }

}
