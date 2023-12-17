package com.wineexchange.api.Repository;

import com.wineexchange.api.Domain.Wine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.UUID;

@RepositoryRestResource
public interface WineRepository extends JpaRepository<Wine, UUID> {

}
