package com.wineexchange.api.Domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "wine_compositions")
public class WineComposition {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String grape;
    private String alcohol;
}
