package com.fvukic.webshop.role;

import com.fvukic.webshop.exception.EntityWithIdNotFoundException;
import com.fvukic.webshop.role.model.RoleRequest;
import com.fvukic.webshop.util.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImplementation implements RoleService {

    private RoleRepository roleRepository;

    private static final Logger logger = LoggerFactory.getLogger(RoleServiceImplementation.class);

    public RoleServiceImplementation(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> getAllRoles() {
        logger.info("Fetching all roles");
        List<Role> roles = roleRepository.findAll();
        logger.info("Fetched {} roles", roles.size());
        return roles;
    }

    @Override
    public void saveNewRoleRequest(RoleRequest roleRequest) {
        logger.info("Saving new role request");
        Role role = getRoleRequest(roleRequest);
        roleRepository.save(role);
        logger.info("Saved new role request");
    }

    @Override
    public void updateRoleRequest(RoleRequest roleRequest, Integer roleId) {
        logger.info("Updating role with ID: {}", roleId);
        Role existingRole = roleRepository.findById(roleId)
                .orElseThrow(() -> {
                    logger.error("Error occurred while updating role: Role with ID {} not found", roleId);
                    return new EntityWithIdNotFoundException(ErrorResponse.ERROR_WRONG_ID,roleId);
                });
        existingRole.setRoleName(roleRequest.getRoleName());
        roleRepository.save(existingRole);
        logger.info("Updated role with ID: {}", roleId);
    }

    @Override
    public void deleteRoleRequest(Integer roleId) {
        logger.info("Deleting role with ID: {}", roleId);
        if (!roleRepository.existsById(roleId)){
            logger.error("Error occurred while deleting role: Role with ID {} not found", roleId);
            throw new EntityWithIdNotFoundException(ErrorResponse.ERROR_WRONG_ID,roleId);
        }
        roleRepository.deleteById(roleId);
        logger.info("Deleted role with ID: {}", roleId);
    }

    private Role getRoleRequest(RoleRequest roleRequest){
        logger.info("Getting role from request");
        Role role = Role.builder().roleName(roleRequest.getRoleName()).build();
        logger.info("Got role from request");
        return role;

    }
}
