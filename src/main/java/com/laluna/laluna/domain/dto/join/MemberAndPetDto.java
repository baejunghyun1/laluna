package com.laluna.laluna.domain.dto.join;

import com.laluna.laluna.domain.entity.Pets;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MemberAndPetDto {
    // 멤버 정보
    private String memberid;
    private String memberpassword;
    private String phone;
    private String address;
    private String email;
    private Long memberno;

    // 펫 정보
    private String petname;
    private int petage;
    private Boolean petsex;
    private String petkind;
    private String petfeature;
    private String petvac;
    private String petcondition;


}
