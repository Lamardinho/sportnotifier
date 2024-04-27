package com.lamardinho.sportnotifier.service;

import com.lamardinho.sportnotifier.dto.RegistrationForm;
import com.lamardinho.sportnotifier.entity.User;
import com.lamardinho.sportnotifier.repository.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class RegistrationService {

    @NonNull
    private final UserRepository userRepository;
    /*@NonNull
    private final PasswordEncoder passwordEncoder;*/

    public String processRegistration(@NonNull RegistrationForm form) {
        val user = new User();
        user.setUsername(form.getUsername());
        //  user.setPassword(passwordEncoder.encode(form.getPassword()));
        user.setFullname(form.getFullname());
        user.setStreet(form.getStreet());
        user.setCity(form.getCity());
        user.setState(form.getState());
        user.setZip(form.getZip());
        user.setPhoneNumber(form.getPhone());

        userRepository.save(user);

        return "redirect:/login";
    }
}
