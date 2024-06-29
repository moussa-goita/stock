package odk.kalanso.stock.services;

import odk.kalanso.stock.entities.Admin;
import odk.kalanso.stock.entities.Manager;
import odk.kalanso.stock.entities.Role;
import odk.kalanso.stock.repositories.ManagerRepository;
import odk.kalanso.stock.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ManagerService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ManagerRepository managerRepository;

    public List<Manager> allManager(){
        return managerRepository.findAll();
    }
    public Manager createManager(Manager manager){
        Role role = roleRepository.findByName("Manager" ) ;
        manager.setRoles(Collections.singletonList(role));
        return managerRepository.save(manager);
    }
    public void deleteManager(Long id){
         managerRepository.deleteById(id);
    }

    //update Admin
    public Manager updateManager(Long id, Manager manager) {
        if (managerRepository.existsById(id)) {
            Manager existingManager = managerRepository.findById(id).orElse(null);
            if (existingManager != null) {
                if (manager.getUsername() != null && !manager.getUsername().equals(existingManager.getUsername())) {
                    existingManager.setUsername(manager.getUsername());
                }
                if (manager.getEmail() != null && !manager.getEmail().equals(existingManager.getEmail())) {
                    existingManager.setEmail(manager.getEmail());
                }
                if (manager.getPwd() != null && ! existingManager.getPwd().equals(existingManager.getPwd())) {
                    existingManager.setPwd(manager.getPwd());
                }
                return managerRepository.save(existingManager);
            }
        }
        return null;
    }

//    public Admin updateAdmin(Admin admin,){
//        return adminRepository.save(admin);
//    }
}
