package com.wineexchange.api.Repository;

import com.wineexchange.api.Domain.Wine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface WineRepository extends JpaRepository<Wine, String> {

}
