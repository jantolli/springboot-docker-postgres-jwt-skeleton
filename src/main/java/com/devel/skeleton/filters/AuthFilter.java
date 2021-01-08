package com.devel.skeleton.filters;

import com.devel.skeleton.constants.Constants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.HttpHeaders;
import java.io.IOException;

public class AuthFilter extends GenericFilter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        String authHeader = httpRequest.getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Authorization token must be provided");
        }
        String bearerToken = null;
        try {
            bearerToken = authHeader.split("Bearer ")[1];
        } catch (IndexOutOfBoundsException ex) {
            throw new ResponseStatusException (HttpStatus.UNAUTHORIZED, "Authorization token must be Bearer [token]");
        }
        try {
            Claims claims = Jwts.parser().setSigningKey(Constants.SECRET).parseClaimsJws(bearerToken).getBody();
        } catch (Exception ex) {
            throw new ResponseStatusException (HttpStatus.UNAUTHORIZED, "invalid/expired token");
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
