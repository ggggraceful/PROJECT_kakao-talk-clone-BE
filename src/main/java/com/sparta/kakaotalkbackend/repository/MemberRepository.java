package com.sparta.kakaotalkbackend.repository;

import com.sparta.kakaotalkbackend.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

<<<<<<< HEAD
public interface MemberRepository extends JpaRepository<Member, Long> {
=======
public interface MemberRepository  extends JpaRepository<Member, Long> {
	Optional<Member> findByUsername(String username);

>>>>>>> a838b9eccca4ff279f386002967c54523dc1903b
}
