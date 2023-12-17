package com.wineexchange.api.Domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
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
    private Integer year;

    @Column(columnDefinition = "DATE")
    private LocalDate date;

    @NotNull(message = "Price is mandatory")
    @DecimalMin(value = "0.01", message = "Price must be greater than or equal to 0.01")
    @Digits(integer = 8, fraction = 2, message = "Invalid price format")
    private Float price;
    @NotNull(message = "Quantity is mandatory")

    private boolean isAvailable = true;

    @ManyToOne
    @JoinColumn(name = "winery_id")
    private Winery winery;

    @OneToOne
    @JoinColumn(name = "composition_id")
    private WineComposition wineComposition;
}
