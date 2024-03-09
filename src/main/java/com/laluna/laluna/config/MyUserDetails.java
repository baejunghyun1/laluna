package com.laluna.laluna.config;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
public class MyUserDetails extends User {
    private Long memberno;
    private String phone;
    private String address;
    private String email;

    public MyUserDetails(String username, String password,
                         Collection<? extends GrantedAuthority> authorities,
                         Long memberno, String phone, String address, String email) {
        super(username, password, authorities);
        this.memberno = memberno;
        this.phone = phone;
        this.address = address;
        this.email = email;
    }
    public Long getMemberno() {
        return memberno;
    }
    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }
}
