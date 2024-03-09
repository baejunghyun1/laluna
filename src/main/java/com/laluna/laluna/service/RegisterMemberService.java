package com.laluna.laluna.service;

import com.laluna.laluna.domain.entity.Member;
import com.laluna.laluna.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterMemberService {

    private final PasswordEncoder passwordEncoder;

    private final MemberRepository repository;

    public Member join(String memberid, String memberpassword, String phone, String address,
                     String email, Long memberno){
        Member member = Member.createUser(memberid, memberpassword, passwordEncoder, phone, address, email, memberno);
        validateDuplicateMember(member);

        return repository.save(member);


    }

    private void validateDuplicateMember(Member member) {
        repository.findBymemberid(member.getMemberid())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }
}
