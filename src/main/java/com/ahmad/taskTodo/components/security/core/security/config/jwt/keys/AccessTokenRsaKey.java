package com.ahmad.taskTodo.components.security.core.security.config.jwt.keys;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "rsa.access-token")
public record AccessTokenRsaKey(RSAPublicKey publicKey, RSAPrivateKey privateKey) {
}
