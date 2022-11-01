package com.sparta.kakaotalkbackend.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "Member not found", "해당 멤버는 존재하지 않습니다.");

    private final Integer httpStatus;
    private final String message;
    private final String detail;
}
