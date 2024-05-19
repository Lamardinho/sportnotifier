package com.lamardinho.sportnotifier.service;

import lombok.SneakyThrows;
import lombok.val;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.List;

@Service
public class NetworkInfoService {

    @EventListener(ApplicationReadyEvent.class)
    @SneakyThrows
    public List<String> getIpAddresses() {
        val networkAddresses = new ArrayList<String>();

        val interfaces = NetworkInterface.getNetworkInterfaces();

        while (interfaces.hasMoreElements()) {
            val networkInterface = interfaces.nextElement();

            val inetAddresses = networkInterface.getInetAddresses();
            while (inetAddresses.hasMoreElements()) {
                val address = inetAddresses.nextElement();
                networkAddresses.add(address.toString());
            }
        }

        return networkAddresses;
    }

    @SneakyThrows
    public String getLocalHost() {
        val inetAddress = InetAddress.getLocalHost();
        return inetAddress.getHostAddress();
    }
}
