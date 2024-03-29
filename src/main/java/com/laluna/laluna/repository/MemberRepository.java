package com.laluna.laluna.repository;

import com.laluna.laluna.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findBymemberid(String memberid);
}
