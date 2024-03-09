package com.laluna.laluna.domain.entity;

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
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberno;

    @Column(length = 20, nullable = false, unique = true)
    private String memberid;

    @Column(length = 500, nullable = false)
    private String memberpassword;

    private String roles;

    private String phone;

    @Column(length = 100)
    private String address;

    @Column(length = 100)
    private String email;

   @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
   private List<Pets> pets = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Board> board = new ArrayList<>();

    public static Member createUser(String memberid, String memberpassword, PasswordEncoder passwordEncoder, String phone, String address,
                                    String email, Long memberno) {

        return Member.builder()
                .memberid(memberid)
                .memberpassword(passwordEncoder.encode(memberpassword))
                .roles("USER")
                .phone(phone)
                .address(address)
                .email(email)
                .memberno(memberno)
                .build();
    }
}
