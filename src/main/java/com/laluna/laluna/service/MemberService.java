package com.laluna.laluna.service;

//import com.laluna.laluna.domain.dto.member.UpdateMemberResponse;
//import com.laluna.laluna.domain.dto.member.UpdateMemberRequest;
import com.laluna.laluna.domain.entity.Member;
import com.laluna.laluna.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository repository;

    public Optional<Member> findOne(String memberid) {
        return repository.findBymemberid(memberid);
    }

    public boolean isValidMember(String memberid, String password) {
        Optional<Member> member = findOne(memberid);
        if(member.isPresent()) {
            return member.get().getMemberpassword().equals(password);
        }
        return false;
    }

}
