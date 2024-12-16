package com.ahmad.taskTodo.components.security.core.tokenManagement;

import java.text.MessageFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Component;

import com.ahmad.taskTodo.components.security.core.tokenManagement.data.TaskTodoAppSecurityToken;
import com.ahmad.taskTodo.components.security.core.security.data.TaskTodoAppSecurityUser;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TokenManager {
    private final JwtEncoder accessTokenEncoder;

    private final JwtEncoder refreshTokenEncoder;

    private static final String ISSUER = "securityManagement";


    public TokenManager(final JwtEncoder accessTokenEncoder, @Qualifier("jwtRefreshTokenEncoder") final JwtEncoder refreshTokenEncoder) {
        this.accessTokenEncoder = accessTokenEncoder;
        this.refreshTokenEncoder = refreshTokenEncoder;
    }


    public TaskTodoAppSecurityToken getToken(final Authentication authentication) {
        if (!(authentication.getPrincipal() instanceof final TaskTodoAppSecurityUser securityUser)) {
            throw new BadCredentialsException(
              MessageFormat.format("principal {0} is not of User type", authentication.getPrincipal().getClass())
            );
        }

        final String accessToken = this.createAccessToken(authentication);
        final TaskTodoAppSecurityToken securityToken = new TaskTodoAppSecurityToken();
        securityToken.setIssuer(ISSUER);
        securityToken.setAccessToken(accessToken);

        final String refreshToken;
        if (authentication.getCredentials() instanceof final Jwt jwt) {
            final Instant now = Instant.now();
            final Instant expiresAt = jwt.getExpiresAt();
            final Duration duration = Duration.between(now, expiresAt);
            final long daysUntilExpired = duration.toDays();
            if (daysUntilExpired < 7) {
                refreshToken = this.createRefreshToken(authentication);
            } else {
                refreshToken = jwt.getTokenValue();
            }
        } else {
            refreshToken = this.createRefreshToken(authentication);
        }
        securityToken.setRefreshToken(refreshToken);

        return securityToken;
    }


    private String createAccessToken(final Authentication authentication) {
        final TaskTodoAppSecurityUser securityUser = (TaskTodoAppSecurityUser) authentication.getPrincipal();
        final Instant now = Instant.now();

        final JwtClaimsSet claimsSet = JwtClaimsSet.builder()
                                                   .issuer("Security Manager")
                                                   .issuedAt(now)
                                                   .expiresAt(now.plus(5, ChronoUnit.MINUTES))
                                                   .subject(securityUser.getUsername())
                                                   .build();

        return this.accessTokenEncoder.encode(JwtEncoderParameters.from(claimsSet)).getTokenValue();
    }

    private String createRefreshToken(final Authentication authentication) {
        final TaskTodoAppSecurityUser securityUser = (TaskTodoAppSecurityUser) authentication.getPrincipal();
        final Instant now = Instant.now();

        final JwtClaimsSet claimsSet = JwtClaimsSet.builder()
                                                   .issuer("Security Manager")
                                                   .issuedAt(now)
                                                   .expiresAt(now.plus(30, ChronoUnit.DAYS))
                                                   .subject(securityUser.getUsername())
                                                   .build();

        return this.refreshTokenEncoder.encode(JwtEncoderParameters.from(claimsSet)).getTokenValue();
    }



}
