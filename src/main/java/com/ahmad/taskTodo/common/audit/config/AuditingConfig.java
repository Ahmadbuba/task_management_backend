package com.ahmad.taskTodo.common.audit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.ahmad.taskTodo.common.audit.data.CurrentAuditor;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class AuditingConfig {
    @Bean
    public AuditorAware<String> auditorAware() {
        return new CurrentAuditor();
    }
}
