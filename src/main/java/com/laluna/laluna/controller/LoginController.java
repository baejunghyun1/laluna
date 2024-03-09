package com.laluna.laluna.controller;

import com.laluna.laluna.domain.dto.member.MemberLoginDto;
import com.laluna.laluna.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class LoginController {

    private final MemberService memberService;

    @PostMapping("/login-process")
    public String login(MemberLoginDto dto) {
        boolean isValidMember = memberService.isValidMember(dto.getMemberid(), dto.getMemberpassword());
        if (isValidMember)
            return "/view/dashboard";
        return "/view/login";
    }

    @PostMapping("/logout")
    public String logout() {
        return "/test";
    }
}
