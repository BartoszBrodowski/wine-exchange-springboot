package com.wineexchange.api.Repository;

import com.wineexchange.api.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

}
