package com.wineexchange.api.repository;

import com.wineexchange.api.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;

@RepositoryRestResource
public interface UserRepository extends JpaRepository<User, UUID> {
    public boolean existsByEmail(String email);
    public User findByEmail(String email);
}
