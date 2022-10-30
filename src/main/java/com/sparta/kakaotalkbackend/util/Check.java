package com.sparta.kakaotalkbackend.util;

import com.sparta.kakaotalkbackend.domain.member.Member;
import com.sparta.kakaotalkbackend.exception.ErrorCode;
import com.sparta.kakaotalkbackend.exception.GlobalException;
import com.sparta.kakaotalkbackend.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class Check {

    private MemberRepository memberRepository;


    public void memberExist(Member member) {
        Optional<Member> findMember = memberRepository.findById(member.getId());
        if (findMember.isEmpty()) throw new GlobalException(ErrorCode.MEMBER_NOT_FOUND);
    }
}
