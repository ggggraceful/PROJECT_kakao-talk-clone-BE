package com.sparta.kakaotalkbackend.domain.member;

import com.sparta.kakaotalkbackend.exception.ErrorCode;
import com.sparta.kakaotalkbackend.exception.GlobalException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Optional;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column
    private String nickname;

    @Column(nullable = false)
    private String password;

    @Column
    private String image;

    @Column
    private String status;

    public void update(ProfileUpdateRequest profileupdateRequest) {
        this.nickname = profileupdateRequest.getNickname();
        this.image = profileupdateRequest.getImage();
        this.status = profileupdateRequest.getStatus();
    }

}
