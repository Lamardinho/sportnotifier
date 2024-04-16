package com.lamardinho.sportnotifier;

import lombok.extern.log4j.Log4j2;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Log4j2
class SportnotifierApplicationTests {

    @Autowired
    private Environment environment;

    @Test
    void contextLoads() {
        log.info("ok");

        val profiles = environment.getActiveProfiles();
        val profileList = Arrays.stream(profiles).collect(Collectors.toSet());

        assertThat(profileList)
                .isEqualTo(Set.of("test"));
    }
}
