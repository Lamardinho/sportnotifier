package com.lamardinho.sportnotifier.controller.api;

import com.lamardinho.sportnotifier.service.NetworkInfoService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/public/network-info")
@RequiredArgsConstructor
@Log4j2
public class NetworkInfoPublicController {

    @NonNull
    private final NetworkInfoService service;

    @GetMapping("/localhost")
    public String getLocalHost() {
        return service.getLocalHost();
    }
}
