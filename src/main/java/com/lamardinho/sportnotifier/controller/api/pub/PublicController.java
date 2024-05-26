package com.lamardinho.sportnotifier.controller.api.pub;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.val;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/public")
@RequiredArgsConstructor
@Log4j2
public class PublicController {

    @NonNull
    private final RestTemplate restTemplate;

    /**
     * Для тестирования связанности разных deployments в 1ом кластере.
     */
    @GetMapping("/nginx")
    public String nginx() {
        val url = "http://nginx";
        return restTemplate.getForObject(url, String.class);
    }
}
