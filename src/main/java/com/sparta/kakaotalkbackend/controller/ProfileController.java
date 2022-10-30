package com.sparta.kakaotalkbackend.controller;

import com.sparta.kakaotalkbackend.domain.ResponseDto;
import com.sparta.kakaotalkbackend.domain.member.Member;
import com.sparta.kakaotalkbackend.domain.member.ProfileUpdateRequest;
import com.sparta.kakaotalkbackend.domain.member.MemberResponse;
import com.sparta.kakaotalkbackend.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/myprofile")
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping // @AuthenticationPrincipal -> UserDetailsImpl 만들어지면 넣기
    public ResponseDto<MemberResponse> getMyProfile(Member member) {
        return profileService.getMyProfile(member);
    }

    /*
    마이 프로필 수정
    기존 데이터를 프로필 수정할 때 보내서 바꾸지 않은 데이터는 기존 데이터가 리쿼스트로 오도록 하자.
    예를 들어 이미지만 바꾸고 상태메세지는 안바꾸는 경우......................
     */

    @PutMapping// @AuthenticationPrincipal -> UserDetailsImpl 만들어지면 넣기
    public ResponseDto<MemberResponse> updateMyProfile(@RequestBody ProfileUpdateRequest profileUpdateRequest,
                                                       Member member) {
        return profileService.updateMyProfile(profileUpdateRequest, member);
    }


}
