package com.ahmad.taskTodo.components.security.core.tokenManagement;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.ahmad.taskTodo.components.security.core.tokenManagement.rest.resource.TokenResource;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TokenDataService {
    private final TokenManager securityManager;

    public TokenResource getToken(final Authentication authentication) {
        return this.securityManager.getToken(authentication)
                                   .toTokenResource();
    }

}
