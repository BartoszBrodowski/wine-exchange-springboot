package com.wineexchange.api.Domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "wine")
public class Wine {
    @Id
    private long id;
    private String name;
//    private Integer year;
//    private LocalDate date;
//    private Float price;
//    private boolean isAvailable;
}
