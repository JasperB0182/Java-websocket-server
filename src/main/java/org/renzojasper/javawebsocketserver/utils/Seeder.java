package org.renzojasper.javawebsocketserver.utils;

import org.renzojasper.javawebsocketserver.models.Role.ERole;
import org.renzojasper.javawebsocketserver.models.Role.Role;
import org.renzojasper.javawebsocketserver.services.RoleService;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class Seeder {
    private RoleService roleService;

    public Seeder(RoleService roleService) {
        this.roleService = roleService;
    }

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        Role userRol = new Role(ERole.ROLE_USER);
        roleService.createRole(userRol);
    }
}