package com.lamardinho.sportnotifier.config;

import com.lamardinho.sportnotifier.SportnotifierApplication;
import com.lamardinho.sportnotifier.config.util.AppProfile;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@SpringBootTest(
        classes = {
                SportnotifierApplication.class
        }
)
@ActiveProfiles(AppProfile.TEST)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Transactional
@AutoConfigureMockMvc
public @interface ControllerTest {
}
