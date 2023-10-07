package com.lamardinho.sportnotifier.footballdataorg.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class FootballDataOrgService {

    @NonNull
    private final ObjectMapper objectMapper;

    @SneakyThrows
    public void test(){
        objectMapper.writeValueAsString("test");
        log.info("test");
    }
}
