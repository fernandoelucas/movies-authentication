package com.movies.authentication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movies.authentication.entity.Role;
import com.movies.authentication.repository.RoleRepository;

@Service
public class RoleService {

    private final RoleRepository roleRepository;


    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role getRoleByName(String name) {
        return roleRepository.findRoleByName(name);
    }

    public Role updateRole(Role role) {
        return roleRepository.save(role);
    }
}
