package com.sparta.kakaotalkbackend.service;

import com.sparta.kakaotalkbackend.domain.ResponseDto;
import com.sparta.kakaotalkbackend.domain.friend.Friend;
import com.sparta.kakaotalkbackend.domain.friend.FriendRequestDto;
import com.sparta.kakaotalkbackend.domain.member.Member;
import com.sparta.kakaotalkbackend.domain.member.MemberResponseDto;
import com.sparta.kakaotalkbackend.repository.FriendRepository;
import com.sparta.kakaotalkbackend.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FriendService {
    private final FriendRepository friendRepository;
    private final MemberRepository memberRepository;

    // 친구 찾기 + 추가
    public ResponseDto<String> addFriend (Member member, FriendRequestDto requestDto){
        // memberRepository에서 등록된 회원 username으로 찾기
        Optional<Member> findFriend = memberRepository.findByUsername(requestDto.getUsername());
        if(!findFriend.isPresent()){
            return ResponseDto.fail(404, "사용자를 찾을 수 없습니다.", "Not Found");
        }
        Friend friend = Friend.builder()
                .myUsername(member.getUsername())
                .username(findFriend.get().getUsername())
                .nickname(findFriend.get().getNickname())
                .image(findFriend.get().getImage())
                .status(findFriend.get().getStatus())
                .build();
        friendRepository.save(friend);
        return ResponseDto.success(friend.getNickname() +"님을 친구추가했습니다");
    }

    // 친구 목록 조회
    public ResponseDto<List<MemberResponseDto>> getAllFriends (Member member){
        // friendRepository에서 나의이름을 기준으로 찾기 (나와 연결된 친구를 찾기 위해)
        List<Friend> findFriendList = friendRepository.findAllByMyUsername(member.getUsername());
        List<MemberResponseDto> friendList = new ArrayList<>();

        for (Friend friend:findFriendList) {
            friendList.add(
                    MemberResponseDto.builder()
                            .id((friend.getId()))
                            .username(friend.getUsername())
                            .nickname(friend.getNickname()) // 수정 nickname 추가
                            .image(friend.getImage())
                            .status(friend.getStatus())
                            .build()
            );
        }
        return ResponseDto.success(friendList);
    }

    // 친구 프로필 조회
    public ResponseDto<MemberResponseDto> getFriend (Long friendId){
        Optional<Friend> friend = friendRepository.findById(friendId);

        return ResponseDto.success(
                MemberResponseDto.builder()
                        .id(friend.get().getId())
                        .username(friend.get().getUsername())
                        .nickname(friend.get().getNickname())
                        .image(friend.get().getImage())
                        .status(friend.get().getStatus())
                        .build()
        );
    }
}
