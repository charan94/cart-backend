package org.commkart.repo;

import org.commkart.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepo extends JpaRepository<Role, Integer> {

	Role findByName(String name);
}
