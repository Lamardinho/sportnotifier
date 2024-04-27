package com.lamardinho.sportnotifier.repository;

import com.lamardinho.sportnotifier.entity.User;
import lombok.NonNull;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByUsername(@NonNull String username);
}
