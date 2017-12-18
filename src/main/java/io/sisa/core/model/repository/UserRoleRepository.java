package io.sisa.core.model.repository;

import io.sisa.core.model.domain.UserRole;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author isaozturk
 */

public interface UserRoleRepository extends CrudRepository<UserRole, Long> {

    List<UserRole> findByUserId(Long userId);

}
