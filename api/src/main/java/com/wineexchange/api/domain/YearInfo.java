package com.wineexchange.api.domain;

import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDate;

@Data
public class YearInfo {
    @Column(name = "harvest_date", columnDefinition = "DATE")
    private LocalDate harvest;

    @Column(name = "bottling_date", columnDefinition = "DATE")
    private LocalDate bottling;
}

