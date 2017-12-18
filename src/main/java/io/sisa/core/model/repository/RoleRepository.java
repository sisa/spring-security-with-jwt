package io.sisa.core.model.repository;

import io.sisa.core.model.domain.Role;
import org.springframework.data.repository.CrudRepository;

/**
 * @author isaozturk
 */

public interface RoleRepository extends CrudRepository<Role, Long> {
}
