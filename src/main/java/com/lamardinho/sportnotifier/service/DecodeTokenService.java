package com.lamardinho.sportnotifier.service;

import lombok.NonNull;
import lombok.val;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class DecodeTokenService {

    @NonNull
    public Map<String, Object> decodeToken(@NonNull String token) {
        val jsonParser = JsonParserFactory.getJsonParser();
        val decoder = java.util.Base64.getDecoder();

        val bytes = decoder.decode(token);
        val decodedToken = new String(bytes);

        return jsonParser.parseMap(decodedToken);
    }
}
