package org.renzojasper.javawebsocketserver.services;

import org.renzojasper.javawebsocketserver.dao.RoleDAO;
import org.renzojasper.javawebsocketserver.models.Role.Role;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private RoleDAO roleDAO;

    public RoleService(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    public Role getRoleByName(String roleName) {
        return roleDAO.getRoleByName(roleName);
    }

    public void createRole(Role role) {
        this.roleDAO.saveRole(role);
    }
}
