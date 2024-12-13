package com.ahmad.taskTodo.components.userComponent.core.userManagement.database.entity;

import java.util.Objects;
import java.util.UUID;

import org.hibernate.annotations.NaturalId;

import com.ahmad.taskTodo.components.userComponent.core.userManagement.data.TaskTodoAppRole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class UserEntity {

    @Id
    private UUID id;

    private String name;

    @Column(unique = true)
    @NaturalId
    private String email;

    private String password;

    private TaskTodoAppRole role;

    private boolean active;

    protected UserEntity() {}

    public static UserEntity createUser(final String name, final String email, final String password) {
        Objects.requireNonNull(name, "Name cannot be null");
        Objects.requireNonNull(email, "Email cannot be null");
        Objects.requireNonNull(password, "Password cannot be null");
        final UserEntity userEntity = new UserEntity();
        userEntity.id = UUID.randomUUID();
        userEntity.name = name;
        userEntity.email = email;
        userEntity.password = password;
        userEntity.role = TaskTodoAppRole.USER;
        userEntity.active = true;
        return userEntity;
    }

    @Override
    public boolean equals(final Object other) {
        return other instanceof UserEntity                   // check type with instanceof, not getClass()
               && ((UserEntity) other).getEmail()
                                      .equals(this.getEmail());
        // compare natural ids
    }

    @Override
    public int hashCode() {
        return this.email.hashCode();

    }

}