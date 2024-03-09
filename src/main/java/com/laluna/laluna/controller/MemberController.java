package com.laluna.laluna.controller;

import com.laluna.laluna.config.MyUserDetails;
import com.laluna.laluna.domain.entity.Pets;
import com.laluna.laluna.service.MemberService;
import com.laluna.laluna.service.PetsService;
import lombok.RequiredArgsConstructor;

import com.laluna.laluna.domain.dto.pet.UpdatePetRequest;
import com.laluna.laluna.domain.dto.pet.UpdatePetResponse;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
@RequestMapping("/view")
@RequiredArgsConstructor
public class MemberController {

    private final PetsService petsService;

    @GetMapping("/login")
    public String loginPage() {
        return "/view/login";
    }

    @GetMapping("/join")
    public String joinPage(){
        return "/view/join";
    }


    public void addLoginAttributes(@AuthenticationPrincipal MyUserDetails userDetails, Model model) {

        Long memberno = userDetails.getMemberno();
        List<Pets> pets = petsService.findByMember(memberno);
        System.out.println(pets);
        model.addAttribute("pets", pets);
        model.addAttribute("loginno", userDetails.getMemberno());
        model.addAttribute("loginId", userDetails.getUsername());
        model.addAttribute("loginRoles", userDetails.getAuthorities());
        model.addAttribute("loginPhone", userDetails.getPhone());
        model.addAttribute("loginAddress", userDetails.getAddress());
        model.addAttribute("loginEmail", userDetails.getEmail());
    }

    @GetMapping("/dashboard")
    public String dashboardPage(@AuthenticationPrincipal MyUserDetails userDetails, Model model) {
        addLoginAttributes(userDetails, model);
        return "/view/dashboard";
    }

    @GetMapping("/mypage")
    public String myPage(@AuthenticationPrincipal MyUserDetails userDetails, Model model) {
        addLoginAttributes(userDetails, model);
        return "/view/mypage";
    }

    @PostMapping("/mypage")
    public String updatepetpage(@AuthenticationPrincipal MyUserDetails userDetails,
                              @ModelAttribute UpdatePetRequest requestDTO, RedirectAttributes redirectAttributes) {
        Long petno = userDetails.getMemberno();
        UpdatePetResponse responseDTO = petsService.petUpdate(petno, requestDTO);
        redirectAttributes.addFlashAttribute("mypage", responseDTO);
        return "/view/mypage";
    }
}
