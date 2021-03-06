package com.enigma.api.inventory.configs;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

    private static final long serialVersionUID = -7858869558953243875L;

    @Override
    public void commence(HttpServletRequest httpRequest, HttpServletResponse httpResponse,
                         AuthenticationException e) throws IOException, ServletException {
        httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");

    }
}
