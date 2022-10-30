package com.sparta.kakaotalkbackend.repository;

import com.sparta.kakaotalkbackend.domain.jwt.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
}
