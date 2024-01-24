package com.wineexchange.api.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;

@Data
@Entity
@Table(name = "wines")
public class Wine {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotBlank(message = "Name is mandatory")
    @Size(min = 2, max = 30, message = "Name must be between 2 and 30 characters")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "wine_id")
    private List<Tag> tags = new ArrayList<>();

    @NotNull(message = "Price is mandatory")
    @DecimalMin(value = "0.01", message = "Price must be greater than or equal to 0.01")
    @Digits(integer = 8, fraction = 2, message = "Invalid price format")
    private Float price;

    @Embedded
    private YearInfo years;

    @Column(name = "created_at")
    private LocalDate createdAt = LocalDate.now();

    @Column(name = "available")
    private boolean available = true;

    @ManyToOne
    @JoinColumn(name = "winery_id")
    private Winery winery;

    @OneToOne
    @JoinColumn(name = "composition_id")
    private WineComposition wineComposition;
}
