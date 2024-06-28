package odk.kalanso.stock.services;

import odk.kalanso.stock.entities.Role;
import odk.kalanso.stock.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public List<Role> AllRoles() {

        return roleRepository.findAll();
    }

    public Optional<Role> getRoleById(int id) {
        return roleRepository.findById((long) id);
    }

    public Role createRole(Role role) {
        Role role1 = roleRepository.save(role);
        return role1;
    }
    //Update Role
    public Role updateRole(int id, Role roleDetails) {
        Role role = roleRepository.findById((long) id)
                .orElseThrow(() -> new RuntimeException("Role not found"));
        role.setName(roleDetails.getName());
        return roleRepository.save(role);
    }

    public void deleteRole(int id) {
        roleRepository.deleteById((long) id);
    }
}
