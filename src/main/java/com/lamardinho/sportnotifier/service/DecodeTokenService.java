package com.lamardinho.sportnotifier.service;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.val;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Service
public class DecodeTokenService {

    private final Base64.Decoder decoder = Base64.getDecoder();
    private final JsonParser jsonParser = JsonParserFactory.getJsonParser();

    @NonNull
    @SneakyThrows
    public Map<String, Object> decodeToken(@Valid @NotBlank @NonNull String token) {
        val parts = token.split("\\.");

        switch (parts.length) {
            case 1 -> {
                return decodePart(token);
            }
            case 3 -> {
                val decodedToken = new HashMap<String, Object>();
                decodedToken.put("header", decodePart(parts[0]));
                decodedToken.put("payload", decodePart(parts[1]));
                decodedToken.put("signature", parts[2]);
                return decodedToken;
            }
            default -> throw new IllegalArgumentException("Invalid token part");
        }
    }

    @NonNull
    private Map<String, Object> decodePart(@NonNull String part) {
        try {
            val bytes = decoder.decode(part);
            val decodedPart = new String(bytes);
            return jsonParser.parseMap(decodedPart);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid token part", e);
        }
    }
}
