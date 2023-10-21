package com.lamardinho.sportnotifier.sevice;

import com.lamardinho.sportnotifier.entity.TestEntity;
import com.lamardinho.sportnotifier.repository.TestRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class TestEntityService {

    @NonNull
    private final TestRepository testRepository;

    public List<String> getAllNames() {
        return testRepository.findAll().stream().map(TestEntity::getName).collect(Collectors.toList());
    }
}
