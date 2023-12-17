package com.wineexchange.api.Domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    @Size(min = 2, max = 30, message = "Name must be between 2 and 30 characters")
    private String name;

    @NotNull
    @Email(message = "Please provide a valid email address")
    private String email;

    @NotNull
    @Size(min = 8, max = 30, message = "Password must be between 8 and 30 characters")
    private String password;

    private List<Wine> cart;
}
