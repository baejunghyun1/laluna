package com.laluna.laluna.controller;

import com.laluna.laluna.config.MyUserDetails;
import com.laluna.laluna.domain.dto.join.MemberAndPetDto;
import com.laluna.laluna.domain.dto.pet.UpdatePetRequest;
import com.laluna.laluna.domain.entity.Pets;
import com.laluna.laluna.service.PetsService;
import com.laluna.laluna.service.RegisterMemberService;
import groovy.util.logging.Log4j;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthorizationController {

    private final RegisterMemberService registerMemberService;
    private final PetsService petsService;

    @PostMapping("/join")
    public ResponseEntity<String> join(@RequestBody MemberAndPetDto dto) {
        System.out.println(dto.toString());  // 로깅
        try {
            registerMemberService.join(dto.getMemberid(), dto.getMemberpassword(), dto.getPhone(), dto.getAddress(), dto.getEmail(), dto.getMemberno());
            petsService.savePet(dto, dto.getMemberid());
            return ResponseEntity.ok("join success");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    Logger logger = LoggerFactory.getLogger(PetsService.class);

    @PostMapping("/update_pet_info")
    public ResponseEntity<String> update_pet_info(@AuthenticationPrincipal MyUserDetails userDetails, UpdatePetRequest dto) {
        try {
            Long petnum = userDetails.getMemberno();
            petsService.petUpdate(petnum, dto);
            return ResponseEntity.ok("펫 정보 업데이트 성공");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/pets/{petno}")
    public ResponseEntity<Void> deletePet(@PathVariable Long petno) {
        petsService.deletePet(petno);
        return ResponseEntity.noContent().build();
    }

}