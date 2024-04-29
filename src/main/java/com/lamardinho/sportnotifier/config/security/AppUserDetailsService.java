package com.lamardinho.sportnotifier.config.security;

import com.lamardinho.sportnotifier.common.AppException;
import com.lamardinho.sportnotifier.entity.user.AppUserDetails;
import com.lamardinho.sportnotifier.entity.user.UserPermission;
import com.lamardinho.sportnotifier.repository.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppUserDetailsService implements UserDetailsService {

    @NonNull
    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String login) {
        val user = userRepository
                .findByLogin(login)
                .orElseThrow(() -> new AppException(String.format("User '%s' not found", login)));
        val permissions = user.getPermissions()
                .stream().map(UserPermission::getName).collect(Collectors.toSet());

        val userDto = new UserAuthDetailsData();
        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPassword());
        userDto.setAuthorities(permissions);

        return new AppUserDetails() // todo: use mapstruct
                .setId(user.getId())
                .setLogin(login)
                .setPassword(user.getPassword())
                .setCreatedTime(user.getCreatedTime())
                .setPermissions(user.getPermissions());
    }
}
