package com.lamardinho.sportnotifier.controller.api;

import com.lamardinho.sportnotifier.service.NetworkInfoService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/network-info")
@RequiredArgsConstructor
@Log4j2
public class NetworkInfoController {

    @NonNull
    private final NetworkInfoService service;

    @GetMapping("/ip-addresses")
    public List<String> getIpAddresses() {
        return service.getIpAddresses();
    }
}
