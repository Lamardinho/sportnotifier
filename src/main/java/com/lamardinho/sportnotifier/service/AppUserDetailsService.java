package com.lamardinho.sportnotifier.service;

import com.lamardinho.sportnotifier.dtomappers.AppUserDetailsMapper;
import com.lamardinho.sportnotifier.entity.user.AppUserDetails;
import com.lamardinho.sportnotifier.repository.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.val;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Log4j2
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
                .orElse(new AppUserDetails()); // не бросаем тут исключение "User not found" чтобы не дать злоумышленникам осуществлять подбор логинов
        return detailsMapper.toDto(user);
    }
}
