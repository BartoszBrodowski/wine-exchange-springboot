package com.wineexchange.api.Domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "wines")
public class Wine {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private Integer year;
    private LocalDate date;
    private Float price;
    private boolean isAvailable;

    @ManyToOne
    @JoinColumn(name = "winery_id")
    private Winery winery;

    @OneToOne
    @JoinColumn(name = "composition_id")
    private WineComposition wineComposition;
}
