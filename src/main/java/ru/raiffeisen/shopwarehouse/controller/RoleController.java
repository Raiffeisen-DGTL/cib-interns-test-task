package ru.raiffeisen.shopwarehouse.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.raiffeisen.shopwarehouse.entity.Role;
import ru.raiffeisen.shopwarehouse.service.RoleService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RoleController {

    private final RoleService roleService;
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping(value = "/roles/createRole")
    public ResponseEntity<?> createRole(@RequestBody Role role) {
        return roleService.create(role) ?
                new ResponseEntity<>(role, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/role/getRole/{id}")
    public ResponseEntity<Role> getRole(@PathVariable(name = "id") int id) {
        Role role = roleService.get(id);
        if(role != null) {
            return new ResponseEntity<>(role, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/roles/getAllRoles")
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> roles = roleService.getAll();
        return !roles.isEmpty() && !roles.equals(null) ?
                new ResponseEntity<>(roles, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = "/roles/updateRole")
    public ResponseEntity<Role> updateRole(@RequestBody Role role) {
        boolean isRoleUpdated = roleService.update(role, role.getId());
        if(isRoleUpdated) {
            return new ResponseEntity<>(role, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/roles/deleteRole/{id}")
    public ResponseEntity<?> deleteRole(@PathVariable(name = "id") int id) {
        boolean isRoleDeleted = roleService.delete(id);
        if(isRoleDeleted) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
