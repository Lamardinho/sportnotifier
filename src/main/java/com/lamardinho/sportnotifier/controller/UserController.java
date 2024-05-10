package com.lamardinho.sportnotifier.controller;

import com.lamardinho.sportnotifier.common.aop.TrackExecutionTime;
import com.lamardinho.sportnotifier.dto.ChangePasswordDTO;
import com.lamardinho.sportnotifier.service.UserService;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Log4j2
public class UserController {

    @NonNull
    private final UserService service;

    @PostMapping("/change-password")
    @TrackExecutionTime
    public void changePassword(
            @RequestBody @Valid ChangePasswordDTO dto,
            @NonNull Principal principal
    ) {
        service.changePassword(dto, principal.getName());
    }
}
