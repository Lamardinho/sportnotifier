package com.lamardinho.sportnotifier.service;

import com.lamardinho.sportnotifier.common.AppException;
import com.lamardinho.sportnotifier.config.security.AppUserDetails;
import com.lamardinho.sportnotifier.dto.RegistrationDTO;
import com.lamardinho.sportnotifier.repository.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.val;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Log4j2
public class RegistrationService {

    @NonNull
    private final UserRepository userRepository;
    @NonNull
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public String processRegistration(@NonNull RegistrationDTO form) {
        if (userRepository.findByLogin(form.getLogin()).isPresent()) {
            throw new AppException("Пользователь с таким именем уже есть!");
        }

        val user = new AppUserDetails();
        user.setLogin(form.getLogin());
        user.setPassword(passwordEncoder.encode(form.getPassword()));

        userRepository.save(user);

        return "redirect:/login";
    }
}
