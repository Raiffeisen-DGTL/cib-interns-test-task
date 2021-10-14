package ru.raiffeisen.shopwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.raiffeisen.shopwarehouse.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role getRoleByName(String name);
    Role getRoleById(Long id);
    void deleteRoleById(long id);
}
