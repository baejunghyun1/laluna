package com.laluna.laluna.controller;

import com.laluna.laluna.config.MyUserDetails;
import com.laluna.laluna.domain.entity.Pets;
import com.laluna.laluna.service.PetsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalControllerAdvice {

    private final PetsService petsService;
    @ModelAttribute
    public void addAttributes(@AuthenticationPrincipal MyUserDetails userDetails, Model model) {


        if (userDetails != null) {
            Long memberno = userDetails.getMemberno();
            List<Pets> pets = petsService.findByMember(memberno);
            model.addAttribute("pets", pets);
            model.addAttribute("loginId", userDetails.getUsername());
            model.addAttribute("loginRoles", userDetails.getAuthorities());
            model.addAttribute("loginPhone", userDetails.getPhone());
            model.addAttribute("loginAddress", userDetails.getAddress());
            model.addAttribute("loginEmail", userDetails.getEmail());
        }
    }
}
