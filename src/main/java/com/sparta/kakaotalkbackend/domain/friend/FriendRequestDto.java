package com.sparta.kakaotalkbackend.domain.friend;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FriendRequestDto {
    // username으로 친구 검색 시 사용
    private String username;
}
