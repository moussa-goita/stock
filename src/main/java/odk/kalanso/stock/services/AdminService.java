package odk.kalanso.stock.services;

import odk.kalanso.stock.entities.Admin;
import odk.kalanso.stock.entities.Role;
import odk.kalanso.stock.repositories.AdminRepository;
import odk.kalanso.stock.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private RoleRepository roleRepository;

    public List<Admin> allAdmin(){
        return adminRepository.findAll();
    }
    public Admin createAdmin(Admin admin){
       Role role = roleRepository.findByName("Admin" ) ;
       admin.setRoles(Collections.singletonList(role));
        return this.adminRepository.save(admin);
    }
//    public Admin deleteAdmin(int Id){
//        return adminRepository.deleteById(Id);
//    }

    //update Admin
    public Admin updateAdmin(Long id, Admin admin) {
        if (adminRepository.existsById(id)) {
            Admin existingAdmin = adminRepository.findById(id).orElse(null);
            if (existingAdmin != null) {
                if (admin.getUsername() != null && !admin.getUsername().equals(existingAdmin.getUsername())) {
                    existingAdmin.setUsername(admin.getUsername());
                }
                if (admin.getEmail() != null && !admin.getEmail().equals(existingAdmin.getEmail())) {
                    existingAdmin.setEmail(admin.getEmail());
                }
                if (admin.getPwd() != null && ! existingAdmin.getPwd().equals(existingAdmin.getPwd())) {
                    existingAdmin.setPwd(admin.getPwd());
                }
                return adminRepository.save(existingAdmin);
            }
        }
        return null;
    }

}
