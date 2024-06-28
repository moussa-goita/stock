package odk.kalanso.stock.controllers;

import odk.kalanso.stock.entities.Admin;
import odk.kalanso.stock.entities.Manager;
import odk.kalanso.stock.entities.Role;
import odk.kalanso.stock.services.AdminService;
import odk.kalanso.stock.services.ManagerService;
import odk.kalanso.stock.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private ManagerService managerService;
    @Autowired
    private RoleService roleService;

    @GetMapping("/all")
    public List<Admin> allAdmin(){
        return  adminService.allAdmin();
    }
    @PostMapping("/create")
    public Admin createAdmin(@RequestBody Admin admin){
        return adminService.createAdmin(admin);
    }

    @PutMapping("/update/{id}")
    public Admin updateAdmin(@PathVariable Long id,
                             @RequestBody Admin admin) {
        return adminService.updateAdmin(id,admin);
    }
    //Manager
    @GetMapping("/all")
    public List<Manager> allAManager(){
        return  managerService.allManager();
    }
    @PostMapping("/create")
    public Manager createManager(@RequestBody Manager manager){
        return managerService.createManager(manager);
    }

    @PutMapping("/update/{id}")
    public Manager updateManager(@PathVariable Long id,
                             @RequestBody Manager manager) {
        return managerService.updateManager(id,manager);
    }
    //Role
    @GetMapping("/role/{id}")
    public List<Role> AllRoles() {
        return roleService.AllRoles();
    }

    //Create Role
    @PostMapping("role/create/")
    public Role createRole( @RequestBody Role role) {
        return roleService.createRole(role);
    }

    @PutMapping("role/update/{id}/")
    public ResponseEntity<Role> updateRole(@PathVariable int id,
                                           @RequestBody Role roleDetails) {
        return ResponseEntity.ok(roleService.updateRole(id, roleDetails));
    }
    //delete
    @DeleteMapping("role/delete/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable int id) {
        roleService.deleteRole(id);
        return ResponseEntity.noContent().build();
    }

}

