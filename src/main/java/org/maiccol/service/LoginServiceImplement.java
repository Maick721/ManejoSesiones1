package org.maiccol.service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Arrays;
import java.util.Optional;

public class LoginServiceImplement implements LoginService {
    @Override
    public Optional<String> getUserName(HttpServletRequest request) {

        Cookie[] cookies = request.getCookies() != null ? request.getCookies() : new Cookie[0];
        return Arrays.stream(cookies)
                .filter(c-> "username".equals(c.getName()))//Filtra el valor de la cookie username
                .map(Cookie::getValue)//mapea y busca el valor
                .findAny(); // Tomar la primera coincidencia
    }
}
