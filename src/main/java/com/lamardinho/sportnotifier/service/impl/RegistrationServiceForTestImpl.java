package com.lamardinho.sportnotifier.service.impl;

import com.lamardinho.sportnotifier.config.util.AppProfile;
import com.lamardinho.sportnotifier.dto.RegistrationDTO;
import com.lamardinho.sportnotifier.service.RegistrationService;
import lombok.NonNull;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile(AppProfile.TEST)
@Service
public class RegistrationServiceForTestImpl implements RegistrationService {

    @Override
    public long processRegistration(@NonNull RegistrationDTO form) {
        return form.getLogin().equals("badUser") ? 0 : 1;
    }
}
