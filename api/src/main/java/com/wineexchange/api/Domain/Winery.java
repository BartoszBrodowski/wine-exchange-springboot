package com.wineexchange.api.Domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "wineries")
public class Winery {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String location;

    @OneToMany(mappedBy = "winery", cascade = CascadeType.ALL)
    private List<Wine> wines;
}
