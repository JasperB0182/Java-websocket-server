package org.renzojasper.javawebsocketserver.dao;

import org.renzojasper.javawebsocketserver.models.Role.ERole;
import org.renzojasper.javawebsocketserver.models.Role.Role;
import org.renzojasper.javawebsocketserver.repositories.RoleRepository;
import org.springframework.stereotype.Component;

@Component
public class RoleDAO {
    private final RoleRepository roleRepository;

    public RoleDAO(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role getRoleByName(String roleName) {
        ERole eRole = ERole.valueOf(roleName);

        return roleRepository.findByRoleName(eRole);
    }

    public void saveRole(Role role) {
        this.roleRepository.save(role);
    }
}
