package com.ahmad.taskTodo.components.security.core.security.config.jwt.authentication;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import com.ahmad.taskTodo.components.security.core.security.data.TaskTodoAppSecurityUser;
import com.ahmad.taskTodo.components.user.core.userManagement.UserManager;
import com.ahmad.taskTodo.components.user.core.userManagement.data.TaskTodoAppUser;
import com.ahmad.taskTodo.components.user.core.userManagement.exception.UserNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwtTokenToAuthentication implements Converter<Jwt, UsernamePasswordAuthenticationToken> {
    private final UserManager userManager;

    @Override
    public UsernamePasswordAuthenticationToken convert(final Jwt source) {
        final String username = source.getSubject();
        final TaskTodoAppSecurityUser securityUser;
        final TaskTodoAppUser user;
        try {
            user = this.userManager.getUserByEmail(username);
        }
        catch (final UserNotFoundException e) {
            throw new AuthenticationCredentialsNotFoundException(e.getMessage());
        }
        securityUser = new TaskTodoAppSecurityUser(user);
        return new UsernamePasswordAuthenticationToken(securityUser, source, securityUser.getAuthorities());
    }
}
