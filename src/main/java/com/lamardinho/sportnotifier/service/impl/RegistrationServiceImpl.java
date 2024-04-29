package com.lamardinho.sportnotifier.service.impl;

import com.lamardinho.sportnotifier.common.AppException;
import com.lamardinho.sportnotifier.entity.user.AppUserDetails;
import com.lamardinho.sportnotifier.config.util.AppProfile;
import com.lamardinho.sportnotifier.dto.RegistrationDTO;
import com.lamardinho.sportnotifier.repository.UserRepository;
import com.lamardinho.sportnotifier.service.RegistrationService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Profile({AppProfile.PROD, AppProfile.PRE_PROD, AppProfile.STAGE, AppProfile.DEV})
@Service
@RequiredArgsConstructor
@Log4j2
public class RegistrationServiceImpl implements RegistrationService {

    @NonNull
    private final UserRepository userRepository;
    @NonNull
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public long processRegistration(@NonNull RegistrationDTO form) {
        if (userRepository.findByLogin(form.getLogin()).isPresent()) {
            throw new AppException("There is already a user with the same name!");
        }

        var user = new AppUserDetails();
        user.setLogin(form.getLogin());
        user.setPassword(passwordEncoder.encode(form.getPassword()));

        user = userRepository.save(user);
        log.info("User with login: '{}' successfully created. ID:{}", user.getLogin(), user.getId());
        return user.getId();
    }
}
