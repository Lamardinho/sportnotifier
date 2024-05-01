package com.lamardinho.sportnotifier.service;

import com.lamardinho.sportnotifier.common.AppException;
import com.lamardinho.sportnotifier.dtomappers.AppUserDetailsMapper;
import com.lamardinho.sportnotifier.repository.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AppUserDetailsService implements UserDetailsService {

    @NonNull
    private final UserRepository userRepository;
    @NonNull
    private final AppUserDetailsMapper detailsMapper;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String login) {
        val user = userRepository
                .findByLogin(login)
                .orElseThrow(() -> new AppException(String.format("User '%s' not found", login)));

        return detailsMapper.toDto(user);
    }
}
