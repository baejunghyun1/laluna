package com.laluna.laluna.domain.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Pets {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long petno;  //펫 번호

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "memberno")
    private Member member;   //주인 아이디 받아오기

    private String petname; //펫 이름

    private int petage;  //펫 나이

    private Boolean petsex;  //펫 성별

    private String petkind;  //펫 종류

    @Column(length = 500)
    private String petfeature;  //펫 정보(특징)

    private String petvac; //펫 백신

    private String petcondition; //펫 건강상태


    public void update( String petname, int petage, Boolean petsex, String petkind, String petfeature,String petvac, String petcondition ) {
                this.petname = petname;
                this.petage = petage;
                this.petsex = petsex;
                this.petkind = petkind;
                this.petfeature = petfeature;
                this.petvac = petvac;
                this.petcondition = petcondition;
    }

    @Override
    public String toString() {
        return "Pets{" +
                "petnum=" + petno +
                ", member=" + member +
                ", petname='" + petname + '\'' +
                ", petage=" + petage +
                ", petsex='" + petsex + '\'' +
                ", petkind='" + petkind + '\'' +
                ", petfeature='" + petfeature + '\'' +
                ", petvac='" + petvac + '\'' +
                ", petcondition='" + petcondition + '\'' +
                '}';
    }

}
