package com.wineexchange.api.Domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "wineries")
public class Winery {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @NotNull
    @Size(min = 2, max = 30, message = "Name must be between 2 and 30 characters")
    private String name;
    @NotNull
    @Size(min = 2, max = 30, message = "Location must be between 2 and 30 characters")
    private String location;

    @OneToMany(mappedBy = "winery", cascade = CascadeType.ALL)
    private List<Wine> wines;
}
