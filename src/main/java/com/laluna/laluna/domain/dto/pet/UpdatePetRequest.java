package com.laluna.laluna.domain.dto.pet;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class UpdatePetRequest {

    private String petname; //펫 이름

    private int petage;  //펫 나이

    private Boolean petsex;  //펫 성별

    private String petkind;  //펫 종류

    private String petfeature;  //펫 정보(특징)

    private String petvac; //펫 백신

    private String petcondition;



}
