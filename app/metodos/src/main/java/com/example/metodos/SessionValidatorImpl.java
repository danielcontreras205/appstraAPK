package com.example.metodos;

import org.json.JSONObject;
import java.util.Base64;


public class SessionValidatorImpl implements SessionValidator {

    @Override
    public boolean isTokenValid(String token) {
        try {
            String[] parts = token.split("\\.");
            if (parts.length != 3) return false;
            @SuppressWarnings("NewApi")
            byte[] decodedBytes = Base64.getUrlDecoder().decode(parts[1]);
            String payload = new String(decodedBytes);

            JSONObject jsonObject = new JSONObject(payload);
            long exp = jsonObject.getLong("exp");

            long currentTime = System.currentTimeMillis() / 1000;

            return exp > currentTime;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
