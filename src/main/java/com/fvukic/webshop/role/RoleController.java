package com.fvukic.webshop.role;

import com.fvukic.webshop.role.model.RoleRequest;
import com.fvukic.webshop.util.Helper;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    @ApiOperation(value = "Adds new RoleRequest to database", notes = "Enter all RoleRequest parameters to add new RoleRequest object to database", response = RoleRequest.class)
    private void saveNewRoleRequest(@ApiParam(value = "RoleRequest value you pass to the database")@Valid @RequestBody RoleRequest roleRequest, BindingResult bindingResult){
        Helper.validateRequest(bindingResult);
        roleService.saveNewRoleRequest(roleRequest);
    }

    @PutMapping("/{roleId}")
    @ApiOperation(value = "Updates RoleRequest in database", notes = "Enter RoleRequest id to update RoleRequest object in database", response = RoleRequest.class)
    private void updateRoleRequest(@ApiParam(value = "RoleRequest value you pass to the database")@Valid @PathVariable Integer roleId, @RequestBody RoleRequest roleRequest, BindingResult bindingResult){
        Helper.validateRequest(bindingResult);
        roleService.updateRoleRequest(roleRequest,roleId);
    }

    @DeleteMapping("/{roleId}")
    private void deleteRoleRequest(@PathVariable Integer roleId){
        roleService.deleteRoleRequest(roleId);
    }
}
