package com.laluna.laluna.domain.dto.pet;

import com.laluna.laluna.domain.entity.Pets;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PetResponse {
    private String petname; //펫 이름

    private int petage;  //펫 나이

    private Boolean petsex;  //펫 성별

    private String petkind;  //펫 종류

    private String petfeature;  //펫 정보(특징)

    private String petvac; //펫 백신

    private String petcondition;

    public PetResponse(Pets pet) {
        this.petname = pet.getPetname();
        this.petage = pet.getPetage();
        this.petsex = pet.getPetsex();
        this.petkind = pet.getPetkind();
        this.petfeature = pet.getPetfeature();
        this.petvac = pet.getPetvac();
        this.petcondition = pet.getPetcondition();
    }
}
