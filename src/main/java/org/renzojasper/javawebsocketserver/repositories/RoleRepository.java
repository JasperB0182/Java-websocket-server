package org.renzojasper.javawebsocketserver.repositories;

import org.renzojasper.javawebsocketserver.models.Role.ERole;
import org.renzojasper.javawebsocketserver.models.Role.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRoleName(ERole roleName);
}
