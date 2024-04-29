package com.lamardinho.sportnotifier.service;

import com.lamardinho.sportnotifier.dto.RegistrationDTO;
import lombok.NonNull;

public interface RegistrationService {

    long processRegistration(@NonNull RegistrationDTO form);
}
