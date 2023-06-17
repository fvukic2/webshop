package com.fvukic.webshop.role;

import com.fvukic.webshop.role.model.RoleRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    private RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    private List<Role> getAllRoles(){
        return roleService.getAllRoles();
    }

    @PostMapping
    private void saveNewRoleRequest(@RequestBody RoleRequest roleRequest){
        roleService.saveNewRoleRequest(roleRequest);
    }

    @PutMapping("/{roleId}")
    private void updateRoleRequest(@RequestBody RoleRequest roleRequest,@PathVariable Integer roleId){
        roleService.updateRoleRequest(roleRequest,roleId);
    }

    @DeleteMapping("/{roleId}")
    private void deleteRoleRequest(@PathVariable Integer roleId){
        roleService.deleteRoleRequest(roleId);
    }
}
