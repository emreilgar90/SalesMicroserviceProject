package com.emre.service;

import com.emre.repository.IRoleRepository;
import com.emre.repository.entity.Role;
import com.emre.utillity.ServiceManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService extends ServiceManager<Role,Long> {
    private final IRoleRepository iRoleRepository;
    public RoleService(JpaRepository<Role, Long> repository, IRoleRepository iRoleRepository) {
        super(repository);
        this.iRoleRepository = iRoleRepository;
    }
}
