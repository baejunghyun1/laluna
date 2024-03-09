package com.laluna.laluna.domain.dto.member;

import com.laluna.laluna.domain.entity.Photo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberJoinDto {

    private String memberid;

    private String memberpassword;

    private String phone;

    private String address;

    private String email;

    @Override
    public String toString() {
        return "MemberJoinDto{" +
                "memberid='" + memberid + '\'' +
                ", memberpassword='" + memberpassword + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

