package com.fvukic.webshop.role;

import com.fvukic.webshop.exception.EntityWithIdNotFoundException;
import com.fvukic.webshop.role.model.RoleRequest;
import com.fvukic.webshop.util.ErrorResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImplementation implements RoleService {

    private RoleRepository roleRepository;

    public RoleServiceImplementation(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public void saveNewRoleRequest(RoleRequest roleRequest) {
        Role role = getRoleRequest(roleRequest);
        roleRepository.save(role);
    }

    @Override
    public void updateRoleRequest(RoleRequest roleRequest, Integer roleId) {
        Role existingRole = roleRepository.findById(roleId)
                .orElseThrow(() -> new EntityWithIdNotFoundException(ErrorResponse.ERROR_WRONG_ID,roleId));
        existingRole.setRoleName(roleRequest.getRoleName());
        roleRepository.save(existingRole);
    }

    @Override
    public void deleteRoleRequest(Integer roleId) {
        if (!roleRepository.existsById(roleId)){
            throw new EntityWithIdNotFoundException(ErrorResponse.ERROR_WRONG_ID,roleId);
        }
        roleRepository.deleteById(roleId);
    }

    private Role getRoleRequest(RoleRequest roleRequest){
        return Role.builder().roleName(roleRequest.getRoleName()).build();

    }
}
