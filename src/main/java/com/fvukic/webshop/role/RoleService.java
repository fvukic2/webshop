package com.fvukic.webshop.role;

import com.fvukic.webshop.role.model.RoleRequest;

import java.util.List;

public interface RoleService {

     List<Role> getAllRoles();

     void saveNewRoleRequest(RoleRequest roleRequest);

     void updateRoleRequest(RoleRequest roleRequest, Integer roleId);

     void deleteRoleRequest(Integer roleId);
}
