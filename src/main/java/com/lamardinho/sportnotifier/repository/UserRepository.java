package com.lamardinho.sportnotifier.repository;

import com.lamardinho.sportnotifier.entity.user.AppUserDetails;
import lombok.NonNull;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<AppUserDetails, Long> {

    Optional<AppUserDetails> findByLogin(@NonNull String username);
}
