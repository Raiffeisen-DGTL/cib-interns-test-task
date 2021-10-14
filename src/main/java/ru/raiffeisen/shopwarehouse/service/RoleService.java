package ru.raiffeisen.shopwarehouse.service;

import org.springframework.stereotype.Service;
import ru.raiffeisen.shopwarehouse.entity.Role;
import ru.raiffeisen.shopwarehouse.repository.RoleRepository;

import java.util.List;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public boolean create(Role role) {
        roleRepository.save(role);
        if (roleRepository.getRoleByName(role.getName()) != null) {
            return true;
        } else {
            return false;
        }
    }

    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    public Role get(long id) {
        return roleRepository.getRoleById(id);
    }

    public boolean update(Role role, long id) {
        Role localRole = roleRepository.getById(id);
        if(!localRole.equals(null)) {
            role.setId(id);
            roleRepository.save(role);
            return true;
        } else {
            return false;
        }
    }

    public boolean delete(long id) {
        Role role = roleRepository.getRoleById(id);
        roleRepository.delete(role);
        role = roleRepository.getRoleByName(role.getName());
        if (role == null) {
            return true;
        } else {
            return false;
        }
    }
}