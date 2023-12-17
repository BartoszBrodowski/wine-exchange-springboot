package com.wineexchange.api.Domain;

import jakarta.persistence.*;
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
    private String name;
    private String location;

    @OneToMany(mappedBy = "winery", cascade = CascadeType.ALL)
    private List<Wine> wines;
}
