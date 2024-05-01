package com.lamardinho.sportnotifier.dtomappers;

import com.lamardinho.sportnotifier.dto.AppUserDetailsDTO;
import com.lamardinho.sportnotifier.entity.user.AppUserDetails;
import com.lamardinho.sportnotifier.entity.user.UserPermission;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.stream.Collectors;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        imports = {
                UserPermission.class,
                Collectors.class,
        }
)
public interface AppUserDetailsMapper {

    AppUserDetailsDTO toDto(AppUserDetails entity);
}
