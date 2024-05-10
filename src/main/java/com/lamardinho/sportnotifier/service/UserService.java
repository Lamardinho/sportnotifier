package com.lamardinho.sportnotifier.service;

import com.lamardinho.sportnotifier.dto.ChangePasswordDTO;
import com.lamardinho.sportnotifier.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class UserService {

    @NonNull
    private final UserRepository userRepository;
    @NonNull
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void changePassword(
            @NonNull ChangePasswordDTO dto,
            @NonNull String name
    ) {
        var user = userRepository
                .findByLogin(name)
                .orElseThrow(() -> new EntityNotFoundException(format("User with login '%s' does not exist", name)));

        val matches = passwordEncoder.matches(dto.getOldPassword(), user.getPassword());
        Assert.isTrue(matches, "You entered your old password incorrectly");

        user.setPassword(passwordEncoder.encode(dto.getNewPassword()));

        userRepository.save(user);
    }
}
