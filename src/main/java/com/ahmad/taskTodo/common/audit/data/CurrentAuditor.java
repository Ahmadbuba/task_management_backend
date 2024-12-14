package com.ahmad.taskTodo.common.audit.data;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.ahmad.taskTodo.components.security.core.security.data.TaskTodoAppSecurityUser;

public class CurrentAuditor implements AuditorAware<String> {

    private static final String SYSTEM = "system";

    @Override
    @NonNull
    public Optional<String> getCurrentAuditor() {
        final Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();
        if (authentication == null ||
                !authentication.isAuthenticated() ||
                authentication instanceof AnonymousAuthenticationToken
        ) {
            return Optional.of(SYSTEM);
        }

        final TaskTodoAppSecurityUser principal = (TaskTodoAppSecurityUser) authentication.getPrincipal();
        return Optional.ofNullable(principal.getUsername());
    }
}
