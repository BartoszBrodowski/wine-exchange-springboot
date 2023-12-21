package com.wineexchange.api.repository;

import com.wineexchange.api.domain.WineComposition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;

@RepositoryRestResource
public interface WineCompositionRepository extends JpaRepository<WineComposition, UUID> {
}
