package io.sisa.core.model.repository;

import io.sisa.core.model.domain.AppUser;
import org.springframework.data.repository.CrudRepository;

/**
 * @author isaozturk
 */

public interface ApplicationUserRepository extends CrudRepository<AppUser, Long> {
    AppUser findByUsername(String username);
}
